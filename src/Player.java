import java.util.ArrayList;

public class Player {
	private int gold = 0;
	private ArrayList<Items.items> inventory = new ArrayList<>();
	
	public int getGold()	{
		return this.gold;
	}
	
	public void updateGold(int moreGold)	{
		this.gold += moreGold;
	}
	
	public String toString()	{
		String out = "";
		out += "You have " + this.gold + " gold.\n";
		if (inventory.size() != 0) {
			out += "In your bags you have:\n";
			for (Items.items str : inventory)	{
				out += str + "\n";
			}
		} else out += "You have nothing in your bags.";
		
		return out;
	}
}
