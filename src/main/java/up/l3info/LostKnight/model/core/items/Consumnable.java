package up.l3info.LostKnight.model.core.items;

import up.l3info.LostKnight.model.core.character.*;

/**
 * This class represents Items that can be consumed by the attackable characters
 * 
 * @author Noé
 */
public abstract class Consumnable extends Item {

	/**
	 * This constructor creates an Consumnable item with its name, a description, posX and posY
	 * 
	 * @param name The name of the item
	 * @param desc The description of the item
	 * @param posX X position
	 * @param posY Y position
	 */
	public Consumnable(String name, String desc,int posX, int posY) {
		super(name, desc,posX, posY);
	}

	/**
	 * This constructor creates an Consumnable item with its name,posX and posY. It uses the default description
	 * 
	 * @param name The name of the item
	 * @param posX X position
	 * @param posY Y position
	 */
	public Consumnable(String name,int posX, int posY) {
		super(name,posX,posY);
	}

	/**
	 * Abstract method to use the item
	 * 
	 * @param character the character that uses it
	 * @param target the character to use it on
	 */
	public abstract void use(AttackableCharacter character, AttackableCharacter target);

}