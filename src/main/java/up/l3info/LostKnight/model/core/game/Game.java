package up.l3info.LostKnight.model.core.game;

import java.util.*;

import up.l3info.LostKnight.model.core.character.*;
import up.l3info.LostKnight.model.core.items.Chest;
import up.l3info.LostKnight.model.core.items.Consumnable;
import up.l3info.LostKnight.model.core.items.Container;
import up.l3info.LostKnight.model.core.items.Item;
import up.l3info.LostKnight.model.core.items.Key;
import up.l3info.LostKnight.model.core.items.Weapon;
import up.l3info.LostKnight.model.core.map.*;
import up.l3info.LostKnight.model.core.miscellaneous.*;

/**
 * This class handle all the game loop and all the game logic
 * 
 * @author Noé
 * 
 * */
public class Game {

	/**
	 * Scanner isn't constant because it has to be modified in the Junit test.
	 * This isn't the right way to do this but it is the only solution I have for now.
	 */
	private static Scanner SCANNER = new Scanner(System.in);
	
	private Hero hero;
	private Commands commands;
	private Location currentLocation;
	private Location spawn;
	private boolean stopGame = false;
	
	/**
	 * This constructor creates an instance of game with a hero and a starting location
	 * 
	 * @param hero The hero already created
	 * @param startLocation The spawn point of the game
	 * */
	public Game(Hero hero, Location startLocation) {
		this.hero = hero;
		commands = new Commands(this);
		currentLocation = startLocation;
		spawn = startLocation;
	}

	/**
	 * Returns the current location  of the hero in the game
	 * 
	 * @return The actual location
	 * 
	 * */
	public Location getCurrentLocation() {
		return this.currentLocation;
	}
	

	/** 
	 * Set the current location of the hero
	 * 
	 * @param newLocation The new Location
	 */
	public void setCurrentLocation(Location newLocation) {
		this.currentLocation = newLocation;
	}
	
	
	/**
	 * Start the game loop. Doesn't stops until the quit command is entered
	 * 
	 */
	public void gameLoop() {
		
		currentLocation.look();
		while(!stopGame) {
			
			gameTurn();
			
		}
		
	}
	
	/**
	 * Run one turn of the game :
	 * Wait for the user to type a command and then executes all the logic
	 */
	public void gameTurn() {
		System.out.println("\n╔══════\n║ \033[94;1mEnter a command :\033[0;2m \n");
		String command = SCANNER.nextLine();
		commands.execCommand(command);
		
		for (GameCharacter gameChar : currentLocation.getCharacters().values()) {
			if(gameChar instanceof Enemy) {
				if(!((AttackableCharacter) gameChar).isDead()) {
					((AttackableCharacter) gameChar).attack(hero, ((Enemy)gameChar).getWeapon()); 
				}
			}
		}
		if(hero.isDead()) {
			StringBuilder delimiter = new StringBuilder();
			String s = "You died";
			delimiter.repeat("═", s.length()+2);
			System.out.println("\033[91m╔" + delimiter + "╗\033[0m");
			System.out.println("  \033[41;1mYou died\033[0;2m");
			System.out.println("\033[91m╚" + delimiter + "╝\033[0m");
			currentLocation = spawn;
			hero.setHp(50);
			hero.feed(50);
		}
		
	}

	/**
	 * Tries to take an Exit.
	 * If it's successful then the location is set to the location the exit leads to.
	 * If it's unsuccessful then print a message and doesn't change the current location
	 * 
	 * @param exitName then name of the exit
	 */
	public void go(String exitName) {
		Exit e = currentLocation.getExit(exitName);
		if(e == null) {
			System.out.println("There is no exit named like this");
			return;
		}
		
		Location newLoc = e.takeExit();
		
		if(newLoc != null) {
			currentLocation = newLoc;
			newLoc.look();
		}else {
			System.out.println("This exit seems locked for now");
		}
		
	}

	/**
	 * Tries to take an Item from a contener.
	 * If there's no container, then pick the item from the ground.
	 * If no item found then no item is added to the inventory.
	 * 
	 * @param itemName The name of the Item
	 * @param container The name of the container (ie. a Chest for instance)
	 */
	public void take(String itemName, String container) {
		// TODO - implement Game.take
		Item c = currentLocation.getItem(container);
		Item item;
		boolean fromChest = false;
		
		if(c!= null && c instanceof Chest) {
			item = ((Chest) c).getItem(itemName);
			fromChest = true;
		}else {
			item = currentLocation.getItem(itemName);
		}
		
		if(item == null) {
			System.out.println("No object named \033[0;1m" + itemName + "\033[0;2m");
			return;
		}
		if((item instanceof Container)) {
			System.out.println("You can't pick up this object");
			return;
		}
		
		boolean success = hero.getInventory().addItem(item);
		
		if(success) {
			System.out.println("\033[92;1m You picked up \033[93;1m" + itemName + "\033[0;2m");
			if(fromChest) {
				((Chest)c).removeItem(itemName);
			}else {				
				currentLocation.takeItem(itemName);
			}
		}
	}
	
