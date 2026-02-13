package fr.l3info.lostknight;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.l3info.lostknight.character.HeroEnnemyIT;
import fr.l3info.lostknight.character.NpcIT;
import fr.l3info.lostknight.game.GameIT;
import fr.l3info.lostknight.items.ContainerIT;
import fr.l3info.lostknight.items.FoodIT;
import fr.l3info.lostknight.items.KeyIT;
import fr.l3info.lostknight.items.PotionIT;
import fr.l3info.lostknight.items.WeaponIT;
import fr.l3info.lostknight.spells.SpellsIT;

@RunWith(Suite.class)
@SuiteClasses({ HeroEnnemyIT.class, NpcIT.class, GameIT.class, ContainerIT.class, FoodIT.class,
	KeyIT.class, PotionIT.class, WeaponIT.class, SpellsIT.class})
public class LostKnightTestSuite {

}
