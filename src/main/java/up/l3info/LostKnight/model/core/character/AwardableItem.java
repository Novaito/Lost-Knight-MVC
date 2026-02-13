package up.l3info.LostKnight.model.core.character;

import java.util.ArrayList;

import up.l3info.LostKnight.model.core.items.Item;

/**
 * This interface permit to characters excepted the Hero to manage his items with the Hero.
 * It is used in order to award the Hero after an action performed.
 */


public interface AwardableItem {

	/**
	 * Permit to give item(s) to an Hero.
	 * @param Hero The Hero to give it/them
	 */
	void giveItem(Hero hero);
	/**
	 * Permit to update the items of any character that implements the interface.
	 * @param item The List of item to update.
	 */
	void updateItems(ArrayList<Item> items);

}