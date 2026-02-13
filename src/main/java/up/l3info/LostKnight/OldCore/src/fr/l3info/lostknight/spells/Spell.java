package fr.l3info.lostknight.spells;

import fr.l3info.lostknight.character.*;

/**
 * The abstract class of spells. It is contained in Potion item.
 * @see Potion
 * @author Thomas
 */

public abstract class Spell {

	private final String NAME;

	/**
	 * This constructor set the name of the spell. 
	 * @param name Spell's name
	 */
	public Spell(String name) {
		NAME = name;
	}

	/**
	 * Returns the name of the spell
	 * @return Spell's name
	 */
	public String getName() {
		return NAME;
	}

	/**
	 * Permit to cast a sort of spell to a character
	 * @param character The character targeted
	 * @see DamageSpell
	 * @see HealSpell
	 * @see StaminaSpell
	 * @see StarvationSpell
	 * @see WeaknessSpell
	 */
	public abstract void castSpell(AttackableCharacter character);
	
	/**
	 * Returns the formatted name of the spell
	 * @return Formatted name
	 */
	@Override
	public String toString() {
		return "\033[95;1m" + getName() + "\033[0;2m";
	}

}