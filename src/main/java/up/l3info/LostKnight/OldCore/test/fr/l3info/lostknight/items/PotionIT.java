package fr.l3info.lostknight.items;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import fr.l3info.lostknight.character.AttackableCharacter;
import fr.l3info.lostknight.character.Hero;
import fr.l3info.lostknight.spells.DamageSpell;
import fr.l3info.lostknight.spells.HealSpell;

public class PotionIT {

	Potion healthPotion;
	Potion damagePotion;
	Hero hero;
	AttackableCharacter character;
	
	@Before
	public void setUp() {
		healthPotion = new Potion("health potion", new HealSpell("healspell", 45));
		damagePotion = new Potion("dam Potion", new DamageSpell("Damagespell", 30));
		hero = new Hero("Hero", 100, "");
		hero.setHp(50);
		hero.addItem(healthPotion);
	}
	
	@Test
	public void useHealthPotion() {
		healthPotion.use(hero, hero);
		assertEquals(hero.getHp(), 95);
		assertFalse(hero.getInventory().hasItem(healthPotion.getName()));
	}
	
	@Test
	public void testEnemyUseDamage() {
		damagePotion.use(character, hero);
		assertEquals(hero.getHp(), 20);
	}
	
}
