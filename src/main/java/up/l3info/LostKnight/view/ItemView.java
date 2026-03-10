package up.l3info.LostKnight.view;

import javafx.scene.layout.Pane;
import up.l3info.LostKnight.mvc.View;

public class ItemView extends Pane implements View {

	@Override
	public void hide() {
		setVisible(false);
	}

	@Override
	public void show() {
		setVisible(true);
	}

	private ItemView() {
		super();
	}
	
	public static ItemView create(String imgSrc, int posX, int posY) {
		ItemView itemView = new ItemView();
		itemView.init(imgSrc, posX, posY);
		return itemView;
	}
	
	private void init(String imgSrc, int posX, int posY) {
		setStyle("-fx-background-image: url(" + imgSrc + ");");
		setLayoutX(posX);
		setLayoutY(posY);
	}
}
