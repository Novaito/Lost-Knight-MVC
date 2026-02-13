package fr.l3info.lostknight.items;

/**
 * This class represent a chest in a game
 * 
 * @author Noé
 */
public class Chest extends Container {

	/**
	 * This constructor creates a chest with its name and its maximum capacity
	 * 
	 * @param name The name of the chest
	 * @param maxItems The maximum items it can contain
	 */
	public Chest(String name, int maxItems) {
		super(name, maxItems);
	}

	/**
	 * Implementation of the LookableObject interface
	 */
	@Override
	public void look() {
		System.out.println("This is a chest");
		
	}
	
	

}