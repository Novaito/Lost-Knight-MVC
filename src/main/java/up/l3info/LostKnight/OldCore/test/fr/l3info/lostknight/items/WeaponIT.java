package fr.l3info.lostknight.items;

import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Test;

import fr.l3info.lostknight.character.Hero;

public class WeaponIT {
	
	Weapon sword;
	Weapon knife;
	Hero hero;
	Hero target;
	
	@Before
	public void setUp() {
		sword = new Weapon("Sword", 34);
		knife = new Weapon("Knife", 26);
		hero = new Hero("hero", 100, "");
		target = new Hero("Second hero", 100, "");
	}
	
	@Test
	public void testGetName() {
		assertEquals(sword.getName(), "Sword");
		assertEquals(knife.getName(), "Knife");
	}
	
	@Test
	public void testGetDamages() {
		assertEquals(sword.getDamages(), 34);
		assertEquals(knife.getDamages(), 26);
	}
	
	@Test
	public void testUseWeapon() {
		target.setHp(100);
		hero.attack(target, knife);
		assertEquals(target.getHp(), 100 - knife.getDamages());
	}
	
	

}
