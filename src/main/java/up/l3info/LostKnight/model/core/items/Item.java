package up.l3info.LostKnight.model.core.items;

import up.l3info.LostKnight.model.core.miscellaneous.*;

/**
 * This abstract class represents the items in the games
 * 
 * @author Noé
 */
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = Food.class,   name = "Food"),
		@JsonSubTypes.Type(value = Weapon.class, name = "Weapon")
})
public abstract class Item extends GameObject implements LookableObject {

	private String name;
	private String description;
	private static final String DEFAULT_DESC = "This is a %s";
	private final String TILE_PATH;

	/**
	 * This constructor creates an Item with its name, a description, posX and posY
	 * 
	 * @param name The name of the item
	 * @param desc The description of the item
	 */
	public Item(String name, String path, String desc, int posX, int posY) {
		this.name = name;
		this.TILE_PATH = path;
		this.setPosX(posX);
		this.setPosY(posY);
		if(desc == null) {
			this.description = String.format(DEFAULT_DESC, name);
		}else {
			description = desc;
		}
	}
	
	/**
	 * This constructor creates an Item with its name, posX and posY. It uses the default description
	 * 
	 * @param name The name of the item
	 */
	public Item(String name, int posX, int posY) {
		this(name, "", null, posX, posY);
	}
	
	public String getTilePath() {
		return TILE_PATH;
	}

	/**
	 * Returns the name of the Item
	 * 
	 * @return the name of the Item
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the description of the Item
	 * 
	 * @return the description of the Item
	 */	
	public String getDescription() {
		return description;
	}
	
	/**
	 * Implementation of the LookableItem interface
	 */
	@Override
	public abstract void look();
	
	/**
	 * give the name of the Item
	 */
	@Override
	public String toString() {
		return "\033[93;1m" + getName() + "\033[0;2m";
	}

}