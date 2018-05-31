package com.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Demo1 {
	
	public static void main(String[] arg) {
		List<String> list=new ArrayList<String>();
		list.add("test1");
		list.add("test2");
		list.add("test3");
		list.add("test4");
		for (String s:list) {
			System.out.print(s+", ");
		}
	}
	
	
}
