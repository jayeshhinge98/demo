package com.test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class demo2{
	
	public static void main(String[] args) throws IOException{
		Scanner s1=new Scanner(new File("src/main/resources/textfile.txt"));
		
		String s=null;
		while(s1.hasNext()) {
			s=s+s1.next();
		}
		//String expectedString=p.getProperty("helpContent");
		System.out.println("Expected contents: "+s);
	}

}
