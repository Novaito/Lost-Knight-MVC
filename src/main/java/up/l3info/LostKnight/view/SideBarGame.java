package up.l3info.LostKnight.view;

import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import up.l3info.LostKnight.mvc.View;

public class SideBarGame extends BorderPane implements View {

	@Override
	public void hide() {
		setVisible(false);
	}

	@Override
	public void show() {
		setVisible(true);
	}
	
	public static SideBarGame create(List<Node> childsElements, HeroStateBar heroStateBarTest) {
		SideBarGame sideBarGame = new SideBarGame();
		sideBarGame.init(childsElements, heroStateBarTest);
		sideBarGame.style();
		return sideBarGame;
	}
	
	private void init(List<Node> childsElements, HeroStateBar heroStateBarTest) {
		VBox orderer = new VBox();
		for (Node n : childsElements) {
			Spring s1 = new Spring();
			orderer.getChildren().addAll(s1, n);
		}
		Spring s2 = new Spring();
		orderer.getChildren().add(s2);
		orderer.setAlignment(Pos.CENTER);
		setCenter(orderer);
		setBottom(heroStateBarTest);
	}
	
	private void style() {
		setStyle("-fx-background-color:#4f4f4f;");
	}
	
}

