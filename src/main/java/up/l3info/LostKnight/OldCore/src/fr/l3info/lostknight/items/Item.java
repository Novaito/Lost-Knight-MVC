package fr.l3info.lostknight.items;

import fr.l3info.lostknight.miscellaneous.*;

/**
 * This abstract class represents the items in the games
 * 
 * @author Noé
 */
public abstract class Item implements LookableObject {

	private String name;
	private String description;
	private static final String DEFAULT_DESC = "This is a %s";

	/**
	 * This constructor creates an Item with its name and a description
	 * 
	 * @param name The name of the item
	 * @param desc The description of the item
	 */
	public Item(String name, String desc) {
		this.name = name;
		if(desc == null) {
			this.description = String.format(DEFAULT_DESC, name);
		}else {
			description = desc;
		}
	}
	
	/**
	 * This constructor creates an Item with its name. It uses the default description
	 * 
	 * @param name The name of the item
	 */
	public Item(String name) {
		this(name, null);
	}

	/**
	 * Returns the name of the Item
	 * 
	 * @return the name of the Item
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the description of the Item
	 * 
	 * @return the description of the Item
	 */	
	public String getDescription() {
		return description;
	}
	
	/**
	 * Implementation of the LookableItem interface
	 */
	@Override
	public abstract void look();
	
	/**
	 * give the name of the Item
	 */
	@Override
	public String toString() {
		return "\033[93;1m" + getName() + "\033[0;2m";
	}

}