package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo1 {
	public static void main(String[] arg) {
		System.out.println("==============LIST============");
		List<String> list = new ArrayList<String>();
		list.add("test1");
		list.add("test2");
		list.add("test3");
		list.add("test4");
		list.add("test4");
		String[] dup = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + ", ");
			dup[i] = list.get(i);
		}
		System.out.println("\n==============HASHMAP=========");
		HashMap<Integer, String> hm = new HashMap<>();
		for (int i = 0; i < list.size(); i++) {
			hm.put(i, list.get(i));
		}

		for (Map.Entry<Integer, String> entry : hm.entrySet()) {
			System.out.println("Key: " + entry.getKey() + "\t Value: " + entry.getValue());
		}
		System.out.println("==============Find Duplicates and print count of each word=========");

		for (int i = 0; i < dup.length; i++) {
			int count = 1;
			for (int j = i + 1; j < dup.length; j++) {
				if (dup[i].equals(dup[j])) {
					count = count + 1;
					dup[j] = "0";
				}
			}
			if (dup[i] != "0") {
				System.out.println(dup[i] + ": " + count);
			}
		}
		System.out.println("==============Reverse the String=========");
		String original = "Testing";
		System.out.println("Original String is: " + original);
		System.out.println("Case 1: Using StringBuffer =========");
		StringBuffer sb = new StringBuffer(original);
		System.out.println("Reverse of string is: " + sb.reverse());
		System.out.println("Case 2: Using CharArray============");
		char[] arr = original.toCharArray();
		String reverse = "";
		for (int i = arr.length - 1; i >= 0; i--) {
			reverse = reverse + arr[i];
		}
		System.out.println("Reverse of string is: " + reverse);

	}

}
