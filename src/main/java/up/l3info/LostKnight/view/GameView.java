package up.l3info.LostKnight.view;


import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import up.l3info.LostKnight.mvc.View;

public class GameView extends BorderPane implements View {

	@Override
	public void hide() {
		setVisible(false);
	}

	@Override
	public void show() {
		setVisible(true);
	}
	
	private GameView() {
		super();
	}
	
	/**
	 * Instanciate properly the game view
	 * @param childsElement List of elements to add (buttons quit,...)
	 * @param centerView
	 * @param hero
	 * @return
	 */
	public static GameView create(List<Node> childsElement,LocationView centerView, CharactersView hero) {
		GameView gameView = new GameView();
		gameView.init(childsElement, centerView, hero);
		return gameView;
	}
	
	private void init(List<Node> childsElement, LocationView locationView, CharactersView hero) {
		String urlProfileHero = getClass().getResource("/img/profileHero.png").toExternalForm();
		HeroStateBar heroStateBar = HeroStateBar.create(urlProfileHero, hero.progressProperty().getValue());
		heroStateBar.progressProperty().bind(hero.progressProperty());
		heroStateBar.progressStyleProperty().bind(hero.progressStyleProperty());
		SideBarGame sideBarGame = SideBarGame.create(childsElement, heroStateBar);
		setLeft(sideBarGame);
		
		Pane centerView = new Pane();
		centerView.getChildren().add(locationView);
		centerView.getChildren().add(hero);
		setCenter(centerView);
		
		centerView.minHeightProperty().bind(minHeightProperty());
		centerView.minWidthProperty().bind(minWidthProperty());
	}
	
	public void setLocationView(LocationView locationView) {
		((Pane) getCenter()).getChildren().set(0, locationView);
	}
}
