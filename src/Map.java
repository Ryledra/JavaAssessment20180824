import java.util.Scanner;

public class Map {

	public enum commands {
		// commands for input
		// {go, look, check, exit, help, open} primary commands
		// {watch, gold} secondary commands
		go, look, check, exit, help, open, watch, gold
	};

	public enum directions {
		// cardinal directions to follow the commands <look / go>
		north, east, south, west
	};

	// main player map
	private Space[][] map = new Space[101][101];
	//secondary map for psudo-infinite map space
	private Space[][] map2 = new Space[101][101];

	// keep track of player
	private int[] playerPos = { 0, 0 };
	private Player player = new Player();
	
	public Map() {

		// initialise the maps
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = new Space();
				map2[i][j] = new Space();
			}
		}
		
		// print to console help function
		System.out.println(this);
		
		// print to console description of player's current
		// location and the areas around them
		System.out.println(playerUpdate());
		
		System.out.println("\nOn your wrist you find a strange contraption that seems to glow faintly.\n" +
							"Taking a closer look you decide that the glow must indicate a chest hidden nearby.");
		
	}

	public String look(directions direction) {
		// function returns a string describing the terrain of the next space in direction requested
		String response = "To your " + direction + " you can see a ";
		Boolean container;
		AreaType.areaType terrain;
		switch (direction) {
		case north:
			if (playerPos[0] == 100)	{
				terrain = map2[0][this.playerPos[1]].getTerrain();
				container = map2[0][this.playerPos[1]].getContainer();
			}
			else {
				terrain = map[this.playerPos[0] + 1][this.playerPos[1]].getTerrain();
				container = map[this.playerPos[0] + 1][this.playerPos[1]].getContainer();
			}
			response += terrain;
			break;
		case east:
			if (playerPos[1] == 100)	{
				terrain = map2[this.playerPos[0]][0].getTerrain();
				container = map2[this.playerPos[0]][0].getContainer();
			}
			else {
				terrain = map[this.playerPos[0]][this.playerPos[1] + 1].getTerrain();
				container = map[this.playerPos[0]][this.playerPos[1] + 1].getContainer();
			}
			response += terrain;
			break;
		case south:
			if (playerPos[0] == 0)	{
				terrain = map2[100][this.playerPos[1]].getTerrain();
				container = map2[100][this.playerPos[1]].getContainer();
			}
			else {
				terrain = map[this.playerPos[0] - 1][this.playerPos[1]].getTerrain();
				container = map[this.playerPos[0] - 1][this.playerPos[1]].getContainer();
			}
			response += terrain;
			break;
		case west:
			if (playerPos[1] == 100)	{
				terrain = map2[this.playerPos[0]][0].getTerrain();
				container = map2[this.playerPos[0]][0].getContainer();
			}
			else {
				terrain = map[this.playerPos[0]][100].getTerrain();
				container = map[this.playerPos[0]][100].getContainer();
			}
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
		// method to take player input and call selected commands
		// returns true unless player enters "exit"
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
			} else if (command[0].equals(String.valueOf(commands.check)) 
					&& command[1].equals(String.valueOf(commands.watch)))	{
				System.out.println(checkWatch());
			} else if (command[0].equals(String.valueOf(commands.check)) 
					&& command[1].equals(String.valueOf(commands.gold)))	{
				System.out.println(player);
			} else if (command[0].equals(String.valueOf(commands.open)))	{
				System.out.println(openChest());
			}

		} while (!validInput);
		return true;
	}
	
	public String toString()	{
		// prints the valid commands as a help function
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
		// returns a string stating where the nearest chest is
		// currently only checks cardinal directions
		String out = "Looking closely at your watch it looks like there might be";
		for (int i = 1; i <= 20 ; i++)	{
			//north
			if (this.playerPos[0] + i > 100 && map2[i - (100 - this.playerPos[0])][this.playerPos[1]].getContainer())	{
				out += " a chest " + i + "00 yards to the north";
				break;
			} else if (map[this.playerPos[0]+i][this.playerPos[1]].getContainer())	{
				out += " a chest " + i + "00 yards to the north";
				break;
			}
			
			// south
			else if (this.playerPos[0] - i < 0 && map2[(100 - this.playerPos[0])][this.playerPos[1]].getContainer())	{
				out += " a chest " + i + "00 yards to the south";
				break;
			} else if (map[this.playerPos[0]-i][this.playerPos[1]].getContainer())	{
				out += " a chest " + i + "00 yards to the south";
				break;
			} 
			
			// east
			else if (this.playerPos[1] + i > 100 && map2[this.playerPos[0]][i - (100 - this.playerPos[1])].getContainer())	{
				out += " a chest " + i + "00 yards to the east";
				break;
			} else if (map[this.playerPos[0]][this.playerPos[1]+i].getContainer())	{
				out += " a chest " + i + "00 yards to the east";
				break;
			} 
			
			// west
			else if (this.playerPos[1] - i < 0 && map2[this.playerPos[0]][(100 - this.playerPos[1])].getContainer())	{
				out += " a chest " + i + "00 yards to the east";
				break;
			} else if (map[this.playerPos[0]][this.playerPos[1]-i].getContainer())	{
				out += " a chest " + i + "00 yards to the west";
				break;
			}
		}
		if (out.equals("")) out = "Looking closely at your watch you see no chests nearby";
		return out;
	}
	
	public String go(directions direc)	{
		// moves the player in the desired direction
		// returns a string from playerUpdate()
		switch (direc)	{
		case north:
			if (this.playerPos[0] + 1 > 100)	{
				updateMaps(direc);
				this.playerPos[0] = 0;
			} else this.playerPos[0] += 1;
			return playerUpdate();
		case east:
			if (this.playerPos[1] + 1 > 100)	{
				updateMaps(direc);
				this.playerPos[1] = 0;
			} else this.playerPos[1] += 1;
			return playerUpdate();
		case south:
			if (this.playerPos[0] - 1 < 0)	{
			updateMaps(direc);
			this.playerPos[0] = 100;
			} else this.playerPos[0] -= 1;
			return playerUpdate();
		case west:
			if (this.playerPos[1] - 1 < 0)	{
			updateMaps(direc);
			this.playerPos[1] = 100;
			} else this.playerPos[1] -= 1;
			return playerUpdate();
		default:
			return "not a valid direction";
		}
	}
	
	public String playerUpdate()	{
		// returns string of player surroundings
		return map[playerPos[0]][playerPos[1]] + "\n\n" 
				+ this.look(directions.north) + "\n" 
				+ this.look(directions.east) + "\n" 
				+ this.look(directions.south) + "\n" 
				+ this.look(directions.west);
	}
	
	public void updateMaps(directions direc)	{
		// when player reaches edge of map replaces with 
		// map2, reinitialises map2 for psudo-infinite play
		this.map = this.map2;
		this.map2 = new Space[101][101];
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map2[i][j] = new Space();
			}
		}
	}
	
	public String openChest()	{
		// allows player to open chest returns string with details
		// adds gold found to player's inventory 
		if (map[playerPos[0]][playerPos[1]].getChest() == null)	{
			return "There is no chest here";
		}
		int gold = map[playerPos[0]][playerPos[1]].getChest().getGold();
		player.updateGold(gold);
		map[playerPos[0]][playerPos[1]].removeChest();
		return "You open the chest to find " + gold + " gold.\n" +
				"You put your new gold into your pockets.\n" + this.player;
	}
}
