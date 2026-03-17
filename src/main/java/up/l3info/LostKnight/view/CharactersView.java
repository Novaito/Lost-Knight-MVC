package up.l3info.LostKnight.view;

import javafx.beans.property.SimpleIntegerProperty;
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
	
	public static CharactersView create(String imgSrc, SimpleIntegerProperty posX, SimpleIntegerProperty posY) {
		CharactersView playerView = new CharactersView();
		playerView.init(imgSrc, posX, posY);
		return playerView;
	}
	
	private void init(String imgSrc, SimpleIntegerProperty posX, SimpleIntegerProperty posY) {
		setStyle("-fx-background-image: url(" + imgSrc + ");");
		layoutXProperty().bind(posX);
		layoutYProperty().bind(posY);
	}
	
	public void setX(int posX) {
		setLayoutX(posX);
	}
	
	public void setY(int posY) {
		setLayoutY(posY);
	}
}
