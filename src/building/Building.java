package building;

import java.util.HashMap;
import java.util.Map;

import exception.IlluminanceTooMuchException;
import room.Room;

public class Building {
	private String nameOfBuilding;
	
	Map<String, Room> rooms = new HashMap<String, Room>();
	
	public Building(String nameOfBuilding) {
		this.nameOfBuilding = nameOfBuilding;
	}
	public void addRoom (String roomName, int space, int numberOfWindows) {
		if (checkWindowIlluminance(numberOfWindows)) {
			rooms.put(roomName, new Room(roomName, space, numberOfWindows));
		}
	}
	private boolean checkWindowIlluminance(int nubmerOfWindows) {
		int windowIlluminance = nubmerOfWindows * Room.WINDOW_ILLUMINANCE; 
		
		try {
			if (windowIlluminance > Room.MAX_ILLUMINANCE) {
				throw new IlluminanceTooMuchException();
			} else {
				return true;
			}
			
		} catch (IlluminanceTooMuchException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public Room getRoom(String roomName) {
		return rooms.get(roomName);
	}
	public String getNameOfBuilding() {
		return nameOfBuilding;
	}
	public void setNameOfBuilding(String nameOfBuilding) {
		this.nameOfBuilding = nameOfBuilding;
	}
	public void decribe() {
		String description = this.nameOfBuilding;
		
		for (Map.Entry<String, Room> room: rooms.entrySet()) {
			description += "\n " + room.getKey();
			Room roomInstance = room.getValue();
			description += "\n  " + roomInstance.getIlluminance();
			description += "\n  " + roomInstance.getSpace();
			description += "\n  " + roomInstance.getFurniture();
		}
		
		System.out.println(description);	
	}
}
