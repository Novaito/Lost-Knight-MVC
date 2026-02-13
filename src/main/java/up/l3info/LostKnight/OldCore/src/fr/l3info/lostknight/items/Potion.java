package fr.l3info.lostknight.items;

import fr.l3info.lostknight.spells.*;
import fr.l3info.lostknight.character.*;

/**
 * This class represents a potion in the game. It extends Consumnable.
 * 
 * @author Noé
 */
public class Potion extends Consumnable {

	Spell spell;

	/**
	 * This constructor creates a potion with its name, a spell and a description 

	 * 
	 * @param name The name of the potion
	 * @param spell The spell the potion applies
	 * @param description The description of the potion
	 */
	public Potion(String name, Spell spell, String description) {
		super(name, description);
		this.spell = spell;
	}
	
	/**
	 * This constructor creates a potion with its name, a spell and a default description
	 * 
	 * @param name The name of the potion
	 * @param spell The spell the potion applies
	 */
	public Potion(String name, Spell spell) {
		super(name);
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