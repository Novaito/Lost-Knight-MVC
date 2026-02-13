package fr.l3info.lostknight.items;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import fr.l3info.lostknight.character.Hero;

public class FoodIT {
	
	Food bread;
	Food apple;
	Hero hero;
	
	@Before
	public void setUp() {
		bread = new Food("bread", 45);
		apple = new Food("apple", 50);
		hero = new Hero("DummyHero", 100, "");
	}

	@Test
	public void initFoodTest(){
		assertEquals(bread.getFoodPoints(), 45);
		assertEquals(apple.getFoodPoints(), 50);
		assertEquals(bread.getName(), "bread");
		assertEquals(apple.getName(), "apple");
	}

	@Test
	public void lookAtFoodTest() {
		bread.look();
		apple.look();
	}
	
	@Test
	public void consumeTest() {
		int currentHunger = hero.getHunger();
		bread.use(hero, hero);
		assertEquals(currentHunger + bread.getFoodPoints(), hero.getHunger());
		assertFalse(hero.getInventory().hasItem(bread.getName()));
		
	}
	
	
	
}
