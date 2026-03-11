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
	
	public static GameView create(View leftView, View centerView) {
		GameView gameView = new GameView();
		gameView.init(leftView, centerView);
		return gameView;
	}
	
	private void init(View leftView, View centerView) {
		setLeft((Node) leftView);
		setRight((Node) centerView);
	}
	
	public void setLocView(LocationView locView) {
		getChildren().set(0, (Node) locView);
	}
	
	public void setPlayerView(CharactersView playerView) {
		getChildren().set(1, (Node) playerView);
	}

}
