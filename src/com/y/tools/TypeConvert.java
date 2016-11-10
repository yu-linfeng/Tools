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
	
	/**
	 * a map convert a java pojo
	 * @param pojo
	 * @return
	 */
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
	 * a java pojo convert a map.
	 * @param map
	 * @param clazz
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T map2Pojo(Map<String, Object> map, Class<T> clazz) throws InstantiationException, IllegalAccessException{
		if (null != map){
			Object obj = clazz.newInstance();
			Field[] fields = clazz.getDeclaredFields();
			if (null != fields && fields.length > 0) {
				for (Field field : fields){
					if (Modifier.isPrivate(field.getModifiers()) && !Modifier.isStatic(field.getModifiers())){
						try {
							Method method = null;
							String fieldName = field.getName();
							if (field.getType() == int.class) {
								method = clazz.getMethod("set" + getMethodName(fieldName), int.class);
							} else if (field.getType() == String.class) {
								method = clazz.getMethod("set" + getMethodName(fieldName), String.class);
							} else if (field.getType() == long.class) {
								method = clazz.getMethod("set" + getMethodName(fieldName), long.class);
							} else if (field.getType() == float.class){
								method = clazz.getMethod("set" + getMethodName(fieldName), float.class);
							} else if (field.getType() == double.class) {
								method = clazz.getMethod("set" + getMethodName(fieldName), double.class);
							} else {
								return null;
							}
							method.invoke(obj, map.get(fieldName));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				return (T)obj;
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
