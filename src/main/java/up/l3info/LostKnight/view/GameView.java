package up.l3info.LostKnight.view;


import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
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
	
	public static GameView create(View leftView, LocationView centerView) {
		GameView gameView = new GameView();
		gameView.init(leftView, centerView);
		return gameView;
	}
	
	private void init(View leftView, LocationView centerView) {
		setLeft((Node) leftView);
		setCenter(centerView);
		centerView.minHeightProperty().bind(heightProperty());
		centerView.minWidthProperty().bind(widthProperty());
	}
	
	public void setLocView(View locView) {
		getChildren().set(0, (Node) locView);
	}
	
	public void setPlayerView(View playerView) {
		getChildren().set(1, (Node) playerView);
	}

}
