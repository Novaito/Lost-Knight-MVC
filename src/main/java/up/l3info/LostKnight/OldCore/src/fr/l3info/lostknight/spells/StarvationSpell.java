package fr.l3info.lostknight.spells;

import fr.l3info.lostknight.character.*;

/**
 * This a starvation spell class. It is a precise instantiation of a Spell.
 * @author Thomas 
 */

public class StarvationSpell extends Spell {

	private final int STARVE_AMOUNT;

	/**
	 * This constructor instantiates a starvation spell with a name and a starvation amount to affect
	 * @param name Spell's name
	 * @param starveAmount Starvation amount to affect (negative or positive)
	 */
	public StarvationSpell(String name, int starveAmount) {
		super(name);
		STARVE_AMOUNT = starveAmount;
	}

	/**
	 * Cast spell to the character and affect the character the weakness amount
	 * @param character The character to affect
	 */
	public void castSpell(AttackableCharacter character) {
		if (character instanceof Hero) {
			((Hero) character).feed(STARVE_AMOUNT);
			System.out.println("\nYour belly got filled up !\n\033[92;1mYOU GAIN " + STARVE_AMOUNT + " OF STARVATION.\033[0;2m");
			((Hero) character).showHunger();
		} else {
			System.out.println("\nYour are so generous to feed " + character.getName() + ", but you get absolutely nothing back.\nYou lost your spell.");
		}
	}

	/**
	 * Returns formatted spell's informations
	 */
	public String toString() {
		return "╔╗\n"
			 + "║║ STARVATION SPELL : " + getName() + "\n"
			 + "║║ Points : \033[0;1m" + STARVE_AMOUNT + "\033[0;2m\n"
			 + "║║ This spell will make you less hungry.\n"
			 + "╚╝\n";
	}
}