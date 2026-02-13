package fr.l3info.lostknight.items;

import fr.l3info.lostknight.map.*;

/**
 * This class represents a key in the game
 */
public class Key extends Item {

	private Exit exit;

	/**
	 * This constructor creates a key with a name.
	 * Be careful, no Exit is bound to this key, you'll need to set the exit later
	 * 
	 * @param name The name of the key
	 */
	public Key(String name) {
		super(name);
		this.exit = null;
	}

	/**
	 * This constructor creates a key with its name and the exit bound to it.
	 * 
	 * @param name The name of the key
	 * @param e The exit that can be opened by this key
	 */
	public Key(String name,Exit e) {
		super(name);
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