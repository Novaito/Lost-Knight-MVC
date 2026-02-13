package up.l3info.LostKnight.model.core.spells;

import up.l3info.LostKnight.model.core.character.*;

/**
 * This a stamina spell class. It is a precise instantiation of a Spell.
 * @author Thomas 
 */

public class StaminaSpell extends Spell {

	private final int STAMINA_AMOUNT;

	/**
	 * This constructor instantiates a stamina spell with a name and a stamina amount to affect
	 * @param name Spell's name
	 * @param staminaAmount Stamina amount to affect
	 */
	public StaminaSpell(String name, int staminaAmount) {
		super(name);
		STAMINA_AMOUNT = staminaAmount;
	}

	/**
	 * Cast spell to the character and affect the character the weakness amount
	 * @param character The character to affect
	 */
	public void castSpell(AttackableCharacter character) {
		if (character instanceof Hero) {
			((Hero) character).addStamina(STAMINA_AMOUNT);
			System.out.println("\nEnergy came back !\n\033[92;1mYOU GAIN " + STAMINA_AMOUNT + " OF STAMINA.\033[0;2m");
			((Hero) character).showStamina();
		} else {
			System.out.println("\nOops,\nYour cast was wrong and sadly have lost your spell. You fortunately are lucky that " + character.getName() + " has no stamina.");
		}
	}
	
	/**
	 * Returns formatted spell's informations
	 */
	public String toString() {
		return "╔╗\n"
			 + "║║ STAMINA SPELL : " + getName() + "\n"
			 + "║║ Points : \033[0;1m" + STAMINA_AMOUNT + "\033[0;2m\n"
			 + "║║ This spell will increase your stamina for the fight.\n"
			 + "╚╝\n";
	}
}