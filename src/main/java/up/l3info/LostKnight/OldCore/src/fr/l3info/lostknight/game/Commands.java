package fr.l3info.lostknight.game;


/**
 * This class interprets all the commands sent by the user and call the right functions in the game
 * 
 * @author Noé
 */
public class Commands {

	private final Game GAME;

	/**
	 * This contructors create an instance of Commands linked to a game instance
	 * 
	 * @param GAME a reference to the Game instance
	 */
	public Commands(Game GAME) {
		this.GAME = GAME;
	}
	
	/**
	 * Prints a message in the console when an entered command is wrong
	 */
	private void usage() {
		System.out.println("Invalid command, type HELP to get the commands list");
	}
	
	/**
	 * Returns true if there is arguments behind the command String
	 * 
	 * @param command The command typed by the user
	 * @return true if the command has arguments
	 */
	private boolean commandHasArguments(String command) {
		return command.split(" ", 2).length > 1;
	}

	/**
	 * Interprets a command and call the right methods with the right arguments
	 * 
	 * @param command The command typed by the user
	 */
	public void execCommand(String command) {
		// TODO - implement Commands.execCommand
		String[] args = command.split(" ");
		
		Keyword keyword;
		
		try {
			keyword = Keyword.valueOf(args[0].toUpperCase());
		} catch (Exception e) {
			usage();
			return;
		}
		
		
		
		switch (keyword) {
		case Keyword.GO	: {
			if (commandHasArguments(command)) {
				GAME.go(command.split(" ", 2)[1]);				
			}else {
				usage();
			}
			break;
		}
		case Keyword.HELP :{
			helpCommand();
			break;
		}
		case Keyword.LOOK :{
			if(commandHasArguments(command)) {				
				GAME.look(command.split(" ", 2)[1]);
			}else {
				GAME.look("location");
			}
			break;
		}
		case Keyword.TAKE: {
			if(commandHasArguments(command)) {
				this.takeCommand(command.split(" ", 2)[1]);			
			}else {
				usage();
			}
			break;
		}
		case Keyword.QUIT: {
			GAME.quit();
			break;
		}
		case Keyword.USE: {
			if(commandHasArguments(command)) {
				this.useCommand(command.split(" ", 2)[1]);				
			}else {
				usage();
			}
			break;
		}
		case Keyword.TALK	: {
			if (commandHasArguments(command)) {
				GAME.talk(command.split(" ", 2)[1]);				
			}else {
				usage();
			}
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + command);
		}
	}
	
	/**
	 * Displays the help in the console
	 */
	private void helpCommand() {
		System.out.println("Command List:");
		System.out.println("HELP: Show this list");
		System.out.println("GO <Exit>: take an exit and go to the Location it leads to");
		System.out.println("LOOK (<Object>): Describe the object you look. By default describe the room. Special values: hero, inventory, location");
		System.out.println("TAKE <Item> (<Container>): Pickup the item. If no container specified pick it from the ground");
		System.out.println("USE <Item> (<Object>): use an item on the thing you want");
		System.out.println("TALK <Npc> : talk to an NPC");
		System.out.println("QUIT: stop the GAME");
	}
	
	/**
	 * Handle the arguments of the use command and call the use fonction in the game
	 * 
	 * @param args The arguments of the command
	 */
	private void useCommand(String args) {

		String[] argsSep = args.split(" ");
		GAME.use(argsSep[0], (argsSep.length >= 2) ? argsSep[1] : null);
	}
	
	/**
	 * Handle the arguments of the use command and call the use fonction in the game
	 * 
	 * @param args The arguments of the command
	 */
	private void takeCommand(String args) {
		
		String[] argsTab = args.split(" ");
		if (argsTab.length == 1) {
			GAME.take(argsTab[0]);
		}else {
			GAME.take(argsTab[0], argsTab[1]);
		}
		
	}
	
	

}


