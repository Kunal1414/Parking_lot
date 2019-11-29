package com.assignment.parking_lot.model;

public enum Command {

	CREATE_PARKING_LOT,
	PARK,
	LEAVE,
	STATUS,
	NOT_AVAILABLE,
	VEHICLE_ALREADY_EXIST,
	NOT_FOUND;
	/*NOT_AVAILABLE(-1),
	VEHICLE_ALREADY_EXIST(-1),
	NOT_FOUND(-1);*/
	
	private Command(int value) { }
	
	private Command() {
		
	}

	
	
}
