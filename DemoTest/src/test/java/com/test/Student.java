package com.test;

import java.util.Date;

public class Student{
	public  int roll_No=0;
	public  String Name="";
	private Date dateOfBirth;
	public  int getRoll_No() {
		return roll_No;
	}
	public void setRoll_No(int roll_No) {
		this.roll_No = roll_No;
	}
	public  String getName() {
		return Name;
	}
	public  void setName(String name) {
		Name = name;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}	
}