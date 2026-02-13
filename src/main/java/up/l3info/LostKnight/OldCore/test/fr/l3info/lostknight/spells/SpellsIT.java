package fr.l3info.lostknight.spells;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.l3info.lostknight.character.*;

public class SpellsIT {
	
	StaminaSpell stamSpell;
	HealSpell hSpell;
	WeaknessSpell weakSpell;
	WeaknessSpell resistantSpell;
	DamageSpell dSpell;
	StarvationSpell starveSpell;
	
	Hero h = new Hero("Toto", 100, null);
	Dragon d = new Dragon("Romuald", 500, null, "");
	
	
	@Before
	public void setUp() {
		stamSpell = new StaminaSpell("Monster", 35);
		hSpell = new HealSpell("Divine Touch", 30);
		weakSpell = new WeaknessSpell("Ensorceled Pepper", 5);
		resistantSpell = new WeaknessSpell("Shield", -25);
		dSpell = new DamageSpell("Tiger Claws", 15);
		starveSpell = new StarvationSpell("Free Will Buffet", 40);
	}
	
	@Test
	public void initSpellTest() {
		assertEquals(stamSpell.getName(), "Monster");
		assertEquals(hSpell.getName(), "Divine Touch");
		assertEquals(weakSpell.getName(), "Ensorceled Pepper");
		assertEquals(resistantSpell.getName(), "Shield");
		assertEquals(dSpell.getName(), "Tiger Claws");
		assertEquals(starveSpell.getName(), "Free Will Buffet");
	}
	
	@Test
	public void testLookSpell() {
		System.out.println(hSpell);
		System.out.println(weakSpell);
		System.out.println(resistantSpell);
		System.out.println(dSpell);
		System.out.println(starveSpell);
		System.out.println(stamSpell);
	}
	
	@Test
	public void testDamageSpell() {		
		// No need to test with null value because it's verified before it's called
		dSpell.castSpell(h); 
		dSpell.castSpell(d);
		assertEquals(h.getHp(), 85);
		assertEquals(d.getHp(), 485);
	}
	
	@Test
	public void testHealSpell() {
		//Idem
		hSpell.castSpell(h);
		hSpell.castSpell(d);
		assertEquals(h.getHp(), 100);
		assertEquals(d.getHp(), 500);
	}
	
	@Test
	public void testStaminaSpell() {
		h.addStamina(-50);
		//Idem
		stamSpell.castSpell(h);
		stamSpell.castSpell(d);
		assertEquals(h.getStamina(), 85);
	}
	
	@Test
	public void testStarvationSpell() {
		starveSpell.castSpell(h);
		starveSpell.castSpell(d);
		assertEquals(h.getHunger(), 90);
	}
	
	@Test
	public void testWeaknessSpell() {
		weakSpell.castSpell(h);
		resistantSpell.castSpell(d);
		dSpell.castSpell(h);
		dSpell.castSpell(d);
		assertEquals(h.getHp(), 80);
		assertEquals(d.getHp(), 500);
	}
}
