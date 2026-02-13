package fr.l3info.lostknight.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.l3info.lostknight.character.Darriwil;
import fr.l3info.lostknight.character.Enemy;
import fr.l3info.lostknight.character.Hero;
import fr.l3info.lostknight.character.Npc;
import fr.l3info.lostknight.items.Chest;
import fr.l3info.lostknight.items.Food;
import fr.l3info.lostknight.items.Key;
import fr.l3info.lostknight.items.Potion;
import fr.l3info.lostknight.items.Weapon;
import fr.l3info.lostknight.map.Exit;
import fr.l3info.lostknight.map.Location;
import fr.l3info.lostknight.map.LockedExit;
import fr.l3info.lostknight.spells.DamageSpell;

public class GameIT {
	
	Game game;
	
	Hero hero;
	Enemy darriwill;
	Enemy darriwill2;
	Npc npc;
	
	Location initialRoom;
	Location nextRoom;
	Location nextRoomLocked;
	
	Exit toNextRoom;
	Exit toInitialRoom;
	LockedExit toNextLockedRoom;
	
	Key key;
	Key dummyKey;
	Food bread;
	Potion damagePotion;
	Weapon sword;
	Weapon ultraSword;
	
	Chest chest;
	
	
	
    private ByteArrayInputStream testIn;
    private final InputStream systemIn = System.in;
    Scanner gameScanner;
    Field scannerField;
	
