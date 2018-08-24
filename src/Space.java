import java.util.Random;

public class Space {
	private Boolean container;
	private Chest chest;
	private AreaType.areaType terrain;
	
	public Space()	{
		Random rand = new Random();
		int randTerrain = rand.nextInt(3);
		
		switch (randTerrain)	{
		case 0:
			this.terrain = AreaType.areaType.field;
			break;
		case 1: 
			this.terrain = AreaType.areaType.wood;
			break;
		case 2:
			this.terrain = AreaType.areaType.lake;
			break;
		default:
			System.out.println("Not a valid terrain type...");
		}
		
		int randContainer = rand.nextInt(100);
		
		if (randContainer <= 5)	this.container = true;
		else this.container = false;
		
		if (this.container)	{
			this.chest = new Chest();
		} else this.chest = null;
	}
	
	public Boolean getContainer()	{
		return container;
	}
	
	public Chest getChest()	{
		return chest;
	}
	
	public AreaType.areaType getTerrain()	{
		return terrain;
	}
	
	public String toString()	{
		String out = "You find yourself ";
		switch (terrain)	{
		case wood:
			out += "surrounded by lots of trees, you are in a wood.";
			break;
		case lake:
			out += "beside a lake, off to your left you can see a small boat.";
			break;
		case field:
			out += "in a field, it seems the farmer is growing wheat.";
			break;
		}
		return out;
	}
}
