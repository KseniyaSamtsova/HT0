package furniture;

public abstract class Furniture {
	private String furnitureName;
	private int spaceUsageFrom;
	private int spaceUsageTo;
	
	public Furniture(String furnitureName, int spaceUsageFrom, int spaceUsageTo) {
		this.furnitureName = furnitureName;
		this.spaceUsageFrom = spaceUsageFrom;
		this.spaceUsageTo = spaceUsageTo;
	}

	public String getFurnitureName() {
		return furnitureName;
	}

	public void setFurnitureName(String furnitureName) {
		this.furnitureName = furnitureName;
	}

	public int getSpaceUsageFrom() {
		return spaceUsageFrom;
	}

	public void setSpaceUsageFrom(int spaceUsageFrom) {
		this.spaceUsageFrom = spaceUsageFrom;
	}

	public int getSpaceUsageTo() {
		return spaceUsageTo;
	}

	public void setSpaceUsageTo(int spaceUsageTo) {
		this.spaceUsageTo = spaceUsageTo;
	}

	@Override
	public String toString() {
		return "Furniture [furnitureName=" + furnitureName + ", spaceUsageFrom=" + spaceUsageFrom + ", spaceUsageTo="
				+ spaceUsageTo + "]";
	}
	
}
