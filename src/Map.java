import java.util.Scanner;

public class Map {

	public enum commands {
		go, look, checkWatch, exit
	};

	public enum directions {
		north, east, south, west, blank
	};

	private Space[][] map = new Space[101][101];

	private int[] playerPos = { 50, 50 };

	public Map() {

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = new Space();
			}
		}
		
		System.out.println(map[playerPos[0]][playerPos[1]]);
		
		System.out.println(this.look(directions.north));
		System.out.println(this.look(directions.east));
		System.out.println(this.look(directions.south));
		System.out.println(this.look(directions.west) + "\n");
		
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

			System.out.print("What would you like to do? ");

			input = scan.nextLine();

			String[] command = input.split(" ");

			if (command[0].equals(String.valueOf(commands.exit))) {
				System.out.println("Game ends.");
				return false;
			} else if (command[0].equals(String.valueOf(commands.go))) {
				System.out.println("command not yet implimented");
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
			}

		} while (!validInput);
		return true;
	}
}
