package up.l3info.LostKnight.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import up.l3info.LostKnight.controller.gui.GameController;
import up.l3info.LostKnight.model.GameModel;
import up.l3info.LostKnight.model.core.character.Hero;
import up.l3info.LostKnight.model.core.game.Game;
import up.l3info.LostKnight.model.core.items.Food;
import up.l3info.LostKnight.model.core.map.Location;
import up.l3info.LostKnight.mvc.Controller;
import up.l3info.LostKnight.view.GameView;

public class DemoMain extends Application{
	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Location spawn = new Location("Meadow", "A cool meadow", 15, 15); //TODO: à corriger nb de tile w x h
		Hero hero = new Hero("Hero", 100, "hello im hero", 100, 100);
		hero.setHp(10);

		
		Food apple = new Food("Apple", 20, 300, 100);
		spawn.addItem(apple);
		Game game = new Game(hero, spawn);
		
		Controller<GameModel, GameView> mainController = GameController.create(new GameModel(game));
		
		Scene scene = new Scene(mainController.getView()); // 400, 600
		
		primaryStage.setTitle("Game");
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
	
	

}
