package up.l3info.LostKnight.view;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
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
		String url = getClass().getResource(imgSrc).toExternalForm();
		Rectangle itemFrame = new Rectangle(64, 64);
		ImagePattern pattern = new ImagePattern(new Image(url));
		itemFrame.setFill(pattern);
		getChildren().add(itemFrame);
		
		setLayoutX(posX);
		setLayoutY(posY);
	}
}
