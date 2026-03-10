package up.l3info.LostKnight.view;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import up.l3info.LostKnight.mvc.View;

public class LocationView extends Pane implements View {

	@Override
	public void hide() {
		setVisible(false);
	}

	@Override
	public void show() {
		setVisible(true);
	}
	
	private LocationView() {
		super();
	}
	
	public static LocationView create(String imgSrc, List<View> environmentViews) {
		LocationView locationView = new LocationView();
		locationView.init(imgSrc, environmentViews);
		return locationView;
	}

	private void init(String imgSrc, List<View> environmentViews) {
		setStyle("-fx-background-image: url(" + imgSrc + ");");
		for (View elementView : environmentViews) {
			getChildren().add((Node) elementView);
		}
	}
}
