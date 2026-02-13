package fr.l3info.lostknight.items;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.l3info.lostknight.map.Exit;
import fr.l3info.lostknight.map.Location;

public class KeyIT {
	
	Key key;
	Exit exit;
	
	@Before
	public void setUp() {
		exit = new Exit("Door", new Location("", ""));
		key = new Key("Door Key", exit);
	}
	
	@Test
	public void testGetExit() {
		assertEquals(key.getExit(), exit);
	}

}
