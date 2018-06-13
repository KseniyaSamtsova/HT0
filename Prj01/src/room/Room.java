package room;

import java.util.ArrayList;
import java.util.List;

import bulb.Bulb;
import exception.IlluminanceTooLittleException;
import exception.IlluminanceTooMuchException;
import exception.SpaceUsageTooMuchException;
import furniture.ArmChair;
import furniture.Furniture;
import furniture.Table;

public class Room {
	private String roomName;
	private int numberOfWindows;
	
	public final static int WINDOW_ILLUMINANCE = 700;
	public final static int MIN_ILLUMINANCE  = 300;
	public final static int MAX_ILLUMINANCE  = 4000;
	private int currentIlluminance;
	
	private final double MAX_PERCENT_SPACE = 0.7;
	private int space;
	private double currentSpace;

	List <Bulb> bulbs = new ArrayList<Bulb>();
	List <Furniture> listOfFurniture = new ArrayList<Furniture>();
	
	public Room(String roomName, int space, int numberOfWindows) {
		this.numberOfWindows = numberOfWindows;
		this.roomName = roomName;
		this.space = space;
		this.currentIlluminance = numberOfWindows * WINDOW_ILLUMINANCE;
	}
	public void add (ArmChair armChair) {
		if (checkSpace(armChair.getSpaceUsageTo())) {
			listOfFurniture.add(armChair);
		}
	}
	public void add (Table table) {
		if (checkSpace(table.getSpaceUsageTo())) {
			listOfFurniture.add(table);
		}
	}
	public void add (Bulb bulb) {
		checkBulbIlluminance(bulb.getIlluminanceOfBulb());
		bulbs.add(bulb);
	}
	
	private boolean checkBulbIlluminance(int illuminance) {
		int estimatingIlluminance = currentIlluminance + illuminance;
		
		try {
			if (estimatingIlluminance < MIN_ILLUMINANCE) {
				throw new IlluminanceTooLittleException();
				
			} else if (estimatingIlluminance > MAX_ILLUMINANCE) {
				throw new IlluminanceTooMuchException();
				
			} else {
				currentIlluminance = estimatingIlluminance;
				return true;
			}
		} catch (IlluminanceTooLittleException e) {
			System.out.println(e.getMessage());
			return false;
			
		} catch (IlluminanceTooMuchException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	private boolean checkSpace(int furnitureSpace) {
		double maxSpace = space * MAX_PERCENT_SPACE;
		double estimatingSpace = currentSpace + furnitureSpace;
		
		try {
			if (estimatingSpace > maxSpace) {
				throw new SpaceUsageTooMuchException();
			} else {
				currentSpace = estimatingSpace;
				return true;
			}
		} catch (SpaceUsageTooMuchException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public String getIlluminance() {
		String description = "Illuminance = " + this.currentIlluminance;
		description += " (" + this.numberOfWindows + " window(s) with " + WINDOW_ILLUMINANCE + ", bulb(s)";
		
		for (int i = 0; i < bulbs.size(); i++) {
			if (i == 0) {
				description += " " + bulbs.get(i).getIlluminanceOfBulb();	
			} else {
				description += " and " + bulbs.get(i).getIlluminanceOfBulb();
			}
		}
		description += ")";
		
		return description;		
	}
	
	public String getSpace() {
		String description = "Space = " + this.space;
		
		double freeSpace = this.space - this.currentSpace;
		double freeSpaceToPercent = freeSpace / this.space * 100;
		description += " (used " + this.currentSpace + ", free " + freeSpace + " or " + freeSpaceToPercent + "%)";
		
		return description;		
	}
	
	public String getFurniture() {
		String description = "Furniture";
		
		if (listOfFurniture.size() != 0) {
			description += ": ";
			
			for (int i = 0; i < listOfFurniture.size(); i++) {
				description += "\n   " + listOfFurniture.get(i).getFurnitureName();
				description += " (space " + listOfFurniture.get(i).getSpaceUsageTo() + ")";			
			}
		} else {
			description += " is empty.";
		}
		
		return description;
	}
}