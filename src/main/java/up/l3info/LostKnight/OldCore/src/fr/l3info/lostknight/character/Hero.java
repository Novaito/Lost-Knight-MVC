package fr.l3info.lostknight.character;


import fr.l3info.lostknight.items.*;

/**
 * This class represents a specific character : the Hero.
 * 
 * @author Thomas
 */

public class Hero extends AttackableCharacter {

	private static final int MAX_STAMINA = 100;
	private int stamina;
	private static final int MAX_HUNGER = 100;
	private int hunger;
	private static final int DEFAULT_MAX_INVENTORY_ITEMS = 20;
	private Bag bag;

	/**
	 * This constructor instantiates a hero with his name, the max HEAL POINTS he will have, and his dialog at the end.
	 * @param name His name
	 * @param hp The max of heal points
	 * @param dialog His dialog
	 */
	public Hero(String name, int hp, String dialog) {
		super(name, hp, dialog);
		stamina = MAX_STAMINA;
		hunger = MAX_HUNGER / 2;
		bag = new Bag("Hero's Bag", DEFAULT_MAX_INVENTORY_ITEMS);
	}

	/**
	 * Prints the current state of the inventory. 
	 * @see Bag
	 */
	public void showInventory() {
		bag.look();
	}

	/**
	 * Returns the bag carried by the Hero.
	 * @return The bag
	 */
	public Bag getInventory() {
		return bag;
	}

	/**
	 * Add an item in the bag of the Hero.
	 * @param item The item to add in
	 */
	public void addItem(Item item) {
		if (!(item instanceof Container)) {
			bag.addItem(item);	
		} else {
			System.out.println("Cannot put a container in a " + bag.getName() + ".");
		}
	}
	
	/**
	 * Returns the hero's stamina level.  
	 * @return Stamina amount
	 */
	public int getStamina() {
		return stamina;
	}
	
	/**
	 * Add a stamina amount to the hero's stamina level.
	 * @param stamina_amount The amount of stamina to add (could be negative or positive)
	 */
	public void addStamina(int stamina_amount) {
		stamina += stamina_amount;
		if (stamina > MAX_STAMINA) {
			stamina = MAX_STAMINA;
		}
		if (stamina < 0) {
			stamina = 0;
		}
	}
	
	/**
	 * Prints the Hero's current statistics : name, hp, stamina, starvation, capacity used in the bag.
	 */
	@Override
	public void look() {
		System.out.println("╔╗\n"
						 + "║║ NAME : " + toString() + "\n"  
						 + "║║ HP : \033[0;1m" + getHp() + "\033[0;2m\n"
						 + "║║ STAMINA : \033[0;1m" + getStamina() + "\033[0;2m\n"
						 + "║║ SARVATION : \033[0;1m" + getHunger() + "\033[0;2m\n"
						 + "║║ BAG : \033[0;1m" + bag.getItemNumber() + "\033[0;2m of \033[0;1m" + bag.getMaxItemNumber() + "\033[0;2m\n" 
						 + "╚╝\n");
	}

	/**
	 * Permit to discuss to another character.
	 * @param discuss The character to discuss.
	 */
	@Override
	public void discuss(GameCharacter character) {
		if (character == null) {
			System.out.println(getName() + " : " + getDialog());			
		} else {
			character.discuss(this);			
		}
	}
	
	/**
	 * Feed the hero with an amount of starvation. 
	 * @param amount Starvation amount feeding (could be negative or positive)
	 */
	public void feed(int amount) {
		hunger += amount;
		if(hunger > MAX_HUNGER) {
			hunger = MAX_HUNGER;
		}
		if (hunger < 0) {
			hunger = 0;
		}
	}
	
	/**
	 * Returns the Hero's starvation level.
	 * @return Starvation amount
	 */
	public int getHunger() {
		return hunger;
	}
	
	/**
	 * Decrease the Hero's starvation level.
	 * @param amount Decrease amount
	 */
	public void removeHunger(int amount) {
		hunger -= amount; //TODO gerer la faim
	}
	
	/**
	 * Prints the Hero's starvation level.
	 */
	public void showHunger() {
		System.out.println("\033[0;1mYou have " + getHunger() + " food points out of " + MAX_HUNGER + ".\033[0;2m");
	}

	/**
	 * Prints the Hero's stamina level.
	 */
	public void showStamina() {
		System.out.println("\033[0;1mYou have " + getStamina() + " stamina points out of " + MAX_STAMINA + ".\033[0;2m");
	}
}