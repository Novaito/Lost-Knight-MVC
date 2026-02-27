package up.l3info.LostKnight.model.core.character;

import java.util.ArrayList;

import up.l3info.LostKnight.model.core.items.Item;
import up.l3info.LostKnight.model.core.items.Weapon;


/**
 * This a enemy character class. It is a precise instantiation of an enemy. 
 * @author Wallis
 */

public class Undead extends Enemy {

	/**
	 * This constructor instantiates an Undead.
	 * @see GameCharacter
	 * @see Enemy
	 * @param name Undead's name
	 * @param hp Max hp
	 * @param items Lists of items to award
	 * @param dialog Undead's dialog
	 */
	public Undead(String name, int hp, ArrayList<Item> items, String dialog, int posX, int posY) {
		super(name, hp, items, dialog, new Weapon("Bite", 2),posX , posY);
	}

	/**
	 * This constructor instantiates an Undead.
	 * @see GameCharacter
	 * @see Enemy
	 * @param name Undead's name
	 * @param hp Max hp
	 * @param items Lists of items to award
	 * @param dialog Undead's dialog
	 * @param dmg Damages that he will inflict 
	 */
	public Undead(String name, int hp, ArrayList<Item> items, String dialog , int dmg,int posX , int posY) {
		super(name, hp, items, dialog, new Weapon("Bite", dmg),posX , posY);
	}

	/**
	 * Prints dialog with the Hero. Once dialog printed he gives his items as award to the Hero, and loses his list items to give.
	 * @param character The character (Hero) to give items.
	 */
	@Override
	public void discuss(GameCharacter character) {
		System.out.println(getName() + " : " + getDialog());
		giveItem((Hero)character);
		System.out.println(character.getName() + " : job done ! But why do I kill them...");
	}

	/**
	 * Prints his appearance when looking him.
	 */
	@Override
	public void look() {
		System.out.println("A humanoid undead creature, rather slow, it is more frightening than harmful.\nIts soul is waiting to be freed.");
	}

}