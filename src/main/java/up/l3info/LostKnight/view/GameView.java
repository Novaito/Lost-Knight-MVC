package up.l3info.LostKnight.view;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import up.l3info.LostKnight.mvc.View;

public class GameView extends Pane implements View {

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
	
	public static GameView create(List<View> subViews) {
		GameView gameView = new GameView();
		gameView.init(subViews);
		return gameView;
	}
	
	private void init(List<View> subViews) {
		for (View v : subViews) {
			getChildren().add((Node) v);
		}
	}
	
	public void setLocView(LocationView locView) {
		getChildren().set(0, (Node) locView);
	}
	
	public void setPlayerView(CharactersView playerView) {
		getChildren().set(1, (Node) playerView);
	}

}
