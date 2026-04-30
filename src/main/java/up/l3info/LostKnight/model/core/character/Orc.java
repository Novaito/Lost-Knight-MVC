package up.l3info.LostKnight.model.core.character;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import up.l3info.LostKnight.model.core.items.Item;
import up.l3info.LostKnight.model.core.items.Weapon;


/**
 * This a enemy character class. It is a precise instantiation of an enemy.
 * @author Thomas 
 */

public class Orc extends Enemy {

	/**
	 * This constructor instantiates an Orc.
	 * @see GameCharacter
	 * @see Enemy
	 * @param name Orc's name
	 * @param hp Max hp
	 * @param items Lists of items to award
	 * @param dialog Orc's dialog
	 */
	@JsonCreator
	public Orc(@JsonProperty("name") String name,
           @JsonProperty("hp") int hp,
			   @JsonProperty("items") ArrayList<Item> items,
			   @JsonProperty("tilePath") String path,
           @JsonProperty("dialog") String dialog,
           @JsonProperty("posX") int posX,
           @JsonProperty("posY") int posY) {
		super(name, hp, items, path, dialog, new Weapon("AxRock", 15, 0, 0), posX, posY);
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
		System.out.println("A massive orc with dark green skin, damaged tusks, scars on the body handle a big axe with a rock instead of a blade.\nThis beast lived through the war.");
	}

}