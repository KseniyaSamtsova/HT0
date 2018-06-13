package runner;

import building.Building;
import bulb.Bulb;
import furniture.ArmChair;
import furniture.Table;

public class Runner {

	public static void main(String[] args) {
		Building building = new Building ("MyBuilding");
		building.addRoom("Room1", 25, 2);
		building.getRoom("Room1").add(new Bulb(300));
		building.getRoom("Room1").add(new Bulb(400));
		building.getRoom("Room1").add(new ArmChair("Comfortable ArmChair", 1, 5));
		building.getRoom("Room1").add(new Table("Super Table", 2));
		
		building.addRoom("Room2", 25, 2);
		building.decribe();
	}
}
