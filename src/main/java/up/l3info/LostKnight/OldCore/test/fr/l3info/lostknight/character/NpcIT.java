package fr.l3info.lostknight.character;

import static org.junit.Assert.assertTrue;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import fr.l3info.lostknight.items.Food;
import fr.l3info.lostknight.items.Item;
import fr.l3info.lostknight.items.Weapon;

public class NpcIT {
	
	Hero h = new Hero("Toto", 100, "");
	
	Npc n;
	Npc nSvd;
	
	ArrayList<Item> itemsN = new ArrayList<Item>();
	
	@Before
	public void setUp() {
		itemsN.add(new Weapon("Sword", 15));
		n = new Npc("Dowee", "\nYou are testing", itemsN);
		itemsN.add(new Food("Apple", 5));
		System.out.println("Appel txt");
		nSvd = new Npc("Pasteur", "/dialogs/Pasteur.txt", itemsN , true);			
	
	}
	
	@Test
	public void lookTest() {
		n.look();
		nSvd.look();
	}
	
	@Test
	public void discussTest() {
		h.discuss(n);
		assertTrue(h.getInventory().hasItem("Sword"));
		
		h.discuss(n);
	}
	
}
