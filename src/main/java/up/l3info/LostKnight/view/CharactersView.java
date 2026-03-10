package up.l3info.LostKnight.view;

import javafx.scene.layout.Pane;
import up.l3info.LostKnight.mvc.View;

public class CharactersView extends Pane implements View {

	@Override
	public void hide() {
		setVisible(false);
	}

	@Override
	public void show() {
		setVisible(true);
	}
	
	private CharactersView() {
		super();
	}
	
	public static CharactersView create(String imgSrc, int posX, int posY) {
		CharactersView playerView = new CharactersView();
		playerView.init(imgSrc, posX, posY);
		return playerView;
	}
	
	private void init(String imgSrc, int posX, int posY) {
		setStyle("-fx-background-image: url(" + imgSrc + ");");
		setLayoutX(posX);
		setLayoutY(posY);
	}
	
	public void setX(int posX) {
		setLayoutX(posX);
	}
	
	public void setY(int posY) {
		setLayoutY(posY);
	}
}
