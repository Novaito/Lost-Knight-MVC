package up.l3info.LostKnight.main;

import javafx.application.Application;
import javafx.stage.Stage;
import up.l3info.LostKnight.controller.MenuController;
import up.l3info.LostKnight.model.GameModel;
import up.l3info.LostKnight.model.core.character.AttackableCharacter;
import up.l3info.LostKnight.model.core.character.Hero;
import up.l3info.LostKnight.model.core.character.Orc;
import up.l3info.LostKnight.model.core.game.Game;
import up.l3info.LostKnight.model.core.items.Food;
import up.l3info.LostKnight.model.core.items.Weapon;
import up.l3info.LostKnight.model.core.map.Location;
import up.l3info.LostKnight.model.core.levelEditor.GameSaver;
import up.l3info.LostKnight.model.core.levelEditor.GameLoader;
import java.io.File;

public class DemoMain extends Application{
	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		//partie codé en dur
		/*
		String[] paths = {
          "/img/floor/grass.png",
          "/img/floor/grass.png",
          "/img/floor/grass.png",
          "/img/floor/grass.png",
          "/img/floor/grass.png",
          "/img/floor/grass.png",
          "/img/floor/grass.png",
          "/img/floor/grass.png",
          "/img/floor/grass.png",
          "/img/floor/grass.png",
          "/img/floor/grass.png",
          "/img/floor/grass.png",
          "/img/floor/grass.png",
          "/img/floor/grass.png",
          "/img/floor/grass.png",
          "/img/floor/grass.png",
          "/img/floor/grass.png",
          "/img/floor/grass.png",
          "/img/floor/grass.png",
          "/img/floor/grass.png",
          "/img/floor/grass.png",
          "/img/floor/grass.png",
          "/img/floor/grass.png",
          "/img/floor/grass.png",
          "/img/floor/grass.png",
		};
		
		Location spawn = new Location("Meadow", "A cool meadow", paths, 15, 15);


		Food pig_1 = new Food("pig_1", 20, "/img/sprite/pork.png", null, 5, 5);
		Food pig_2 = new Food("pig_2", 20, "/img/sprite/pork.png", null, 1, 7);
		Food pig_3 = new Food("pig_3", 20, "/img/sprite/pork.png", null, 9, 10);
		spawn.addItem(pig_1);
		spawn.addItem(pig_2);
		spawn.addItem(pig_3);

		AttackableCharacter orc = new Orc("Orc", 100, null, "/img/sprite/orc.png", "je suis mechant", 200, 300);
		orc.setWeapon(new Weapon("claws", 10, 0, 0));

		spawn.addCharacters(orc);


		 */

		GameLoader loader = new GameLoader(new File("./save/gamesave.json"));
		Location spawn = loader.load("Meadow");



		Hero hero = new Hero("Hero", 100, "/img/profileHero.png", "hello im hero", 100, 100);
		hero.setWeapon(new Weapon("sword", 20, 0, 0));
		hero.setHp(20);

		Game game = new Game(hero, spawn);

			//plus utile car j'ai deja sérialiser
			//GameSaver saver = new GameSaver(new File("./save/gamesave2.json"), game);
			//saver.save(spawn);

		GameModel gameModel = new GameModel(game);
		MenuController menu = MenuController.create(primaryStage, gameModel);

		primaryStage.show();	
	}
	
	
}
