import java.util.Random;

public class Chest {

	private int gold;
	
	public Chest()	{
		Random rand = new Random();
		int rand100 = rand.nextInt(100);
		
		if (rand100 < 50)	this.gold = 1;
		else if (rand100 < 75) this.gold = 3;
		else if (rand100 < 90) this.gold = 5;
		else if (rand100 < 98) this.gold = 8;
		else this.gold = 10;
	}
	
	public int getGold()	{
		return gold;
	}
	
	public String toString()	{
		return "In the chest you find " + this.gold + " gold.";
	}
	
}
