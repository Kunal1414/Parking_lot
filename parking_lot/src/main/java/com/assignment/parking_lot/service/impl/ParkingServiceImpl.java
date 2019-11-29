package com.assignment.parking_lot.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.assignment.parking_lot.Exception.ErrorCode;
import com.assignment.parking_lot.Exception.ParkingException;
import com.assignment.parking_lot.dao.ParkingDeatils;
import com.assignment.parking_lot.dao.impl.ParkingDeatilsImpl;
import com.assignment.parking_lot.model.Car;
import com.assignment.parking_lot.service.ParkingLogic;
import com.assignment.parking_lot.service.ParkingServicesProvider;


public class ParkingServiceImpl implements ParkingServicesProvider {
	
    private ParkingDeatils<Car> dataManager = null;
	
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	@SuppressWarnings("unchecked")
	public void createParkingLot(int capacity) throws ParkingException {

		if (dataManager != null)
			throw new ParkingException(ErrorCode.PARKING_ALREADY_EXIST.getMessage());
		List<Integer> capacityList = new ArrayList<Integer>();
		ParkingLogic parkingLogic = new ParkingLogic();
		capacityList.add(capacity);
		this.dataManager = ParkingDeatilsImpl.getInstance(capacity, parkingLogic);
		System.out.println("Created parking lot with " + capacity + " slots");
		
	}

	public Optional<Integer> park(Car car) throws ParkingException {
		Optional<Integer> value = Optional.empty();
		lock.writeLock().lock();
		validateParkingLot();
		try
		{
			value = Optional.of(dataManager.parkCar(car));
			if (value.get() == -1)
				System.out.println("Sorry, Parking lot is full");
			else if (value.get() == -2)
				System.out.println("Your Car is already parked.");
			else
				System.out.println("Allocated slot number: " + value.get());
		}
		catch (Exception e)
		{
			throw new ParkingException(ErrorCode.PROCESSING_ERROR.getMessage(), e);
		}
		finally
		{
			lock.writeLock().unlock();
		}
		return value;
	}

	public void unPark(String regNo, int hours) throws ParkingException {
		lock.writeLock().lock();
		validateParkingLot();
		try
		{
			Car car = new Car(regNo);
			int slotNumber = dataManager.leaveCar(regNo,hours, car);
			if (slotNumber!=0)
				System.out.println("Registration number " + regNo + " with Slot Number " + slotNumber + " is free with Charge " + ((10*hours)-10));
			else
				System.out.println("Slot number is Empty Already.");
		}
		catch (Exception e)
		{ 
			throw new ParkingException(ErrorCode.INVALID_VALUE.getMessage().replace("{variable}", regNo), e);
		}
		finally
		{
			lock.writeLock().unlock();
		}
		
	}

	public void getStatus() throws ParkingException {
		lock.readLock().lock();
		validateParkingLot();
		try
		{
			System.out.println("Slot No.\tRegistration");
			List<String> statusList = dataManager.getStatus();
			if (statusList.size() == 0)
				System.out.println("Sorry, parking lot is empty.");
			else
			{
				for (String statusSting : statusList)
				{
					System.out.println(statusSting);
				}
			}
		}
		catch (Exception e)
		{
			throw new ParkingException(ErrorCode.PROCESSING_ERROR.getMessage(), e);
		}
		finally
		{
			lock.readLock().unlock();
		}
		
	}

	public Optional<Integer> getAvailableSlotsCount() throws ParkingException {
		lock.readLock().lock();
		Optional<Integer> value = Optional.empty();
		validateParkingLot();
		try
		{
			value = Optional.of(dataManager.getAvailableSlotsCount());
		}
		catch (Exception e)
		{
			throw new ParkingException(ErrorCode.PROCESSING_ERROR.getMessage(), e);
		}
		finally
		{
			lock.readLock().unlock();
		}
		return value;
	}


	public void doCleanup() {
		if (dataManager != null)
			dataManager.doCleanup();
		
	}
	
	private void validateParkingLot() throws ParkingException
	{
		if (dataManager == null)
		{
			throw new ParkingException(ErrorCode.PARKING_NOT_EXIST_ERROR.getMessage());
		}
	}



}
