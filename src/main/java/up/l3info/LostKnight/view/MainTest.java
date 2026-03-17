package up.l3info.LostKnight.view;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainTest extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		String urlProfileHero = getClass().getResource("/img/profileHero.png").toExternalForm();
		HeroStateBar st = HeroStateBar.create(urlProfileHero, 0.2);
		List<Node> childElements = getSideBarElements();
		
		SideBarGame sideBarGame = SideBarGame.create(childElements, st);
		String urlLocation = getClass().getResource("/img/locationTest.png").toExternalForm();
		LocationView locationView = LocationView.create(urlLocation, null);
		
		GameView gameView = GameView.create(sideBarGame, locationView);
		
		
		Scene sc = new Scene(gameView, 1000, 800);
		primaryStage.setScene(sc);
		primaryStage.show();
	}
	
	// --> Liste de node car pour l'instant j'ai que des boutons et 
	//	   faire des controleurs juste pour ça ne sert à rien
	//
	// =>  List<View> si jamais il y a un inventaire (ou autre)
	public List<Node> getSideBarElements() {
		Button quit = new Button("QUIT");
		quit.setOnAction((e) -> {
			Platform.exit();
		});
		// Action pour exemple 
		Button win = new Button("WIN");
		win.setOnAction((e) -> {
			Alert winAlert = new Alert(AlertType.INFORMATION);
			winAlert.setContentText("YOU WIN !");
			winAlert.setX(500);
			winAlert.setY(450);
			winAlert.showAndWait();
		});
		
		List<Node> childElements = new ArrayList<Node>();
		childElements.addFirst((Node) quit);
		childElements.addFirst((Node) win);
		
		return childElements;
	}

}
