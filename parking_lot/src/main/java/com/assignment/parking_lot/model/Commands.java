package com.assignment.parking_lot.model;

import java.util.HashMap;
import java.util.Map;



public class Commands {
	
	public static volatile Map<String, Integer> commandsMap = new HashMap<String, Integer>();
	
	

	public static Map<String, Integer> getCommandsMap() {
		commandsMap.put("CREATE_PARKING_LOT", 1);
		commandsMap.put("PARK", 1);
		commandsMap.put("LEAVE", 2);
		commandsMap.put("STATUS", 0);
		
		return commandsMap;
	}

	public static void setCommandsMap(Map<String, Integer> commandsMap) {
		Commands.commandsMap = commandsMap;
	}
	
	

}
