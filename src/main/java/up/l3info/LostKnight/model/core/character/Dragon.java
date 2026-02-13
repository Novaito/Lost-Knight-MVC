package up.l3info.LostKnight.model.core.character;

import java.util.ArrayList;

import up.l3info.LostKnight.model.core.items.Item;
import up.l3info.LostKnight.model.core.items.Weapon;


/**
 * This a enemy character class. It is a precise instantiation of an enemy.
 * @author Thomas 
 */

public class Dragon extends Enemy {
	/**
	 * This constructor instantiates a Dragon.
	 * @see GameCharacter
	 * @see Enemy
	 * @param name Dragon's name
	 * @param hp Max hp
	 * @param items Lists of items to award
	 * @param dialog Dragon's dialog
	 */
	public Dragon(String name, int hp, ArrayList<Item> items, String dialog) {
		super(name, hp, items, dialog, new Weapon("Claws", 31));
	}

	/**
	 * Prints his appearance when looking him.
	 */
	@Override
	public void look() {
		System.out.println("A building of scales, the wind of his breath make your hair floating in the air. His heatened tummy ready to expulse the biggest flame ever threat you.\nWatch out this is THE DRAGON " + getName());
	}

	/**
	 * Prints dialog with the Hero. Once dialog printed he gives his items as award to the Hero, and loses his list items to give.
	 * <br>It also mark the end of the game because the Hero will print his dialog of end game.
	 * @param character The character (Hero) to give items.
	 */
	@Override
	public void discuss(GameCharacter character) {
		System.out.println(getName() + " : " + getDialog());
		giveItem(((Hero) character));
		((Hero) character).discuss(null);
	}
}