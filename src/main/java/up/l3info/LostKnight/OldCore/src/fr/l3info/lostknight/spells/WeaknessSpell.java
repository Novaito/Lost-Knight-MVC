package fr.l3info.lostknight.spells;

import fr.l3info.lostknight.character.*;

/**
 * This a weakness spell class. It is a precise instantiation of a Spell.
 * @author Thomas 
 */

public class WeaknessSpell extends Spell {

	private int WEAKNESS_AMOUNT;

	/**
	 * This constructor instantiates a weakness spell with a name and a weakness amount
	 * @param name Spell's name
	 * @param weaknessAmount Weakness amount to affect (negative or positive)
	 */
	public WeaknessSpell(String name, int weaknessAmount) {
		super(name);
		WEAKNESS_AMOUNT = weaknessAmount;
	}

	/**
	 * Cast spell to the character and affect the character the weakness amount
	 * @param character The character to affect
	 */
	public void castSpell(AttackableCharacter character) {
		character.addWeakness(WEAKNESS_AMOUNT);
		if (character instanceof Hero) {
			System.out.print("\nOh nooo you're getting weak.\nFor each perceived damage, ");
		}
		character.showWeakness();
	}
	
	/**
	 * Returns formatted spell's informations
	 */
	public String toString() {
		return "╔╗\n"
			 + "║║ WEAKNESS SPELL : " + getName() + "\n"
			 + "║║ Points : \033[0;1m" + WEAKNESS_AMOUNT + "\033[0;2m\n"
			 + "║║ This spell will make a character stronger or weaker.\n"
			 + "╚╝\n";
	}
}