package furniture;

public class ArmChair extends Furniture {

	public ArmChair(String furnitureName, int spaceUsageFrom, int spaceUsageTo) {
		super(furnitureName, spaceUsageFrom, spaceUsageTo);
	}

	@Override
	public String toString() {
		return "ArmChair [getFurnitureName()=" + getFurnitureName() + ", getSpaceUsageFrom()=" + getSpaceUsageFrom()
				+ ", getSpaceUsageTo()=" + getSpaceUsageTo() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
}
