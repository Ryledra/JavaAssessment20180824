
public class Map {

	public enum  commands {
		go, look, checkWatch, exit
	};
	
	public enum directions {
		north, east, south, west
	};
	
	private Space[][] map = new Space[101][101];
	
	private int[] playerPos = {50,50};
	
	public Map()	{

		for (int i = 0; i < map.length; i++)	{
			for (int j = 0; j < map[0].length; j++)	{
				map[i][j] = new Space();
			}
		}
	}
	
	public String look(directions direction)	{
		
		String response = "To your " + direction + " you can see a ";
		Boolean container;
		AreaType.areaType terrain;
		switch (direction)	{
		case north:
			terrain = map[this.playerPos[0]+1][this.playerPos[1]].getTerrain();
			container = map[this.playerPos[0]+1][this.playerPos[1]].getContainer();
			response += terrain;
			break;
		case east:
			terrain = map[this.playerPos[0]][this.playerPos[1]+1].getTerrain();
			container = map[this.playerPos[0]][this.playerPos[1]+1].getContainer();
			response += terrain;
			break;
		case south:
			terrain = map[this.playerPos[0]-1][this.playerPos[1]].getTerrain();
			container = map[this.playerPos[0]-1][this.playerPos[1]].getContainer();
			response += terrain;
			break;
		case west:
			terrain = map[this.playerPos[0]][this.playerPos[1]-1].getTerrain();
			container = map[this.playerPos[0]][this.playerPos[1]-1].getContainer();
			response += terrain;
			break;
		default:
			return "You see nothing";
		}
		
		if (container)	{
			response += "\n There you can also see a chest.";
		}
		
		return response;
	}
}
