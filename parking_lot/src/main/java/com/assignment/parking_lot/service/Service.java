package com.assignment.parking_lot.service;

import com.assignment.parking_lot.Exception.ErrorCode;
import com.assignment.parking_lot.Exception.ParkingException;
import com.assignment.parking_lot.model.Car;
import com.assignment.parking_lot.model.Command;
import com.assignment.parking_lot.model.Commands;

public class Service {
	
	ParkingServicesProvider parkingServicesProvider;
	
	
	public void init(ParkingServicesProvider parkingServicesProvider) {
		this.parkingServicesProvider = parkingServicesProvider;
	}
	
	public void start(String input) throws ParkingException{
		String[] inputs = input.split(" ");
		String key = inputs[0];
		Command command = Command.valueOf(key.toUpperCase());
		
		switch (command)
		{
			case CREATE_PARKING_LOT :
				try
				{
					int capacity = Integer.parseInt(inputs[1]);
					parkingServicesProvider.createParkingLot(capacity);
				}
				catch (NumberFormatException e)
				{
					throw new ParkingException(ErrorCode.INVALID_VALUE.getMessage().replace("{variable}", "capacity"));
				}
				break;
			case PARK:
				Car car  = new Car(inputs[1]);
				car.setRegistrationNo(inputs[1]);
				parkingServicesProvider.park(car);
				break;
			case LEAVE:
				try
				{
					String regNo = inputs[1];
					int hours = Integer.parseInt(inputs[2]);
					parkingServicesProvider.unPark(regNo, hours);
				}
				catch (NumberFormatException e)
				{
					throw new ParkingException(
							ErrorCode.INVALID_VALUE.getMessage().replace("{variable}", "slot_number"));
				}
				break;
			case STATUS:
				parkingServicesProvider.getStatus();
				break;
			
			default:
				break;
		}
	}
	
	public  boolean checkinputs(String inputString) 
	{
		boolean valid = true;
		try
		{
			String[] inputs = inputString.split(" ");
			int params = Commands.getCommandsMap().get(inputs[0].toUpperCase());   
			switch (inputs.length)
			{
				case 1:
					if (params != 0)
						valid = false;
					break;
				case 2:
					if (params != 1) 
						valid = false;
					break;
				case 3:
					if (params != 2) 
						valid = false;
					break;
				default:
					valid = false;
			}
		}
		catch (Exception e)
		{
			valid = false;
		}
		return valid;
	}
	

}
