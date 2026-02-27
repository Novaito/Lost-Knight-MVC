package up.l3info.LostKnight.model.core.items;


/**
 * This class represents a bag in the game
 * 
 * @author Noé
 */
public class Bag extends Container {
	
	/**
	 * This constructor creates a bag with a name, its maximum capacity, posX and posY
	 * 
	 * @param name The name of the bag
	 * @param maxItems The maximum items the bag can contain
	 * @param posX X position
	 * @param posY Y position
	 */
	public Bag(String name, int maxItems,int posX, int posY) {
		super(name, maxItems,posX,posY);
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