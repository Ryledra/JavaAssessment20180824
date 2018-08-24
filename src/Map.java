import java.util.Scanner;

public class Map {

	public enum commands {
		go, look, check, exit, help, watch, gold
	};

	public enum directions {
		north, east, south, west
	};

	private Space[][] map = new Space[101][101];
	private Space[][] map2 = new Space[101][101];

	private int[] playerPos = { 50, 50 };

	public Map() {

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = new Space();
				map2[i][j] = new Space();
			}
		}
		
		System.out.println(this);
		
		System.out.println(map[playerPos[0]][playerPos[1]]);
		
		System.out.println(this.look(directions.north));
		System.out.println(this.look(directions.east));
		System.out.println(this.look(directions.south));
		System.out.println(this.look(directions.west));
		
		System.out.println("\nOn your wrist you find a strange contraption that seems to glow faintly.\n" +
							"Taking a closer look you decide that the glow indicates a chest hidden nearby.");
		
	}

	public String look(directions direction) {

		String response = "To your " + direction + " you can see a ";
		Boolean container;
		AreaType.areaType terrain;
		switch (direction) {
		case north:
			terrain = map[this.playerPos[0] + 1][this.playerPos[1]].getTerrain();
			container = map[this.playerPos[0] + 1][this.playerPos[1]].getContainer();
			response += terrain;
			break;
		case east:
			terrain = map[this.playerPos[0]][this.playerPos[1] + 1].getTerrain();
			container = map[this.playerPos[0]][this.playerPos[1] + 1].getContainer();
			response += terrain;
			break;
		case south:
			terrain = map[this.playerPos[0] - 1][this.playerPos[1]].getTerrain();
			container = map[this.playerPos[0] - 1][this.playerPos[1]].getContainer();
			response += terrain;
			break;
		case west:
			terrain = map[this.playerPos[0]][this.playerPos[1] - 1].getTerrain();
			container = map[this.playerPos[0]][this.playerPos[1] - 1].getContainer();
			response += terrain;
			break;
		default:
			return "Which direction are you looking?";
		}

		if (container) {
			response += "\n There you can also see a chest.";
		}

		return response;
	}

	public Boolean playerInput() {

		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);

		Boolean validInput = false;

		String input = "";

		do {

			System.out.print("\nWhat would you like to do? ");

			input = scan.nextLine();

			String[] command = input.split(" ");

			if (command[0].equals(String.valueOf(commands.exit))) {
				System.out.println("Game ends.");
				return false;
			} else if (command[0].equals(String.valueOf(commands.go))) {
				if (command[1].equals("")) {
					continue;
				} else if (command[1].equals(String.valueOf(directions.north))) {
					System.out.println(this.go(directions.north));
				} else if (command[1].equals(String.valueOf(directions.east))) {
					System.out.println(this.go(directions.east));
				} else if (command[1].equals(String.valueOf(directions.south))) {
					System.out.println(this.go(directions.south));
				} else if (command[1].equals(String.valueOf(directions.west))) {
					System.out.println(this.go(directions.west));
				}
				validInput = true;
			} else if (command[0].equals(String.valueOf(commands.look))) {
				if (command[1].equals("")) {
					continue;
				} else if (command[1].equals(String.valueOf(directions.north))) {
					System.out.println(this.look(directions.north));
				} else if (command[1].equals(String.valueOf(directions.east))) {
					System.out.println(this.look(directions.east));
				} else if (command[1].equals(String.valueOf(directions.south))) {
					System.out.println(this.look(directions.south));
				} else if (command[1].equals(String.valueOf(directions.west))) {
					System.out.println(this.look(directions.west));
				}
				validInput = true;
			} else if (command[0].equals(String.valueOf(commands.help)))	{
				System.out.println(this);
			} else if (command[0].equals(String.valueOf(commands.check)))	{
				System.out.println(checkWatch());
			} 

		} while (!validInput);
		return true;
	}
	
	public String toString()	{
		return "\n***---*****************---***"
		         + "\nCommands that can be entered:"
		         + "\nlook <direction>"
		         + "\ngo <direction>"
		         + "\ncheck <watch / gold>"
		         + "\nhelp"
		         + "\nexit"
			     + "\n***---*****************---***\n";
	}
	
	public String checkWatch()	{
		String out = "Looking closely at your watch you can see";
		for (int i = 1; i <= 20 ; i++)	{
			if (map[this.playerPos[0]+i][this.playerPos[1]].getContainer())	{
				out += " a chest " + i + "00 yards to the north";
				break;
			} else if (map[this.playerPos[0]-i][this.playerPos[1]].getContainer())	{
				out += " a chest " + i + "00 yards to the south";
				break;
			} else if (map[this.playerPos[0]][this.playerPos[1]+i].getContainer())	{
				out += " a chest " + i + "00 yards to the east";
				break;
			} else if (map[this.playerPos[0]][this.playerPos[1]-i].getContainer())	{
				out += " a chest " + i + "00 yards to the west";
				break;
			}
		}
		if (out.equals("")) out += " no chests nearby";
		return out;
	}
	
	public String go(directions direc)	{
		
		return "not yet implimented";
	}
}
