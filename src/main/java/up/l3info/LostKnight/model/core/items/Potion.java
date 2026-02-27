package up.l3info.LostKnight.model.core.items;

import up.l3info.LostKnight.model.core.character.*;
import up.l3info.LostKnight.model.core.spells.*;

/**
 * This class represents a potion in the game. It extends Consumnable.
 * 
 * @author Noé
 */
public class Potion extends Consumnable {

	Spell spell;

	/**
	 * This constructor creates a potion with its name, a spell, a description, posX and posY

	 * 
	 * @param name The name of the potion
	 * @param spell The spell the potion applies
	 * @param description The description of the potion
	 * @param posX X position
	 * @param posY Y position
	 */
	public Potion(String name, Spell spell, String description,int posX, int posY) {
		super(name, description,posX,posY);
		this.spell = spell;
	}
	
	/**
	 * This constructor creates a potion with its name, a spell, a default description, posX and posY
	 * 
	 * @param name The name of the potion
	 * @param spell The spell the potion applies
	 * @param posX X position
	 * @param posY Y position
	 */
	public Potion(String name, Spell spell,int posX, int posY) {
		super(name,posX,posY);
		this.spell = spell;
	}

	/**
	 * Uses the food. Removes it from the character's inventory and add applies the spell to the target
	 * 
	 * @param character The character that uses the food
	 * @param target The character to use the food on
	 */
	public void use(AttackableCharacter character, AttackableCharacter target) {
		spell.castSpell(target);
		if(character instanceof Hero) {			
			((Hero)character).getInventory().removeItem(this.getName());
		}
		
	}

	/**
	 * The implementation of the LookableItem interface
	 */
	@Override
	public void look() {
		System.out.println(spell);
		
	}


}