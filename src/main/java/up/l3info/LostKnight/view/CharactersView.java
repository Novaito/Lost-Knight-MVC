package up.l3info.LostKnight.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import up.l3info.LostKnight.mvc.View;

public class CharactersView extends VBox implements View {

	private ProgressBar hpProgressBar;
	
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
		setSpacing(7);
	}
	
	public static CharactersView create(String imgSrc, int posX, int posY, double lifePercentage) {
		CharactersView playerView = new CharactersView();
		playerView.init(imgSrc, posX, posY, lifePercentage);
		return playerView;
	}
	
	private void init(String imgSrc, int posX, int posY, double lifePercentage) {
		// Image of Sprite 
		System.out.println(imgSrc);
		String url = getClass().getResource(imgSrc).toExternalForm();
		Rectangle characterFrame = new Rectangle(64, 64);
		ImagePattern pattern = new ImagePattern(new Image(url));
		characterFrame.setFill(pattern);
		Pane sprite = new Pane();
		sprite.getChildren().add(characterFrame);
		
		// HP Bar
		hpProgressBar = new ProgressBar();
		hpProgressBar.setMaxHeight(10);;
		hpProgressBar.setMaxWidth(64);
		setProgress(lifePercentage);
		
		getChildren().addAll(hpProgressBar, sprite);
		setLayoutX(posX);
		setLayoutY(posY);
		
	}
	
	private void setProgress(double lifePercentage) {
		hpProgressBar.setProgress(lifePercentage);
		String colorCSS;
		if (lifePercentage > 0.3) {
			colorCSS = ("-fx-accent:#adff2f;");
		} else {
			colorCSS = ("-fx-accent:#ff4500;");
		}
		hpProgressBar.setStyle(
				"-fx-control-inner-background: #222;"
				+ colorCSS);
	}
	
	public DoubleProperty progressProperty() {
		return hpProgressBar.progressProperty();
	}
	
	public StringProperty progressStyleProperty() {
		return hpProgressBar.styleProperty();
	}
	
	public void setX(int posX) {
		setLayoutX(posX);
	}
	
	public void setY(int posY) {
		setLayoutY(posY);
	}
	
	public void setHp(double lifePercentage) {
		setProgress(lifePercentage);
	}
}
