package com.assignment.parking_lot.service;

import java.util.Optional;

import com.assignment.parking_lot.Exception.ParkingException;
import com.assignment.parking_lot.model.Car;


public interface ParkingServicesProvider {

public void createParkingLot(int capacity) throws ParkingException;
	
	public Optional<Integer> park(Car car) throws ParkingException;
	
	public void unPark(String regNo,int hours) throws ParkingException;
	
	public void getStatus() throws ParkingException;
	
	public Optional<Integer> getAvailableSlotsCount() throws ParkingException;
	
	public void doCleanup();
}
