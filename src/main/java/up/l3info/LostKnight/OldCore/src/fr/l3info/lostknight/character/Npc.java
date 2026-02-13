package fr.l3info.lostknight.character;

import java.util.*;
import fr.l3info.lostknight.items.*;

public class Npc extends GameCharacter implements AwardableItem {

	private ArrayList<Item> items;

	/**
	 * This constructor instantiates a NPC hand-made with an item list.
	 * @see GameCharacter
	 * @param name The npc's name
	 * @param dialog The npc's dialog
	 * @param items The list items kept by the npc
	 */
	public Npc(String name, String dialog, ArrayList<Item> items) {
		super(name, dialog);
		updateItems(items);
	}

	/**
	 * This constructor permits to instantiate NPCs where their dialog is already saved.
	 * @see GameCharacter
	 * @param name The npc's name
	 * @param dialog The npc's dialog
	 * @param items The list items kept by the npc
	 * @param fromFile
	 */
	public Npc(String name, String dialog, ArrayList<Item> items, boolean fromFile) {
		
		super(name, dialog, fromFile);
		updateItems(items);
	}

	/**
	 * Prints the appearance of npc when looking him.
	 */
	@Override
	public void look() {
		System.out.println("No threat appearance, you can talk to him.");
	}

	/**
	 * Prints dialog with the Hero. Once dialog printed he gives his items as award to the Hero, and loses his list items to give.
	 * @param character The character (Hero) to give items.
	 */
	@Override
	public void discuss(GameCharacter character) {
		System.out.println(getName() + " : " + getDialog());
		giveItem((Hero)character);
		updateItems(null);
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
	 * Add to the hero the item held by the npc. If the npc doesn't have any items, the hero receive nothing.
	 * @param hero The Hero to give his items.
	 */
	@Override
	public void giveItem(Hero hero) {
		if (items != null) {
			String s = "\033[92;1m YOU RECEIVED :\033[0;2m \n  ╔╗\n";
			for (Item item : items) {
				hero.addItem(item);
				s += "  ║║ \033[92;1m" + item.getName() + "\033[0;2m \n";
			}
			s += "  ╚╝\n";
			System.out.println(s);			
		} else System.out.println(getName() + " doesn't have anything to give");
	}

}