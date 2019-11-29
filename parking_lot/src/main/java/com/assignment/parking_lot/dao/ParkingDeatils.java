package com.assignment.parking_lot.dao;

import java.util.List;

import com.assignment.parking_lot.model.Car;

@SuppressWarnings("hiding")
public interface ParkingDeatils<Car> {
	
    public int parkCar(Car car);
	
	public int leaveCar(String regNo, int hours, Car car);
	
	public List<String> getStatus();
	
	public int getAvailableSlotsCount();
	
	public void doCleanup();

}
