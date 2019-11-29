package com.assignment.parking_lot.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Car implements Externalizable {
	
	private String	registrationNo	= null;

	
	public Car(String registrationNo)
	{
		this.registrationNo = registrationNo;

	}
	
	public String getRegistrationNo() {
		return registrationNo;
	}


	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}


	@Override
	public String toString()
	{
		return "registrationNo=" + registrationNo ;
	}
	
	
	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub
		
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}

}
