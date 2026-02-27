package up.l3info.LostKnight.model.core.items;

import up.l3info.LostKnight.model.core.map.*;

/**
 * This class represents a key in the game
 */
public class Key extends Item {

	private Exit exit;

	/**
	 * This constructor creates a key with a name, posX and posY
	 * Be careful, no Exit is bound to this key, you'll need to set the exit later
	 * 
	 * @param name The name of the key
	 * @param posX X position
	 * @param posY Y position
	 */
	public Key(String name,int posX, int posY) {
		super(name,posX,posY);
		this.exit = null;
	}

	/**
	 * This constructor creates a key with its name and the exit bound to it, posX and posY.
	 * 
	 * @param name The name of the key
	 * @param e The exit that can be opened by this key
	 * @param posX X position
	 * @param posY Y position
	 */
	public Key(String name,Exit e,int posX, int posY) {
		super(name,posX,posY);
		this.exit = e;
	}

	/**
	 * Set the exit the key can open
	 * 
	 * @param e The exit the key can open
	 */
	public void setExit(Exit e){
		this.exit = e ;
	}
	
	/**
	 * Returns the exit the key can open
	 * 
	 * @return the exit the key can open
	 */
	public Exit getExit() {
		return exit;
	}

	/**
	 * Implementation of the LookableObject interface
	 */
	@Override
	public void look() {
		// TODO Auto-generated method stub
		System.out.println("This is a " + this.getName());
		
	}

}