	/**
	 * Tries to take an item from the ground.
	 * If no item found then no item is added to the inventory.
	 * 
	 * @param itemName The name of the item
	 */
	public void take(String itemName) {
		this.take(itemName, "");
	}

	/**
	 * Stops the game loop
	 */
	public void quit() {
		this.stopGame = true;
	}

	/**
	 * Tries to use an item from the hero's inventory on a target
	 * 
	 * @param itemName The name of the Item
	 * @param target The target to use the item on
	 */
	public void use(String itemName, String target) {
		// TODO - implement Game.use
		AttackableCharacter tar;
		Item item = hero.getInventory().getItem(itemName);
		if (item == null) {
			System.out.println("You don't have any item named \033[0;1m" + itemName + "\033[0;2m");
			return;
		}
		
		//If it's a key
		if(item instanceof Key) {
			Exit exit = currentLocation.getExit(target);
			if(exit == null) {
				System.out.println("There is no " + target + " in this room");
				return;
			}else {
				if(exit instanceof LockedExit) {
					if(((Key)item).getExit() == exit) {						
						((LockedExit) exit).unlock();
						System.out.println("\033[92;1mUnlocking \033[0;2m" + target);
					}else {
						System.out.println("It doesn't seems to open " + target);
					}
					
				}else {
					System.out.println("Can't use this on " + target);
				}
			}
		}
		
		if(target == null || target.toLowerCase().equals("hero")) {
			tar = hero;
		}else {
			GameCharacter character = currentLocation.getCharacter(target);
			if(character == null) {
				System.out.println("There is no " + target + " in this room");
				return;
			}
			if(character instanceof Npc) {
				System.out.println("You can't use " + itemName + " on " + target);
				return;
			}
			tar = (AttackableCharacter) character;
			
		}
		
		if(item instanceof Weapon) {
			((Weapon) item).use(hero, tar);
		}else if (item instanceof Consumnable) {
			((Consumnable) item).use(hero, tar);
		}
		
	}


	/**
	 * Tries to look an object if it implements the interface LookableObject
	 * 
	 * @param s The name of the object
	 */
	public void look(String s) {
		LookableObject lo;
		if(s.toLowerCase().equals("hero")) { 
			hero.look();
		}else if(s.toLowerCase().equals("location")){ 
			currentLocation.look();
		}else if(s.toLowerCase().equals("inventory")) {
			hero.getInventory().look();
		} else{
			lo = currentLocation.getItem(s);
			if(lo == null) {
				lo = currentLocation.getExit(s);
				if(lo == null) {
					System.out.println("Nothing to look");
					return;
				}
			}

			lo.look();
		}
	}

	/**
	 * Speak to the characterName if he is in the location
	 * 
	 * @param characterName The character to speak to
	 */
	public void talk(String characterName) {
		GameCharacter gc;
		gc = this.currentLocation.getCharacter(characterName);
		if(gc instanceof Npc){
			gc.discuss(hero);
		}else {
			System.out.println("There is nobody named \033[0;1m" + characterName + "\033[0;2m here");
		}
	}







		
	public void renderGameHeader() {
		System.out.println(
        		
        		
    		    "\033[0;1m╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════╗"
    	    + "\n║\033[95;2m                                                                                                           \033[0;1m║"
    		+ "\n║\033[95;2m  888                        888                      888    d8P           d8b          888      888       \033[0;1m║"
    		+ "\n║\033[95;2m  888                        888                      888   d8P            Y8P          888      888       \033[0;1m║"
    		+ "\n║\033[95;2m  888                        888                      888  d8P                          888      888       \033[0;1m║"
    		+ "\n║\033[95;2m  888       .d88b.  .d8888b  888888                   888d88K     88888b.  888  .d88b.  88888b.  888888    \033[0;1m║"
    		+ "\n║\033[95;2m  888      d88\"\"88b 88K      888                      8888888b    888 \"88b 888 d88P\"88b 888 \"88b 888       \033[0;1m║"
    		+ "\n║\033[95;2m  888      888  888 \"Y8888b. 888                      888  Y88b   888  888 888 888  888 888  888 888       \033[0;1m║"
    		+ "\n║\033[95;2m  888      Y88..88P      X88 Y88b.                    888   Y88b  888  888 888 Y88b 888 888  888 Y88b.     \033[0;1m║"
    		+ "\n║\033[95;2m  88888888  \"Y88P\"   88888P'  \"Y888                   888    Y88b 888  888 888  \"Y88888 888  888  \"Y888    \033[0;1m║"
    		+ "\n║\033[95;2m                                                                                    888                    \033[0;1m║"
    		+ "\n║\033[95;2m                                                                               Y8b d88P                    \033[0;1m║"
    		+ "\n║\033[95;2m                                                                                \"Y88P\"                     \033[0;1m║"
    		+ "\n╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════╝\033[0;2m"
		);
	}



}