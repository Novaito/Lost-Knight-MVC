package fr.l3info.lostknight.character;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.Before;
import org.junit.Test;

import fr.l3info.lostknight.items.*;
import java.util.ArrayList;
import fr.l3info.lostknight.spells.*;

public class HeroEnnemyIT {
	
	Hero dH; //Dead Hero
	Hero h;
	Enemy dD;
	Enemy dO;
	Enemy dDa;
	
	StaminaSpell stamSpell;
	DamageSpell dSpell;
	WeaknessSpell wdSpell;
	WeaknessSpell wrSpell;
	
	ArrayList<Item> itemsD = new ArrayList<Item>();
	ArrayList<Item> itemsO = new ArrayList<Item>();
	ArrayList<Item> itemsDa = new ArrayList<Item>();
	
	@Before
	public void setUp() {
		stamSpell = new StaminaSpell("Monster", 35);
		dSpell = new DamageSpell("Swordart", 20);
		wdSpell = new WeaknessSpell("Chad", 10);
		wrSpell = new WeaknessSpell("Puanteur", -20);
		
		itemsD.add(new Weapon("Sword", 15));
		itemsD.add(new Potion("Dragon Breathe", new StaminaSpell("Dragon Breathe", 50)));
		itemsO.add(new Food("Steak", 10));
		itemsDa.add(new Potion(dSpell.getName(), dSpell));
		
		dO = new Orc("Brutus", 45, itemsO, "Grrr");
		dD = new Dragon("BigBob", 500, itemsD, "\nYOU ACHIEVED YOUR TESTS.\n ~ RESPECT ~");
		dDa = new Darriwil("Bango", 65, itemsDa, "Shling");
		h = new Hero("Toto", 100, "\nThanks to the tests, my mind came back !\nWow !");
		dH = new Hero("Kaki", 0, "...");
		
		
		h.getInventory().addItem(new Potion(stamSpell.getName(), stamSpell));
		h.getInventory().addItem(new Food("apple", 5));
		
		
	}
	
	@Test
	public void initGameCharacterTest() {
		
	}
	
	@Test
	public void initHeroTest() {
		assertEquals(dH.getName(), "Kaki");
		assertEquals(dH.getHp(), 0);
		assertTrue(dH.isDead());
		
		assertEquals(h.getName(), "Toto");
		assertEquals(h.getHp(), 100);
		assertFalse(h.isDead());
		assertEquals(h.getStamina(), 100);
		assertEquals(h.getHunger(), 50);
		assertEquals(h.getWeakness(), 0);
		assertEquals(h.getInventory().getMaxItemNumber(), 20);
	}
	
	@Test
	public void initEnnemiesTest() {
		assertEquals(dO.getName(), "Brutus");
		assertEquals(dO.getHp(), 45);
		
		assertEquals(dD.getName(), "BigBob");
		assertEquals(dD.getHp(), 500);
		assertEquals(((Dragon) dD).getWeapon().getName(), "Claws");
		
		assertEquals(dDa.getName(), "Bango");
		assertEquals(dDa.getHp(), 65);
	}
	
	@Test
	public void lookHeroTest() {
		h.look();
		h.showInventory();
		h.showHP();
		dH.showHP();
		h.showHunger();
		h.showStamina();
		h.showWeakness();
	}
	
	@Test
	public void lookEnnemiesTest() {
		dO.look();
		dD.look();
		dDa.look();
	}
	
	@Test
	public void HeroDiscussToEnnemiesTest() {
		h.discuss(dO);
		h.discuss(dDa);
		assertTrue(h.getInventory().hasItem("Steak"));
		assertTrue(h.getInventory().hasItem("Swordart"));
	}
	
	@Test
	public void getInventoryTest() {
		assertEquals(h.getInventory().getName(), "Hero's Bag");
		assertTrue(h.getInventory().hasItem("Monster"));
	}
	
	@Test
	public void finalDiscussTest() {
		h.discuss(dD);
		assertTrue(h.getInventory().hasItem("Sword"));
		assertTrue(h.getInventory().hasItem("Dragon Breathe"));
	}
	
	@Test
	public void feedTest() {
		h.feed(15);
		assertEquals(h.getHunger(), 65);
		h.feed(50);
		assertEquals(h.getHunger(), 100);
		h.feed(-110);
		assertEquals(h.getHunger(), 0);
		h.feed(50);
	}
	
	@Test
	public void addStaminaTest() {
		h.addStamina(55);
		assertEquals(h.getStamina(), 100);
		h.addStamina(-101);
		assertEquals(h.getStamina(), 0);
		h.addStamina(50);
		assertEquals(h.getStamina(), 50);
	}
	
	@Test
	public void addItemTest() {
		h.addItem(new Chest("Boufou", 25));
		assertTrue(h.getInventory().hasItem("apple"));
		assertFalse(h.getInventory().hasItem("Bouftou"));
		
	}
	
	@Test
	public void regenHPTest() {
		dH.regenHP(15);
		assertEquals(dH.getHp(), 0);
		h.regenHP(110);
		assertEquals(h.getHp(), 100);
		h.regenHP(-15);
		assertEquals(h.getHp(), 85);
		System.out.println();
		dDa.regenHP(-110);
		assertEquals(dDa.getHp(), 0);
		assertTrue(dDa.isDead());
	}
	
	@Test
	public void getAttackedTest() {
		dH.getAttacked(5);
		assertTrue(dH.isDead());
		h.getAttacked(35);
		assertEquals(h.getHp(), 65);
		dD.getAttacked(45);
		assertEquals(dD.getHp(), 455);
	}
	
	@Test
	public void attackTest() {
		Hero nH = null;
		dD.attack(nH, dD.getWeapon());
		
		h.discuss(dD); // On suppose avoir battu le dragon et reçu les récompenses
		
		h.attack(dDa, h.getInventory().getItem("Sword"));
		assertEquals(dDa.getHp(), 50);
		dO.attack(h, dO.getWeapon());
		assertEquals(h.getHp(), 85);
		
		h.attack(dO, h.getInventory().getItem("apple"));
		assertEquals(h.getHp(), 85);
	}
	
	@Test
	public void useSpellTest() {
		Hero nH = null;
		dD.useSpell(nH, dSpell);
		
		h.discuss(dD); // On suppose avoir battu le dragon et reçu les récompenses
		
		h.useSpell(dD, dSpell);
		assertEquals(dD.getHp(), 480);
		h.useSpell(dD, wrSpell);
		dD.getAttacked(19);
		assertEquals(dD.getHp(), 480);
		
		h.useSpell(dD, wdSpell);
		h.useSpell(dD, wdSpell);
		h.useSpell(dD, wdSpell);
		h.attack(dD, h.getInventory().getItem("Sword"));
		assertEquals(dD.getHp(), 455);
	}
}
