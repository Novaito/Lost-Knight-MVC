package fr.l3info.lostknight.character;

import java.util.ArrayList;

import fr.l3info.lostknight.items.Item;
import fr.l3info.lostknight.items.Weapon;


/**
 * This a enemy character class. It is a precise instantiation of an enemy. 
 * @author Wallis
 */

public class Darriwil extends Enemy {
	/**
	 * This constructor instantiates a Darriwil.
	 * @see GameCharacter
	 * @see Enemy
	 * @param name Darriwil's name
	 * @param hp Max hp
	 * @param items Lists of items to award
	 * @param dialog Darriwil's dialog
	 */
	public Darriwil(String name, int hp, ArrayList<Item> items, String dialog) {
		super(name, hp, items, dialog, new Weapon("Sword67", 10));
	}

	/**
	 * Prints dialog with the Hero. Once dialog printed he gives his items as award to the Hero, and loses his list items to give.
	 * @param character The character (Hero) to give items.
	 */
	@Override
	public void discuss(GameCharacter character) {
		System.out.println(getName() + " : " + getDialog());
		giveItem((Hero)character);
		System.out.println(character.getName() + " : It's strange am I like them ? Why do I kill them...");  //A changer
	}

	/**
	 * Prints his appearance when looking him.
	 */
	@Override
	public void look() {
		System.out.println("...\nA 6'7\" knight in armour with a curved weapon.\n Standing on all fours, he appears completely disarticulated. It is difficult to imagine that he was once human.");
	}
}