package fr.l3info.lostknight.items;

import java.util.Map;

import fr.l3info.lostknight.miscellaneous.CaseInsensitiveHashMap;

/**
 * The abstract class of a container item. A container item can contain a certain amount of other items
 * 
 * @author Noé
 */
public abstract class Container extends Item {

	private Map<String, Item> items;
	private final int MAX_ITEMS;

	/**
	 * This constructor creates a container with a name and the maximum items it can contain
	 * 
	 * @param name The name of the container
	 * @param maxItems
	 */
	public Container(String name, int maxItems) {
		super(name);
		MAX_ITEMS = maxItems;
		items = new CaseInsensitiveHashMap<Item>();
	}

	/**
	 * Add an item to the container if it is not full
	 * 
	 * @param item The item to add
	 * @return true if the item was successfully added to the container, false otherwise.
	 */
	public boolean addItem(Item item) {
		if(!canAddItem() || hasItem(item.getName())) {
			return false;
		}
		items.put(item.getName(), item);
		return true;
	}
	
	/**
	 * Returns true if the container is not full and an item can be added
	 * 
	 * @return true if an item can be added
	 */
	public boolean canAddItem() {
		return items.size() < MAX_ITEMS;
	}

	/**
	 * Removes an item from the container
	 * 
	 * @param item The item to remove
	 */
	public void removeItem(Item item) {
		this.removeItem(item.getName());
	}
	
	/**
	 * Removes an item from the container
	 * 
	 * @param itemName The name of the item to remove
	 */
	public void removeItem(String itemName) {
		items.remove(itemName);
	}
	
	/**
	 * Returns a String with all the items in the container
	 * 
	 * @return The string of the items in the container
	 */
	public String showItems() {
		String s = "╔╗\n";
		for (Map.Entry<String, Item> entry : items.entrySet()) {
	        s+= "║║ \033[93;1m" + entry.getValue().toString() + "\033[0;2m\n";
	    }
		s+="╚╝";
		return s;
	}
	
	/**
	 * Returns the number of items inside the container
	 * 
	 * @return The number of items inside the container
	 */
	public int getItemNumber() {
		return items.size();
	}
	
	/**
	 * Returns the maximum number of item that can be stored inside this container
	 * 
	 * @return the maximum capacity of this container
	 */
	public int getMaxItemNumber() {
		return this.MAX_ITEMS;
	}
	
	/**
	 * Return true if the container contains the item of the given name
	 * 
	 * @param itemName The name of the item
	 * @return true if the container contains the item
	 */
	public boolean hasItem(String itemName) {
		return items.get(itemName) != null;
	}
	
	/**
	 * Give the item named itemName inside the container. Returns null if the item is not contained in the container.
	 * 
	 * @param itemName The name of the item to get
	 * @return The item searched. Null if the item is not found
	 */
	public Item getItem(String itemName) {
		return items.get(itemName);
	}
	
}