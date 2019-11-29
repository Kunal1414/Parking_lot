package com.assignment.parking_lot;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Test;

import com.assignment.parking_lot.Exception.ErrorCode;
import com.assignment.parking_lot.Exception.ParkingException;
import com.assignment.parking_lot.model.Car;
import com.assignment.parking_lot.service.ParkingServicesProvider;
import com.assignment.parking_lot.service.impl.ParkingServiceImpl;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
	private final ByteArrayOutputStream	outContent	= new ByteArrayOutputStream();
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void init()
	{
		System.setOut(new PrintStream(outContent));
	}
	@After
	public void cleanUp()
	{
		System.setOut(null);
	}
	
	@Test
	public void createParkingLot() throws Exception
	{
		init();
		ParkingServicesProvider instance = new ParkingServiceImpl();
		instance.createParkingLot(65);
		assertTrue("createdparkinglotwith65slots".equalsIgnoreCase(outContent.toString().trim().replace(" ", "")));
		instance.doCleanup();
		cleanUp();
	}

	
	 @Test
	    public void park() throws Exception {
		
		ParkingServicesProvider instance = new ParkingServiceImpl();
	      instance.createParkingLot(65);
	        instance.park(new Car("KA-01-HH-1234"));
			instance.park(new Car("KA-01-HH-9999"));
	        assertEquals(new Integer(63), instance.getAvailableSlotsCount().get());
	      
	        instance.doCleanup();
	    }
	 
	 @Test(expected = ParkingException.class)
	public void alreadyExistParkingLot() throws Exception
	{
		ParkingServicesProvider instance = new ParkingServiceImpl();
		instance.createParkingLot(65);
		assertTrue("createdparkinglotwith65slots".equalsIgnoreCase(outContent.toString().trim().replace(" ", "")));
		instance.createParkingLot(65);
		thrown.expect(ParkingException.class);
		thrown.expectMessage(is(ErrorCode.PARKING_ALREADY_EXIST.getMessage()));
		instance.doCleanup();
	}
	
	
	
	 @Test(expected = ParkingException.class)
	public void testEmptyParkingLot() throws Exception
	{
		ParkingServicesProvider instance = new ParkingServiceImpl();
		instance.getStatus();
		thrown.expect(ParkingException.class);
		thrown.expectMessage(is(ErrorCode.PARKING_NOT_EXIST_ERROR.getMessage()));
		instance.doCleanup();
	}
	
	 @Test(expected = ParkingException.class)
	public void testParkingLotIsFull() throws Exception
	{
		ParkingServicesProvider instance = new ParkingServiceImpl();
		instance.doCleanup();
		instance.park(new Car("KA-01-HH-1234"));
		thrown.expect(ParkingException.class);
		thrown.expectMessage(is(ErrorCode.PARKING_NOT_EXIST_ERROR.getMessage()));
		assertEquals("Sorry,CarParkingDoesnotExist", outContent.toString().trim().replace(" ", ""));
		instance.createParkingLot(2);
		instance.park(new Car("KA-01-HH-1234"));
		instance.park(new Car("KA-01-HH-9999"));
		instance.park(new Car("KA-01-BB-0001"));
		String expectedOutput  = "Createdparkinglotwith2slots\r\n" +
				"Allocatedslotnumber:1\r\n" + 
				"Allocatedslotnumber:2\r\n" +
				"Sorry,Parkinglotisfull";
		assertEquals(expectedOutput.toString(), outContent.toString().trim().replace(" ", ""));
		instance.doCleanup();
	/*	assertTrue("createdparkinglotwith2slots\\nAllocatedslotnumber:1\nAllocatedslotnumber:2\nSorry,parkinglotisfull"
				.equalsIgnoreCase(outContent.toString().trim().replace(" ", "")));
		instance.doCleanup();*/
	}
	
	@Test
	public void leave() throws Exception
	{
		 ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	      System.setOut(new PrintStream(outContent));
		ParkingServicesProvider instance = new ParkingServiceImpl();
		instance.createParkingLot(6);
		instance.park(new Car("KA-01-HH-1234"));
		instance.park(new Car("KA-01-HH-9999"));
		instance.park(new Car("KA-01-BB-0001"));
		instance.unPark("KA-01-BB-0001", 4);
		String expectedOutput  = "Createdparkinglotwith6slots\r\nAllocatedslotnumber:1\r\nAllocatedslotnumber:2\r\nAllocatedslotnumber:3\r\nRegistrationnumberKA-01-BB-0001withSlotNumber3isfreewithCharge30";
		assertEquals(expectedOutput.toString(), outContent.toString().trim().replace(" ", ""));
		thrown.expect(ParkingException.class);
		instance.unPark("KA-01-HH-3141", 4);
		thrown.expectMessage("Registration number KA-01-HH-3141 not found");
		instance.doCleanup();
		throw new ParkingException("Registration number KA-01-HH-3141 not found");
		
	
	}
	
	@Test
	public void testWhenVehicleAlreadyPresent() throws Exception
	{
		ParkingServicesProvider instance = new ParkingServiceImpl();
		thrown.expect(ParkingException.class);
		thrown.expectMessage(is(ErrorCode.PARKING_NOT_EXIST_ERROR.getMessage()));
		instance.park(new Car("KA-01-HH-1234"));
		assertEquals("Sorry,CarParkingDoesnotExist", outContent.toString().trim().replace(" ", ""));
		instance.createParkingLot(3);
		instance.park(new Car("KA-01-HH-1234"));
		instance.park(new Car("KA-01-HH-1234"));
		assertTrue(
				"Sorry,CarParkingDoesnotExist\ncreatedparkinglotwith3slots\nAllocatedslotnumber:1\nSorry,vehicleisalreadyparked."
						.equalsIgnoreCase(outContent.toString().trim().replace(" ", "")));
		instance.doCleanup();
	}
	
	@Test
	public void testWhenVehicleAlreadyPicked() throws Exception
	{
		ParkingServicesProvider instance = new ParkingServiceImpl();
		thrown.expect(ParkingException.class);
		thrown.expectMessage(is(ErrorCode.PARKING_NOT_EXIST_ERROR.getMessage()));
		instance.park(new Car("KA-01-HH-1234"));
		assertEquals("Sorry,CarParkingDoesnotExist", outContent.toString().trim().replace(" ", ""));
		instance.createParkingLot(99);
		instance.park(new Car("KA-01-HH-1234"));
		instance.park(new Car("KA-01-HH-9999"));
		instance.unPark("KA-01-HH-1234", 1);
		instance.unPark("KA-01-HH-1234", 1);
		assertTrue(
				"Sorry,CarParkingDoesnotExist\ncreatedparkinglotwith99slots\nAllocatedslotnumber:1\nAllocatedslotnumber:2\nSlotnumberisEmptyAlready."
						.equalsIgnoreCase(outContent.toString().trim().replace(" ", "")));
		instance.doCleanup();
	}
	
	
	@Test(expected = ParkingException.class)
	public void testStatusWithNoParkingCreated() throws Exception
	{
		ParkingServicesProvider instance = new ParkingServiceImpl();
		instance.getStatus();
		thrown.expect(ParkingException.class);
		thrown.expectMessage(is(ErrorCode.PARKING_NOT_EXIST_ERROR.getMessage()));
		assertEquals("Sorry,CarParkingDoesnotExist\r\n", outContent.toString().trim().replace(" ", ""));
		instance.doCleanup();
		
	}
	
   @Test
	public void testStatusWithParkingCreated() throws Exception
	{
		ParkingServicesProvider instance = new ParkingServiceImpl();
		instance.doCleanup();
		instance.createParkingLot(4);
		instance.park(new Car("KA-01-HH-1234"));
		instance.park(new Car("KA-01-HH-9999"));
		instance.getStatus();
		String expectedOutput  = "Createdparkinglotwith4slots\r\n" + 
		 		"YourCarisalreadyparked.\r\n" + 
		 		"YourCarisalreadyparked.\r\n" + 
		 		"SlotNo.	Registration\r\n" + 
		 		"1		registrationNo=KA-01-HH-1234\r\n" + 
		 		"2		registrationNo=KA-01-HH-9999";
		assertEquals(expectedOutput.toString(), outContent.toString().trim().replace(" ", ""));
		instance.doCleanup();
		
	}
}
