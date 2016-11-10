/**
 * 
 */
package com.y.tools;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 余林丰
 *
 * 2016年11月10日
 */
public class Test {

	/**
	 * @param args
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
//		Student stu = new Student();
//		stu.setId(1);
//		stu.setName("kevin");
//		stu.setNum(1000);
//		Map<String, Object> map = TypeConvert.pojo2Map(stu);
//		for (String key : map.keySet()){
//			System.out.println(key);
//		}
//		for (Object value : map.values()){
//			System.out.println(value);
//		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 1);
		map.put("name", "kevin");
		map.put("num", 1000);
		Student stu = TypeConvert.map2Pojo(map, Student.class);
		System.out.println(stu.getId() + stu.getName() + stu.getNum());
	}

}
