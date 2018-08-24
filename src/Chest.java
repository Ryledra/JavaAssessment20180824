import java.util.ArrayList;
import java.util.Random;

public class Chest {

	private int gold;
	private ArrayList<Items.items> items = new ArrayList<>();
	
	public Chest()	{
		Random rand = new Random();
		int rand100 = rand.nextInt(100);
		
		if (rand100 < 50)	this.gold = 1;
		else if (rand100 < 75) this.gold = 3;
		else if (rand100 < 90) this.gold = 5;
		else if (rand100 < 98) this.gold = 8;
		else this.gold = 10;
		
		int rand100_2 = rand.nextInt(100);
		
		if (rand100_2 < 10)	items.add(Items.items.dagger);
		else if (rand100_2 < 15) items.add(Items.items.sword);
		else if (rand100_2 < 20) items.add(Items.items.shield);
		else if (rand100_2 < 22)	items.add(Items.items.helmet);
		else if (rand100_2 < 23)	items.add(Items.items.maille_hauberk);
	}
	
	public int getGold()	{
		return gold;
	}
	
	public String toString()	{
		return "In the chest you find " + this.gold + " gold.";
	}
	
}
