package com.devuger.common.support;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;

public class DefaultKeyGenerator implements KeyGenerator {

	public static final int NO_PARAM_KEY = 0;
	public static final int NULL_PARAM_KEY = 53;
	
	@Override
	public Object generate(Object target, Method method, Object... params) {
		// TODO Auto-generated method stub
		 if (params.length == 1) {
		        return (params[0] == null ? NULL_PARAM_KEY : params[0]);
		    }
		    if (params.length == 0) {
		        return NO_PARAM_KEY;
		    }
		    int hashCode = 17;
		    for (Object object : params) {
		        hashCode = 31 * hashCode + (object == null ? NULL_PARAM_KEY : object.hashCode());
		    }
		    return Integer.valueOf(hashCode);
	}

}
