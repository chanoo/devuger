package com.devuger.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.Vector;

/**
 * @version 1.0<br>
 * 클래스 설명 <br>
 * Object 관련 기능성 Method를 제공하는 Util Class<br>
 */
public class HelloObjectUtil
{
	/** Caller Class <code>CALL_CLASS</code> */
	public static final int CALL_CLASS = 1;
	/** Caller Method <code>CALL_METHOD</code> */
	public static final int CALL_METHOD = 2;
	/** Caller LineNumber <code>CALL_LINENUM</code> */
	public static final int CALL_LINENUM = 4;

	/**
	 * 생성자
	 */
	public HelloObjectUtil(){}

	/**
	 * Object의 Null Check
	 * @param tmpObject Null Check할 Object
	 * @return Object가 null이면 true, instance화 되어 있으면 false
	 */
	public static boolean isNull(Object tmpObject){
		if(tmpObject == null)
			return true;

        return false;
	}

	/**
	 * Object 배열의 Null Check
	 * @param tmpObject Null Check할 Object 배열
	 * @return Object배열이 null이면 true, instance화 되어 있으면 false
	 */
	public static boolean isArrayNull(Object[] tmpObject)
	{
		if(tmpObject == null || tmpObject.length < 1 )
			return true;

        return false;
	}


	/**
	* Object 의 복제. 일반적으로 <code>java.lang.Object.clone()</code> 함수를
	* 사용하여 Object를 복제하면 Object내에 있는 Primitive type을 제외한 Object
	* field들은 복제가 되는 것이 아니라 같은 Object의 reference를 갖게 된다.<br>
	* 그러나 이 Method를 사용하면 각 field의 동일한 Object를 새로 복제(clone)하여 준다.
	*
	* @param object java.lang.Object
	* @return java.lang.Object
	*
	* @see #clone(Object[])
	* @see #clone(java.util.Vector)
	*/
	public static Object clone(Object object)
	{
		Class<?> c = object.getClass();
		Object newObject = null;

		try
		{
			newObject = c.newInstance();
		}
		catch(Exception e )
		{
			return null;
		}

		Field[] field = c.getFields();

		for (int i=0 ; i<field.length; i++)
		{
			try
			{
				Object f = field[i].get(object);
				field[i].set(newObject, f);
			}
			catch(Exception e){}
		}

		return newObject;
	}


	/**
	* Object[] 의 복제. Object의 Array 를 복제(clone)하여 새로운 Instance를 만든다.
	*
	* @param objects java.lang.Object[]
	* @return java.lang.Object[]
	*
	* @see #clone(Object)
	* @see #clone(java.util.Vector)
	*/
	public static Object[] clone(Object[] objects)
	{
		int length = objects.length;
		Class<?> c = objects.getClass().getComponentType();
		Object array = Array.newInstance(c, length);

		for(int i=0;i<length;i++)
		{
			Array.set(array, i, HelloObjectUtil.clone(objects[i]));
		}

		return (Object[])array;
	}

	/**
	* Vector 의 복제. 일반적으로 Vector Object를 clone()을 하면<br>
	* Vector내의 Element Object는 새로 생성되는 것이 아니라<br>
	* 동일한 Object에 대한 reference만 새로 생성되기 때문에 같은 Element Object를<br>
	* reference하는 Vector를 생성하게 된다. 그러나 이 method를 사용하면<br>
	* Vector내의 모든 Element Object도 새로 복제하여 준다.<br>
	*
	* @param objects java.util.Vector
	* @return java.util.Vector
	*
	* @see #clone(Object)
	* @see #clone(Object[])
	*/
	public static Vector<Object> clone(Vector<Object> objects)
	{
		Vector<Object> newObjects = new Vector<Object>();
		Enumeration<Object> e = objects.elements();

		while(e.hasMoreElements())
		{
			Object o = e.nextElement();
			newObjects.addElement(HelloObjectUtil.clone(o));
		}

		return newObjects;
	}



	/**
	* Object의 Deep copy를 구현. 보통 clone()은 reference만 copy하는데 반해
	* reference만 하지 않고 member, method를 모두 copy한다.
	*
	* @param o Deep Copy 하고자 하는 Object
	* @return Deep Copy를 마친 Object
	*
	* @see #clone(Object)
	* @see #clone(Object[])
	*/
    public static Object deepClone( Object o )
    {
	    try
		{
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(b);
            out.writeObject(o);
            ByteArrayInputStream bi = new ByteArrayInputStream(b.toByteArray());
            ObjectInputStream in = new ObjectInputStream(bi);

            return in.readObject();
	    }
	    catch (Exception e)
		{
	        return null;
	    }
	}


	/**
	 * 현재 Object를 호출한 Class를 반환한다.
	 * @return Caller 리스트 문자열
	 */
	public static String getCallerClass()
	{
	    return getCaller(2, CALL_CLASS);
	}

	/**
	 * 현재 Object를 호출한 Method를 반환한다.
	 * @return Caller 리스트 문자열
	 */
	public static String getCallerMethod()
	{
	    return getCaller(2, CALL_METHOD);
	}

	/**
	 * 현재 Object를 호출한 Line Num을 반환한다.
	 * @return Caller 리스트 문자
	 */
	public static String getCallerLine()
	{
	    return getCaller(2, CALL_LINENUM);
	}

	/**
	 * 현재 Object를 호출한 class 및 method, linenum을 주어진 Depth만큼 List한다.
	 * @param depth 확인코저 하는 caller depth
	 * @return caller 리스트 문자열
	 */
	public static String getCaller(int depth)
	{
	    return getCaller(depth+1, CALL_CLASS+CALL_METHOD+CALL_LINENUM);
	}

	/**
	 * 현재 Object를 호출한 주어진 Depth만큼 List한다.
	 * Type에 따라 CLASS/METHOD/LINENUM
	 * @param depth 확인고자 하는 caller depth
	 * @param type 확인하고자 하는 타입(CLASS/METHOD/LINENUM)
	 * @return caller 리스트 문자열
	 */
	public static String getCaller(int depth, int type)
	{
	    StringBuffer sb = new StringBuffer();

	    Throwable tmpThrowable = new Throwable();
	    tmpThrowable.fillInStackTrace();
	    StackTraceElement[] tmpElement = tmpThrowable.getStackTrace();

	    if( !isArrayNull(tmpElement) )
	    {
	        if( (type & CALL_CLASS) == CALL_CLASS )
	            sb.append("CLASS["+tmpElement[depth].getClassName()+"]");

	        if( (type & CALL_METHOD) == CALL_METHOD )
	            sb.append(" METHOD["+tmpElement[depth].getMethodName()+"]");

	        if( (type & CALL_LINENUM) == CALL_LINENUM )
	            sb.append(" LINE["+tmpElement[depth].getLineNumber()+"]");

//	        for(int i=0; i<tmpElement.length; i++)
//	            System.out.println(tmpElement[i].getClassName()+"."+tmpElement[i].getMethodName()+tmpElement[i].getLineNumber());

	        return sb.toString();
	    }

	    return null;
	}
	
	/**
	 * Object[] 에 value 가 포함되었는지 체크
	 * @param array
	 * @param value
	 * @return
	 */
	public static boolean contains(Object[] array, Object value){ 
		for(Object obj : array){ 
			if(obj.equals(value)) {
				return true; 
			} 
		} 
		return false; 
	}
}
