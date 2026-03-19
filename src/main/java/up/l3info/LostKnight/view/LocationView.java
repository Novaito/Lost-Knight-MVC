package up.l3info.LostKnight.view;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import up.l3info.LostKnight.mvc.View;

public class LocationView extends Pane implements View {

	private List<View> environmentViews;
	
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
	
	//TODO il faut une fonction qui update les subviews, dans le cas ou on rammasse un item par exemple

	private void init(String imgSrc, List<View> environmentViews) {
		Rectangle playingFrame = new Rectangle(500, 500);
		ImagePattern pattern = new ImagePattern(new Image(imgSrc));
		playingFrame.setFill(pattern);
		this.environmentViews = environmentViews;
		getChildren().add(playingFrame);
		
		if (this.environmentViews != null) {
			for (View elementView : environmentViews) {
				getChildren().add((Node) elementView);
			}			
		}
	}
	
	public void updateView(View viewPoping) {
		getChildren().remove(getChildren().indexOf((Node)viewPoping));
	}
	
	public void updateViews(List<View> environmentViews) {
		
		System.out.println(getChildren().size());
		getChildren().removeIf(node -> node instanceof ItemView || node instanceof CharactersView);
		System.out.println(getChildren().size());

		this.environmentViews.removeIf(view -> view instanceof ItemView || view instanceof CharactersView);
		
		for (View view : environmentViews) {
			getChildren().add((Node) view);
			this.environmentViews.add(view);
		}
	}
}
