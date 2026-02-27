package up.l3info.LostKnight.model.core.items;

/**
 * This class represent a chest in a game
 * 
 * @author Noé
 */
public class Chest extends Container {

	/**
	 * This constructor creates a chest with its name, its maximum capacity, posX and posY
	 * 
	 * @param name The name of the chest
	 * @param maxItems The maximum items it can contain
	 * @param posX X position
	 * @param posY Y position
	 */
	public Chest(String name, int maxItems,int posX, int posY) {
		super(name, maxItems,posX,posY);
	}

	/**
	 * Implementation of the LookableObject interface
	 */
	@Override
	public void look() {
		System.out.println("This is a chest");
		
	}
	
	

}