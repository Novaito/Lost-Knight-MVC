package up.l3info.LostKnight.view;

import javafx.scene.layout.Pane;
import up.l3info.LostKnight.mvc.View;

public class ExitView extends Pane implements View {

	@Override
	public void hide() {
		setVisible(false);
		
	}

	@Override
	public void show() {
		setVisible(true);
	}
	
	private ExitView() {
		super();
	}

	private void init(String imgSrc, int posX, int posY) {
		setStyle("-fx-background-image: url(" + imgSrc + ");");
		setLayoutX(posX);
		setLayoutY(posY);
	}
	
	public static ExitView create(String imgSrc, int posX, int posY) {
		ExitView exitView = new ExitView();
		exitView.init(imgSrc, posX, posY);
		return exitView;
	}
}
