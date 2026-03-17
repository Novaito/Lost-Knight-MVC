package up.l3info.LostKnight.view;

import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import up.l3info.LostKnight.mvc.View;

public class HeroStateBar extends HBox implements View {
	
	private ProgressBar hpProgressBar;

	@Override
	public void hide() {
		setVisible(false);
	}

	@Override
	public void show() {
		setVisible(true);
	}
	
	private HeroStateBar() {
		super();
		setMaxHeight(64);
		setAlignment(Pos.BOTTOM_LEFT);
	}
	
	public static HeroStateBar create(String imgSrc, double lifePercentage) {
		HeroStateBar heroStateBar = new HeroStateBar();
		heroStateBar.init(imgSrc, lifePercentage);
		heroStateBar.style();
		return heroStateBar;
	}
	
	private void init(String imgSrc, double lifePercentage) {
		StackPane profileHero = createHeroSide(imgSrc);
		VBox hpPart = createHpPart(lifePercentage);		
		
		profileHero.maxHeightProperty().bind(maxHeightProperty());
		hpPart.maxHeightProperty().bind(maxHeightProperty());
		
		getChildren().addAll(profileHero, hpPart);
	}
	
	private StackPane createHeroSide(String imgSrc) {
		StackPane profileHero = new StackPane();
		Circle circle = new Circle(30);
		ImagePattern pattern = new ImagePattern(new Image(imgSrc));
		circle.setFill(pattern);
		circle.setEffect(new DropShadow(10, Color.web("#f64cbf")));
		
		profileHero.getChildren().add(circle);
		return profileHero;
	}
	
	private VBox createHpPart(double lifePercentage) {
		VBox hpPart = new VBox();
		Label labelHeroName = new Label("Hero");
		
		HBox hpBar = new HBox();
		Spring s1 = new Spring();
		Spring s2 = new Spring();
		Spring s3 = new Spring();
		Label labelHp = new Label("HP : ");
		
		labelHeroName.setStyle(
				"-fx-text-fill:#7cfc00;"
				+ "-fx-font-family:consolas;"
				+ "-fx-font-size:14px;"
				+ "-fx-font-weight:bolder;");
		labelHeroName.setPadding(new Insets(0, 0, 0, 5));
		labelHp.setStyle(
				"-fx-text-fill:#fff;"
				+ "-fx-font-family:consolas;");
		labelHp.setPadding(new Insets(0, 0, 0, 5));
		
		hpProgressBar = new ProgressBar();
		hpProgressBar.setMinHeight(15);
		setProgress(lifePercentage);
		
		
		hpBar.getChildren().addAll(labelHp, hpProgressBar);
		hpPart.getChildren().addAll(s1, labelHeroName, s2, hpBar, s3);
		
		return hpPart;
	}
	
	private void style() {
		for (Node n: getChildren()) {
			setMargin(n, new Insets(0, 5, 10, 5));
		}
		getChildren().get(1).setStyle(
					"-fx-background-color:#2f2f2f;"
					+ "-fx-background-radius:10px;"
					+ "-fx-padding:5");
	}
	
	public DoubleProperty progressProperty() {
		return hpProgressBar.progressProperty();
	}

	public void setProgress(double lifePercentage) {
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
}
