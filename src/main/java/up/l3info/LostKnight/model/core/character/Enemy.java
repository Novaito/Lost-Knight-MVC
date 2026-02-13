package up.l3info.LostKnight.model.core.character;

import java.util.ArrayList;

import up.l3info.LostKnight.model.core.items.*;


/**
 * This abstract class represents a enemy.
 * @author Thomas
 */

public abstract class Enemy extends AttackableCharacter implements AwardableItem {

	private ArrayList<Item> items;
	private final Weapon WEAPON;

	/**
	 * This constructor creates an enemy with (his name, his max hp, his dialog, @see GameCharacter), a weapon and a list of items
	 * @param name The enemy name
	 * @param hp The max hp
	 * @param items The lists of items he will give once defeated
	 * @param w The weapon to inflict damage to the Hero
	 */
	public Enemy(String name, int hp, ArrayList<Item> items, String dialog, Weapon w) {
		super(name, hp, dialog);
		updateItems(items);
		WEAPON = w;
	}
	
	/**
	 * Update enemy's possession of items. The previous list of items is lost if another one existed before.
	 * @param items The list of items to update by.
	 */
	@Override
	public void updateItems(ArrayList<Item> items) {
		if (items != null) {
			this.items = items;
		} else this.items = null;
	}
	
	/**
	 * Add to the hero the item held by the enemy.
	 * @param hero The Hero to give his items.
	 */
	@Override
	public void giveItem(Hero hero) {
		String s = "\033[92;1m YOU RECEIVED : \033[0;2m\n  ╔╗\n";
		for (Item item : items) {
			hero.addItem(item);
			s += "  ║║ \033[92;1m" + item.getName() + "\033[0;2m \n";
		}
		s += "  ╚╝\n";
		System.out.println(s);
		updateItems(null);
	}
	
	/**
	 * Returns the weapon of the enemy.
	 * @return The weapon
	 */
	public Weapon getWeapon() {
		return WEAPON;
	} 
}