package up.l3info.LostKnight.model.core.spells;

import up.l3info.LostKnight.model.core.character.*;

/**
 * This a damage spell class. It is a precise instantiation of a Spell.
 * @author Thomas 
 */

public class DamageSpell extends Spell {

	private final int DAMAGE_AMOUNT;

	/**
	 * This constructor instantiates a damage spell with a name and an amount of damage to inflict 
	 * @param name Spell's name
	 * @param damageAmount The amount of damage to inflict
	 */
	public DamageSpell(String name, int damageAmount) {
		// TODO - implement DamageSpell.DamageSpell
		super(name);
		DAMAGE_AMOUNT = damageAmount;
	}

	/**
	 * Cast spell to the character and inflict the character the damage amount
	 * @param character The character to affect
	 */
	public void castSpell(AttackableCharacter character) {
		character.getAttacked(DAMAGE_AMOUNT);
		if (character instanceof Hero) {
			System.out.println("\nOh dear...\nYou cast on yourself.\n \033[91;1mYOU LOST " + DAMAGE_AMOUNT + "HP\033[0;2m.");
		} else {
			System.out.println("\n\033[92;1m" + character.getName() + " received " + DAMAGE_AMOUNT + " damages.\033[0;2m");
		}
		character.showHP();
	}

	/**
	 * Returns formatted spell's informations
	 */
	public String toString() {
		return "╔╗\n"
			 + "║║ DAMAGE SPELL : " + getName() + "\n"
			 + "║║ Points : \033[0;1m" + DAMAGE_AMOUNT + "\033[0;2m\n"
			 + "║║ This spell will inflict damages to a character that can receive damages.\n"
			 + "╚╝\n";
	}
}