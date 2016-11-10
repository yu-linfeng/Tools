/**
 * 
 */
package com.y.tools;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 余林丰
 *
 * 2016年11月10日
 */
public class TypeConvert {
	
	public static <T> Map<String, Object> pojo2Map(T pojo){
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != pojo){
			Class<?> clazz = pojo.getClass();
			Field[] fields = clazz.getDeclaredFields();
			if (null != fields && fields.length > 0){
				for (Field field : fields){
					if (Modifier.isPrivate(field.getModifiers()) && !Modifier.isStatic(field.getModifiers())){
						try {
							String fieldName = field.getName();
							Method method = clazz.getMethod("get" + getMethodName(fieldName));
							Object value = method.invoke(pojo);
							map.put(fieldName, value);
						} catch (Exception e) {
						}
					}
				}
				return map;
			}
		}
		
		return null;	
	}

	/**
	 * @param fieldName
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private static String getMethodName(String fieldName) throws UnsupportedEncodingException {
		byte[] fieldNameBytes = fieldName.getBytes("UTF-8");
		fieldNameBytes[0] = (byte)((char) fieldNameBytes[0] - 'a' + 'A');
		return new String(fieldNameBytes);
	}
}
