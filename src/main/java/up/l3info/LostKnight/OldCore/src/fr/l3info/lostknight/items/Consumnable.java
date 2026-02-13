package fr.l3info.lostknight.items;

import fr.l3info.lostknight.character.*;

/**
 * This class represents Items that can be consumed by the attackable characters
 * 
 * @author Noé
 */
public abstract class Consumnable extends Item {

	/**
	 * This constructor creates an Consumnable item with its name and a description
	 * 
	 * @param name The name of the item
	 * @param desc The description of the item
	 */
	public Consumnable(String name, String description) {
		super(name, description);
	}

	/**
	 * This constructor creates an Consumnable item with its name. It uses the default description
	 * 
	 * @param name The name of the item
	 */
	public Consumnable(String name) {
		super(name);
	}

	/**
	 * Abstract method to use the item
	 * 
	 * @param character the character that uses it
	 * @param target the character to use it on
	 */
	public abstract void use(AttackableCharacter character, AttackableCharacter target);

}