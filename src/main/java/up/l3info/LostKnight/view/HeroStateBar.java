package up.l3info.LostKnight.view;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import up.l3info.LostKnight.mvc.View;

public class HeroStateBar extends HBox implements View {
	
	ProgressBar hpProgressBar;

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
		setSpacing(30);
	}
	
	public static HeroStateBar create(String imgSrc, int lifePercentage) {
		HeroStateBar heroStateBar = new HeroStateBar();
		heroStateBar.init(imgSrc, lifePercentage);
		return heroStateBar;
	}
	
	private void init(String imgSrc, double lifePercentage) {
		Pane profileHero = new Pane();
		profileHero.setStyle("-fx-background-image: url(" + imgSrc + ");");
		
		VBox rightPart = new VBox();
		Label labelHeroName = new Label("Hero");
		
		HBox hpBar = new HBox();
		Label labelHp = new Label("HP :");
		hpProgressBar = new ProgressBar();
		hpProgressBar.setProgress(lifePercentage);
		
		hpBar.getChildren().addAll(labelHp, hpProgressBar);
		rightPart.getChildren().addAll(labelHeroName, hpBar);
		getChildren().addAll(profileHero, rightPart);
	}

	public void setProgress(double lifePercentage) {
		hpProgressBar.setProgress(lifePercentage);
	}
}
