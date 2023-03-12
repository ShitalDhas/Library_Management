package com.person;

public class Student extends Person {
	private int rollNumber;
	private int std;
	private String division;
	
	
	public int getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}
	public int getStd() {
		return std;
	}
	public void setStd(int std) {
		this.std = std;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	

	
	public Student(String name, String emailId, String phoneNumber, String address, String dob, int rollNumber, int std,
			String division) {
		super(name, emailId, phoneNumber, address, dob);
		this.rollNumber = rollNumber;
		this.std = std;
		this.division = division;
	}
	
	public Student() {
		super();
	}
	
	@Override
	public String toString() {
		return "Students [rollNumber=" + rollNumber + ", std=" + std + ", division=" + division + ", name=" + name + ", emailId="
				+ emailId + ", phoneNumber=" + phoneNumber + ", address=" + address + ", dob=" + dob + "]";
	}
	
	
}
