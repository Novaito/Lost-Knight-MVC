package up.l3info.LostKnight.model.core.map;

import up.l3info.LostKnight.model.core.character.GameCharacter;
import up.l3info.LostKnight.model.core.items.*;
import up.l3info.LostKnight.model.core.miscellaneous.CaseInsensitiveHashMap;
import up.l3info.LostKnight.model.core.miscellaneous.LookableObject;

import java.util.Map;

/**
 * This class represents a Location in the game
 * 
 * @author Noé
 */
public class Location implements LookableObject{

	private Map<String, Item> items;
	private Map<String, GameCharacter> characters;
	private Map<String, Exit> exits;
	private String name;
	private String description;
	
	/**
	 * This constructor creates a location with its name and its description
	 * 
	 * @param name The name of the location
	 * @param description The description of the location
	 */
	public Location(String name , String description) {
		this.name = name;
		this.description = description;
		this.exits = new CaseInsensitiveHashMap<Exit>();
		this.items = new CaseInsensitiveHashMap<Item>();
		this.characters = new CaseInsensitiveHashMap<GameCharacter>();
	}
	

	/**
	 * Returns the Exit with the name corresponding. Null if not match.
	 * 
	 * @param exitName The name of the exit
	 * @return The Exit with a name corresponding. Null if no match.
	 */
	public Exit getExit(String exitName) {
		Exit exit = exits.get(exitName);
		return exit;
	}
	
	/**
	 * Returns the name of this location 
	 * 
	 * @return the name of the location
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the Item in this location with the name corresponding. Null if not match.
	 * 
	 * @param itemName The name of the item
	 * @return The item with the name corresponding. Null if no match
	 */
	public Item getItem(String itemName) {
		return this.items.get(itemName);
	}

	/**
	 * Returns the Character in this location with the name corresponding. Null if not match.
	 * 
	 * @param charName The name of the character
	 * @return The character with the name corresponding. Null if not match
	 */
	public GameCharacter getCharacter(String charName) {
		return this.characters.get(charName);
	}
	
	/**
	 * Returns the Map of all the characters in this location
	 * 
	 * @return The Map of all the characters in this location.
	 */
	public Map<String, GameCharacter> getCharacters(){
		return this.characters;
	}

	/**
	 * Adds a character to the location
	 * 
	 * @param charAdd The character to add
	 */
	public void addCharacters(GameCharacter charAdd){
		characters.put(charAdd.getName() , charAdd);
	}

	/**
	 * Adds an exit to this room
	 * 
	 * @param e The Exit to add
	 */
	public void addExit(Exit e){
		exits.put(e.getName() , e);
	}
	
	
	/**
	 * Adds an item to this room
	 * 
	 * @param itemAdd The Item to add
	 */
	public void addItem(Item itemAdd){
		items.put(itemAdd.getName() , itemAdd);
	}

	public Item takeItem(String s){
		Item i = items.get(s);
		items.remove(s);
		return i;
	}

	/**
	 * Implementation the look interface
	 */
	@Override
	public void look() {
		StringBuilder delimiter = new StringBuilder();
		delimiter.repeat("═", getName().length() + 2);
		System.out.println(
				"╔" + delimiter + "╗"
				+ "\n  \033[96;1m" + getName() + "\033[0;2m"
				+ "\n╚" + delimiter +"╝"
				);
		System.out.println(description);
		if(! items.isEmpty()) {	
			System.out.println("\n There are \033[0;1mitems\033[0;2m on the floor: \n  ╔");
			for (Item item : items.values()) {
				System.out.println("  ║ " + item);
			}
			System.out.println("  ╚");
		}
		if (!exits.isEmpty()) {
			System.out.println("\n There are \033[0;1mexits\033[0;2m you can go through : \n  ╔");
			for (Exit exit : exits.values()) {
				System.out.println("  ║ " + exit);
			}
			System.out.println("  ╚");
		}
		if (!characters.isEmpty()) {
			System.out.println("\n There are \033[0;1mcharacters\033[0;2m present : \n  ╔");
			for (GameCharacter gCharacter : characters.values()) {
				System.out.println(gCharacter);
			}
			System.out.println("  ╚");
		}
	}

}