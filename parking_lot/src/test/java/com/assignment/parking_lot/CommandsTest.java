package com.assignment.parking_lot;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.assignment.parking_lot.model.Commands;

public class CommandsTest {

	 Commands commands = new Commands();
	    @SuppressWarnings("static-access")
		@Test
	    public void checkCommandInList() throws Exception {
	    	commands.getCommandsMap();
	        assertFalse(commands.commandsMap.isEmpty());
	        assertTrue(commands.commandsMap.containsKey("CREATE_PARKING_LOT"));
	        assertFalse(commands.commandsMap.containsKey("mytestcommand"));
	    }
}
