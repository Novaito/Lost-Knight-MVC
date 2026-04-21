package up.l3info.LostKnight.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import up.l3info.LostKnight.controller.MenuController;
import up.l3info.LostKnight.controller.gui.GameController;
import up.l3info.LostKnight.model.GameModel;
import up.l3info.LostKnight.model.core.character.AttackableCharacter;
import up.l3info.LostKnight.model.core.character.Hero;
import up.l3info.LostKnight.model.core.character.Orc;
import up.l3info.LostKnight.model.core.game.Game;
import up.l3info.LostKnight.model.core.items.Food;
import up.l3info.LostKnight.model.core.items.Weapon;
import up.l3info.LostKnight.model.core.levelEditor.GameSaver;
import up.l3info.LostKnight.model.core.map.Location;
import up.l3info.LostKnight.mvc.Controller;
import up.l3info.LostKnight.view.GameView;

import java.io.File;

public class DemoMain extends Application{
	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
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
		GameModel gameModel = new GameModel(game);
		MenuController menu = MenuController.create(primaryStage, gameModel);


		GameSaver saver = new GameSaver(new File("testSave.json") , game);
		saver.save();

		//plus utile ?
		Controller<GameModel, GameView> mainController = GameController.create(new GameModel(game));
		Controller<LevelEditorModel, LevelEditorView> lvlController = LevelEditorController.create(new LevelEditorModel(null, 15*15, false));
		
		Scene scene = new Scene(menu.getView()); // 400, 600
		
		primaryStage.setTitle("Game");
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
	
	
}
