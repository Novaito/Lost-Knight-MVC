package up.l3info.LostKnight.model.core.levelEditor;

import up.l3info.LostKnight.model.core.character.AttackableCharacter;
import up.l3info.LostKnight.model.core.character.Hero;
import up.l3info.LostKnight.model.core.game.Game;
import up.l3info.LostKnight.model.core.items.Food;
import up.l3info.LostKnight.model.core.map.Location;

public class GameSaverTest {

    Location spawn = new Location("Meadow", "A cool meadow", 15, 15); //TODO: à corriger nb de tile w x h
    Hero hero = new Hero("Hero", 100, "hello im hero", 100, 100);
		hero.setWeapon(new Weapon("sword", 20, 0, 0));
		hero.setHp(20);


    Food pig_1 = new Food("pig_1", 20, 300, 100);
    Food pig_2 = new Food("pig_2", 20, 200, 140);
    Food pig_3 = new Food("pig_3", 20, 350, 400);
		spawn.addItem(pig_1);
		spawn.addItem(pig_2);
		spawn.addItem(pig_3);

    AttackableCharacter orc = new Orc("Orc", 100, null, "je suis mechant", 200, 300);
		orc.setWeapon(new Weapon("claws", 10, 0, 0));

		spawn.addCharacters(orc);

    Game game = new Game(hero, spawn);



}
