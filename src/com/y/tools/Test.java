/**
 * 
 */
package com.y.tools;

import java.util.Map;

/**
 * @author 余林丰
 *
 * 2016年11月10日
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Student stu = new Student();
		stu.setId(1);
		stu.setName("kevin");
		stu.setNum(1000);
		Map<String, Object> map = TypeConvert.pojo2Map(stu);
		for (String key : map.keySet()){
			System.out.println(key);
		}
		for (Object value : map.values()){
			System.out.println(value);
		}
	}

}
