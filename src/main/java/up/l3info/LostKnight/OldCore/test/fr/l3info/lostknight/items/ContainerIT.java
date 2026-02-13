package fr.l3info.lostknight.items;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fr.l3info.lostknight.spells.HealSpell;

public class ContainerIT {
	
	Container bag = new Bag("testBag", 20);
	Container smallBag = new Bag("small bag", 3);;
	Food apple;
	Food pear;
	Potion health;
	Food ham;
	Key key;
	
	@Before
	public void setUp() {
		
		//bag = new Bag("testBag", 20);
		//smallBag = new Bag("small bag", 3);
		
		apple = new Food("Apple", 15);
		pear = new Food("Pear", 63);
		health = new Potion("Health potion", new HealSpell("Heal spell", 10));
		ham = new Food("ham", 2);
		key = new Key("Room key", null);
		
	}

	@Test
	public void getMaxItemNumber() {
		assertEquals(bag.getMaxItemNumber(), 20);
		assertEquals(smallBag.getMaxItemNumber(), 3);
	}
	
	@Test
	public void testAddItem() {
		assertTrue(bag.addItem(apple));
		assertTrue(bag.hasItem(apple.getName()));
		
		assertTrue(smallBag.addItem(pear));
		assertTrue(smallBag.addItem(health));
		assertTrue(smallBag.addItem(ham));
		assertTrue(smallBag.hasItem(pear.getName()));
		assertTrue(smallBag.hasItem(health.getName()));
		assertTrue(smallBag.hasItem(ham.getName()));
	}
	
	@Test
	public void testAddItem2() {
		bag.addItem(apple);
		assertFalse(bag.addItem(apple));
	}
	
	@Test
	public void testGetItemsNumber() {
		bag.addItem(apple);
		smallBag.addItem(pear);
		smallBag.addItem(health);
		smallBag.addItem(ham);
		assertEquals(bag.getItemNumber(), 1);
		assertEquals(smallBag.getItemNumber(), 3);
	}
	
	@Test
	public void testCanAddItem() {
		bag.addItem(apple);
		smallBag.addItem(pear);
		smallBag.addItem(health);
		smallBag.addItem(ham);
		assertTrue(bag.canAddItem());
		assertFalse(smallBag.canAddItem());
	}
	
	@Test
	public void testAddItemMax() {
		bag.addItem(apple);
		smallBag.addItem(pear);
		smallBag.addItem(health);
		smallBag.addItem(ham);
		System.out.println("smallBag items num: " + smallBag.getItemNumber() + "Small bag item Max: " + smallBag.getMaxItemNumber() );
		assertFalse(smallBag.addItem(key));
		assertFalse(smallBag.hasItem(key.getName()));
	}
	
	@Test
	public void testGetItem() {
		bag.addItem(apple);
		smallBag.addItem(pear);
		smallBag.addItem(health);
		smallBag.addItem(ham);
		assertEquals(bag.getItem(apple.getName()), apple);
		assertEquals(smallBag.getItem(pear.getName()), pear);
		assertEquals(smallBag.getItem(ham.getName()), ham);
		assertEquals(smallBag.getItem(health.getName()), health);
		assertEquals(smallBag.getItem(key.getName()), null);
	}
	
	@Test
	public void testRemoveItem() {
		bag.addItem(apple);
		smallBag.addItem(pear);
		smallBag.addItem(health);
		smallBag.addItem(ham);
		smallBag.removeItem(pear.getName());
		assertFalse(smallBag.hasItem(pear.getName()));
		smallBag.removeItem(health);
		assertFalse(smallBag.hasItem(health.getName()));
		assertEquals(smallBag.getItemNumber(), 1);
	}
	
	@Test
	public void testShowItems() {
		smallBag.addItem(pear);
		smallBag.addItem(health);
		smallBag.addItem(ham);
		assertTrue(smallBag.showItems().contains(pear.toString()));
		assertTrue(smallBag.showItems().contains(ham.toString()));
		assertTrue(smallBag.showItems().contains(health.toString()));
	}
	

}
