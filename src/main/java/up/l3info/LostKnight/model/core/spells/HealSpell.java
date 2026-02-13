package up.l3info.LostKnight.model.core.spells;

import up.l3info.LostKnight.model.core.character.*;

/**
 * This a heal spell class. It is a precise instantiation of a Spell.
 * @author Thomas 
 */

public class HealSpell extends Spell {

	private final int HEAL_AMOUNT;

	/**
	 * This constructor instantiates a heal spell with a name and a heal amount to affect
	 * @param name Spell's name
	 * @param healAmount Heal amount to affect
	 */
	public HealSpell(String name, int healAmount) {
		super(name);
		HEAL_AMOUNT = healAmount;
	}

	/**
	 * Cast spell to the character and affect the character the weakness amount
	 * @param character The character to affect
	 */
	public void castSpell(AttackableCharacter character) {
		character.regenHP(HEAL_AMOUNT);
		if (character instanceof Hero) {
			System.out.println("\nHere you go, some HP will make you feel better.\n\033[92;1mYOU GAIN " + HEAL_AMOUNT + "HP.\033[0;2m");
		} else {
			System.out.println("\nOh dear...\nYou gave him the chance to recover some life points.");
		}
		character.showHP();
	}
	
	/**
	 * Returns formatted spell's informations
	 */
	public String toString() {
		return "╔╗\n"
			 + "║║ HEAL SPELL : " + getName() + "\n"
			 + "║║ Points : \033[0;1m" + HEAL_AMOUNT + "\033[0;2m\n"
			 + "║║ This spell will heal a character that can receive damages.\n"
			 + "╚╝\n";
	}

}