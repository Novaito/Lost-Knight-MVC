package fr.l3info.lostknight.items;


/**
 * This class represents a bag in the game
 * 
 * @author Noé
 */
public class Bag extends Container {
	
	/**
	 * This constructor creates a bag with a name and its maximum capacity
	 * 
	 * @param name The name of the bag
	 * @param maxItems The maximum items the bag can contain
	 */
	public Bag(String name, int maxItems) {
		super(name, maxItems);
	}
	
	/**
	 * Implementation of the LookableItem interface
	 */
	@Override
	public void look() {
		StringBuilder delimiter = new StringBuilder();
		String firstStr = "  This is " + this.getName() + ",\033[91;1m it can carry up to " + getMaxItemNumber() + "\033[0;2m";
		delimiter.repeat("═", firstStr.length());
		System.out.println("╔" + delimiter + "╗");
		System.out.println(firstStr);
		System.out.println("╚" + delimiter + "╝");
		System.out.println("  It currently cointains:\n" + this.showItems());
	}
	

}