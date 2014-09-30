package com.devuger.common.support.base;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;


public class HelloDomain implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4674487342824130179L;

	/**
	 * 모�? ???�?�?????.
	 * 
	 * @param afterDomain
	 * @return
	 * @throws Exception
	 */
	public CompareDomain[] compareFields( HelloDomain afterDomain ) 
	throws Exception
	{
		return compareFields( afterDomain, null, null );
	}
	
	/**
	 * afterDomain�????�?? �?????.
	 * 
	 * @param afterDomain		�????Domain
	 * @param includeFields		NULL?�면 모�? ???�?�?????. NULL?????�? array??????????�?�?????.
	 * @param excludeFields		NULL?�면 ?????? ??? ???, NULL?????�? array??????????�??????? �?????.
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public CompareDomain[] compareFields( HelloDomain afterDomain, String[] includeFields, String[] excludeFields ) throws Exception 
	{	
		ArrayList<CompareDomain> list = new ArrayList<CompareDomain>();
		
		if( afterDomain == null )
			return getFields(this, includeFields, excludeFields);
		
		if( afterDomain.equals(this) )
			return new CompareDomain[0];
		
		// ??��??? �??
		for( int i = 0; includeFields != null && i < includeFields.length; i++ )
			includeFields[i] = includeFields[i].toLowerCase();
		
		for( int i = 0; excludeFields != null && i < excludeFields.length; i++ )
			excludeFields[i] = excludeFields[i].toLowerCase();
		
		PropertyDescriptor[] pd;
		CompareDomain resultDto = null;
		
		pd = listPropertyNames( afterDomain.getClass() );
		
		for( int i = 0; i < pd.length ; i++ )
		{
			if( includeFields != null && includeFields.length > 0 )
			{
				if( !containsField( includeFields, pd[i].getName() ) )
					continue;
			}
			
			if( excludeFields != null && excludeFields.length > 0 )
			{
				if( containsField( excludeFields, pd[i].getName() ) )
					continue;
			}
			
			if( pd[i].getPropertyType().getName().equals("java.lang.Class") )
    			continue;
			
			resultDto = getCompareResult( afterDomain, pd[i] );
			
			if( resultDto != null )
				list.add( resultDto );
		}

		CompareDomain[] arr = new CompareDomain[list.size()];
		list.toArray(arr);
		return arr;
	}
	
	private CompareDomain[] getFields( HelloDomain domain, String[] includeFields, String[] excludeFields ) 
	throws Exception 
	{	
		if( domain == null )
			return new CompareDomain[0];

		Class<?>[] parameterTypes = {};
		Object[] parameters = {};
		String methodName = null;
		Method method = null;
		Object data = null;
		
		ArrayList<CompareDomain> list = new ArrayList<CompareDomain>();
		
		// ??��??? �??
		for( int i = 0; includeFields != null && i < includeFields.length; i++ )
			includeFields[i] = includeFields[i].toLowerCase();
		
		for( int i = 0; excludeFields != null && i < excludeFields.length; i++ )
			excludeFields[i] = excludeFields[i].toLowerCase();
		
		PropertyDescriptor[] pd;
		CompareDomain resultDto = null;
		
		pd = listPropertyNames( domain.getClass() );
		
		for( int i = 0; i < pd.length ; i++ )
		{
			if( includeFields != null && includeFields.length > 0 )
			{
				if( !containsField( includeFields, pd[i].getName() ) )
					continue;
			}
			
			if( excludeFields != null && excludeFields.length > 0 )
			{
				if( containsField( excludeFields, pd[i].getName() ) )
					continue;
			}
			
			if( pd[i].getPropertyType().getName().equals("java.lang.Class") )
    			continue;
			
	    	methodName = pd[i].getReadMethod().getName();
	    	method = domain.getClass().getMethod(methodName, parameterTypes);
	    	data = method.invoke( domain, parameters );

	    	resultDto = new CompareDomain();
	    	resultDto.setBeforeData(data);
	    	resultDto.setAfterData("");
	    	resultDto.setFieldId( pd[i].getName() );
  	
			if( resultDto != null )
				list.add( resultDto );
		}

		CompareDomain[] arr = new CompareDomain[list.size()];
		list.toArray(arr);
		return arr;
	}
	
	private boolean containsField( String[] fields, String searchField )
	{
		searchField = searchField.toLowerCase();
		
		if( fields == null || fields.length == 0 )
			return false;
		
		if( searchField == null || searchField.length() == 0 )
			return false;
		
		for( int i = 0; i < fields.length; i++ )
		{
			if( fields[i].equals( searchField ) )
				return true;
		}
		
		return false;
	}
	
    private CompareDomain getCompareResult( HelloDomain domain, PropertyDescriptor pd ) 
    throws Exception
	{
    	Class<?>[] parameterTypes = {};
    	
    	String methodName = pd.getReadMethod().getName();
    	
    	Method methodBefore = this.getClass().getMethod(methodName, parameterTypes);
    	Method methodAfter = domain.getClass().getMethod(methodName, parameterTypes);
    	
    	Object[] parameters = {};
    	
    	Object beforeData = methodBefore.invoke( this, parameters );
    	Object afterData = methodAfter.invoke( domain, parameters );
    	
    	String beforeData_str = ( beforeData == null )? "" : beforeData.toString();
    	String afterData_str = ( afterData == null ) ? "" : afterData.toString();
    	
    	if( beforeData_str.equals(afterData_str) )
    		return null;
    	if( beforeData == afterData )
    		return null;
    	if( beforeData != null && beforeData.equals( afterData ) )
    		return null;
    	
    	CompareDomain resultDto = new CompareDomain();
    	resultDto.setBeforeData(beforeData);
    	resultDto.setAfterData(afterData);
    	resultDto.setFieldId( pd.getName() );
    	return resultDto;
	}
    
    private PropertyDescriptor[] listPropertyNames(Class<? extends HelloDomain> cls) throws IntrospectionException
    {
    	PropertyDescriptor[] pd;
    	pd = Introspector.getBeanInfo(cls).getPropertyDescriptors();

    	return pd;
    }
}
