
public class Player {
	private int gold = 0;
	
	public int getGold()	{
		return this.gold;
	}
	
	public void updateGold(int moreGold)	{
		this.gold += moreGold;
	}
	
	public String toString()	{
		return "You have " + this.gold + " gold.";
	}
}