	@Before
	public void setUp() {
		
		hero = new Hero("hero", 100, null);
		darriwill = new Darriwil("Darriwill", 100, null, "Hello");
		darriwill2 = new Darriwil("Darriwill2", 100, null, "Hello");
		npc = new Npc("villager", "", null);
		
		
		key = new Key("lockkey");
		dummyKey = new Key("dummyExit");
		bread = new Food("bread", 23);
		damagePotion = new Potion("damagePotion", new DamageSpell("damageSpell", 10));
		sword = new Weapon("sword", 10);
		ultraSword = new Weapon("ultraSword", 120);
		
		chest = new Chest("chest", 10);
		
		initialRoom = new Location("initialRoom", "");
		nextRoom = new Location("nextRoom", "");
		nextRoomLocked = new Location("nextLockedRoom", "");
		
		toInitialRoom = new Exit("toInitialRoom", initialRoom);
		toNextRoom = new Exit("toNextRoom", nextRoom);
		toNextLockedRoom = new LockedExit("toNextLockedRoom", nextRoomLocked);
		key.setExit(toNextLockedRoom);
		
		initialRoom.addExit(toNextRoom);
		initialRoom.addExit(toNextLockedRoom);
		initialRoom.addItem(key);
		initialRoom.addItem(dummyKey);
		initialRoom.addItem(bread);
		initialRoom.addItem(damagePotion);
		initialRoom.addItem(sword);
		initialRoom.addItem(ultraSword);
		initialRoom.addItem(chest);
		initialRoom.addCharacters(darriwill);
		initialRoom.addCharacters(npc);
		
		nextRoom.addCharacters(darriwill2);
		
		chest.addItem(bread);
		
		nextRoom.addExit(toInitialRoom);
		
		nextRoomLocked.addExit(toInitialRoom);
		
		
		game = new Game(hero, initialRoom);
	    Field fields[] = game.getClass().getDeclaredFields();
	    for (Field field : fields) {
			if( field.getName().equals("SCANNER")) {
				field.setAccessible(true);
				try {
					scannerField = field;
					gameScanner = (Scanner) field.get(game);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	    
	    gameScanner.close();

	}
	
	private void reInitScanner() {
		try {
			scannerField.set(scannerField, new Scanner(System.in));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void closeScanner() {
		gameScanner.close();
	}
	
	private void provideInput(String data) {
		System.out.println("In >> " + data);
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        reInitScanner();
        //closeScanner();
    }
	
	@After
    public void restoreSystemInput() {
		closeScanner();
        System.setIn(systemIn);
    }
	
	@Test
	public void testInitalGameState() {
		assertEquals(game.getCurrentLocation(), initialRoom);
	}
	
	@Test
	public void testTakeExit() {
		provideInput("GO toNextRoom");
		game.gameTurn();
		assertEquals(game.getCurrentLocation(), nextRoom);

	}
	
	@Test
	public void testTakeUnexistingExit() {
		provideInput("go exit");
		game.gameTurn();
	}
	
	@Test
	public void testPickupItem() {
		provideInput("TAKE lockkey");
		game.gameTurn();
		assertTrue(hero.getInventory().hasItem(key.getName()));
		assertNull(initialRoom.getItem(key.getName()));

	}
	
	@Test
	public void testPickupChest() {
		provideInput("Take chest");
		game.gameTurn();
		assertNotNull(game.getCurrentLocation().getItem("chest"));
	}
	
	@Test
	public void testPickupBreadInChest() {
		provideInput("take bread chest");
		game.gameTurn();
		assertTrue(hero.getInventory().hasItem("bread"));
		assertNull(((Chest)game.getCurrentLocation().getItem("chest")).getItem("bread"));
	}
	
	@Test
	public void testPickupItemDoesntExists() {
		provideInput("TAKE fakeItem");
		game.gameTurn();
		assertTrue(hero.getInventory().getItemNumber() == 0);
	}
	
	@Test
	public void testCrossLockedDoor() {
		provideInput("Go toNextLockedRoom");
		game.gameTurn();
		assertEquals(game.getCurrentLocation(), initialRoom);
	}
	
	@Test
	public void testOpenThenCrossDoor() {
		game.take("lockkey");
		provideInput("use lockkey toNextLockedRoom");
		game.gameTurn();
		provideInput("Go toNextLockedRoom");
		game.gameTurn();
		assertEquals(game.getCurrentLocation(), nextRoomLocked);
	}
	
	@Test
	public void testUseFood() {
		//TODO set la hunger ou la réduire au moins
		game.take("bread");
		provideInput("use bread");
		game.gameTurn();
		assertFalse(hero.getInventory().hasItem("bread"));
	}
	
	@Test
	public void testUsePotionOnNpc() {
		game.take(damagePotion.getName());
		provideInput("use " + damagePotion.getName() + " " + npc.getName());
		game.gameTurn();
		assertTrue(hero.getInventory().hasItem(damagePotion.getName()));
	}
	
	@Test
	public void testUseKeyOnNotLockedExit() {
		game.take("lockkey");
		provideInput("use lockkey toNextRoom");
		game.gameTurn();
	}
	
	@Test
	public void testUseKeyOnWrongExit() {
		game.take(dummyKey.getName());
		provideInput("use " + dummyKey.getName() + " " + toNextLockedRoom.getName());
		game.gameTurn();
		provideInput("go " + toNextLockedRoom.getName());
		game.gameTurn();
		assertEquals(game.getCurrentLocation(), initialRoom);
		assertTrue(toNextLockedRoom.isLocked());
	}
	
	@Test
	public void testUseKeyOnUnexistingExit() {
		game.take("lockkey");
		provideInput("use lockkey exit");
		game.gameTurn();
		provideInput("go exit");
		game.gameTurn();
		assertEquals(game.getCurrentLocation(), initialRoom);
	}
	
	@Test
	public void testUseUnknownItem() {
		provideInput("use Branch");
		game.gameTurn();
	}
	
	@Test
	public void testUseWeapon() {
		game.take("sword");
		provideInput("USE sword Darriwill");
		game.gameTurn();
		assertEquals(darriwill.getHp(), 90);
	}
	
	@Test
	public void testEnemyAttackHero() {
		provideInput("look");
		game.gameTurn();
		assertEquals(hero.getHp(), 100 - darriwill.getWeapon().getDamages());
	}
	
	@Test
	public void enemyKillHero() {
		provideInput("Go toNextRoom");
		game.gameTurn();
		for(int i = 0; i < 9; i++) {			
			provideInput("look");
			game.gameTurn();
		}

		
		assertEquals(game.getCurrentLocation(), initialRoom);
		assertEquals(hero.getHp(), 50);

	}
	
	@Test
	public void testHeroKillEnnemy() {
		game.take("ultraSword");
		provideInput("use ultraSword Darriwill");
		game.gameTurn();
		assertEquals(darriwill.getHp(), 0);
		assertTrue(darriwill.isDead());
	}
	
	@Test
	public void testSetCurrentLocation() {
		game.setCurrentLocation(nextRoomLocked);
		assertEquals(game.getCurrentLocation(), nextRoomLocked);
	}
	
	@Test
	public void testStopGameLoop() {
		provideInput("quit");
		game.gameLoop();
		assertTrue(true);
	}
	
	@Test
	public void testLookItem() {
		provideInput("look sword");
		game.gameTurn();
	}
	
	@Test
	public void testLookUnexistingItem() {
		provideInput("look brick");
		game.gameTurn();
	}
	
	@Test
	public void testLookExit() {
		provideInput("look toNextRoom");
		game.gameTurn();
	}
	
	@Test
	public void testLookUnexistingExit() {
		provideInput("look trapdoor");
		game.gameTurn();
	}
	
	@Test
	public void testLookCharacter() {
		provideInput("look " + darriwill.getName());
		game.gameTurn();
	}
	
	@Test
	public void testLookUnexistingCharacter() {
		provideInput("look zombie");
		game.gameTurn();
		
	}
	
	@Test
	public void testLookHero(){
		provideInput("look hero");
		game.gameTurn();
	}
	
	

	
	//TODO si le hero est mort ??
	

}
