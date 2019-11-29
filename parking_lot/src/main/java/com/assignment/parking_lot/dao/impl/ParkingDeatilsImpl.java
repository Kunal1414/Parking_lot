package com.assignment.parking_lot.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.assignment.parking_lot.dao.ParkingDeatils;
import com.assignment.parking_lot.model.Car;
import com.assignment.parking_lot.service.ParkingLogic;

//Singletone class 
@SuppressWarnings("hiding")
public class ParkingDeatilsImpl<Car> implements ParkingDeatils<Car> {
	private AtomicInteger	capacity		= new AtomicInteger();
	private AtomicInteger	availability	= new AtomicInteger();
	private ParkingLogic parkingLogic; ;
	private Map<Integer, Optional<Car>> slotCarMap;
	@SuppressWarnings("rawtypes")
	private static ParkingDeatilsImpl instance = null;
	
	@SuppressWarnings("rawtypes")
	public static  ParkingDeatilsImpl getInstance(int capacity,
			ParkingLogic parkingLogic)
	{
		if (instance == null)
		{
			synchronized (ParkingDeatilsImpl.class)
			{
				if (instance == null)
				{
					instance = new ParkingDeatilsImpl(capacity, parkingLogic);
				}
			}
		}
		return instance;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ParkingDeatilsImpl(int capacity, ParkingLogic parkingLogic)
	{
		this.capacity.set(capacity);
		this.availability.set(capacity);
		this.parkingLogic = parkingLogic;
		if (parkingLogic == null)
			parkingLogic = new ParkingLogic();
		slotCarMap = new ConcurrentHashMap();
		 Optional<Car> optional = Optional.empty(); 
		for (int i = 1; i <= capacity; i++)
		{
			slotCarMap.put(i, optional);
			
			parkingLogic.addSlot(i);
		}
	}
	
	public int parkCar(Car car) {
		int availableSlot;
		if (availability.get() == 0)
		{
			return -1; 
				
		}
		else
		{
			Map<Integer, String> hashMap = 
					slotCarMap.entrySet().stream().collect(Collectors.toMap(
										entry -> entry.getKey(), 
										entry -> entry.getValue().toString())
									);
	
			availableSlot = parkingLogic.getSlot();
			if(hashMap.containsValue(Optional.of(car).toString())) {
				return -2;
			}
			
			slotCarMap.put(availableSlot, Optional.of(car));
			availability.decrementAndGet();
			parkingLogic.removeSlot(availableSlot);
		}
		return availableSlot;
	}

	public int leaveCar(String regNO, int hours, Car car) {
			
		Map<Integer, String> hashMap = 
				slotCarMap.entrySet().stream().collect(Collectors.toMap(
									entry -> entry.getKey(), 
									entry -> entry.getValue().toString())
								);
		int slotNumber= 0;
		if(hashMap.containsValue(Optional.of(car).toString())) {
			slotNumber = getKey(hashMap, Optional.of(car).toString());
		}
		
		
		if (!slotCarMap.get(slotNumber).isPresent()) // Slot already empty
			return slotNumber;
		availability.incrementAndGet();
		parkingLogic.addSlot(slotNumber);
		 Optional<Car> optional = Optional.empty(); 
		slotCarMap.put(slotNumber, optional);
		return slotNumber;
	}

	public List<String> getStatus() {
		List<String> statusList = new ArrayList<String>();
		for (int i = 1; i <= capacity.get(); i++)
		{
			Optional<Car> car = slotCarMap.get(i);
			if (car.isPresent())
			{
				statusList.add(i + "\t\t" + car.get()); // need to call registration number
			}
		}
		return statusList;
	}

	public int getAvailableSlotsCount() {
		return availability.get();
	}

	public void doCleanup() {
		this.capacity = new AtomicInteger();
		this.availability = new AtomicInteger();
		this.parkingLogic = null;
		slotCarMap = null;
		instance = null;
		
	}

	public static <K, V> K getKey(Map<K, V> map, V value) {
		for (Map.Entry<K, V> entry : map.entrySet()) {
			if (value.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}

}
