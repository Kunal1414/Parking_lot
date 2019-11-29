package com.assignment.parking_lot.service;

import java.util.TreeSet;

public class ParkingLogic {

private TreeSet<Integer> freeSlots;
	
	public ParkingLogic()
	{
		freeSlots = new TreeSet<Integer>();
	}
	

	public void addSlot(int i)
	{
		freeSlots.add(i);
	}
	

	public int getSlot()
	{
		return freeSlots.first();
	}
	
	
	public void removeSlot(int availableSlot)
	{
		freeSlots.remove(availableSlot);
	}
}
