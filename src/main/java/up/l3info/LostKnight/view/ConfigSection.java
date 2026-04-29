package up.l3info.LostKnight.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import up.l3info.LostKnight.mvc.View;

public class ConfigSection extends VBox implements View {
	
	private final int DEFAULT_X;
	private final int DEFAULT_Y;
	
	private TextField locationName;
	private TextField xField;
	private TextField yField;
	private Button confirmButton;

	@Override
	public void hide() {
		setVisible(false);
	}

	@Override
	public void show() {
		setVisible(true);
	}

	private ConfigSection(int sizeX, int sizeY) {
		super();
		setAlignment(Pos.CENTER);
		setSpacing(3);
		setStyle("-fx-background-color: #959595;");
		DEFAULT_X = sizeX;
		DEFAULT_Y = sizeY;
	}
	
	public static ConfigSection create(int sizeX, int sizeY, boolean isLoading) {
		ConfigSection config = new ConfigSection(sizeX, sizeY);
		config.init(isLoading);
		return config;
	}
	
	private void init(boolean isLoading) {
		Label titleSection = new Label("Blocs");
		titleSection.setStyle("-fx-text-fill: white;");
		
		VBox section = new VBox();
		Label nameLabel = new Label("Nom : ");
		nameLabel.setStyle("-fx-text-fill: white;");
		
		locationName = new TextField();
		locationName.setPrefWidth(120);
		
		HBox nameSec = new HBox();
		nameSec.getChildren().addAll(new Spring(), nameLabel, locationName, new Spring());
		
		confirmButton = new Button("OK");
		if (!isLoading) {
			Label xLabel = new Label("X : ");
			Label yLabel = new Label("Y : ");
			xLabel.setStyle("-fx-text-fill: white;");
			yLabel.setStyle("-fx-text-fill: white;");
			
			xField = new TextField("" + DEFAULT_X);
			yField = new TextField("" + DEFAULT_Y);
			
			xField.setPrefWidth(40);
			yField.setPrefWidth(40);
			
			
			HBox xSec = new HBox();
			HBox ySec = new HBox();
			xSec.getChildren().addAll(new Spring(), xLabel, xField, new Spring());
			ySec.getChildren().addAll(new Spring(), yLabel, yField, new Spring());
			
			section.getChildren().addAll(nameSec, xSec, ySec);
			getChildren().addAll(titleSection, section, confirmButton);
		} else {
			getChildren().addAll(nameSec, confirmButton);
		}
	}
	
	public Integer xBlocks() {
		return (Integer.parseInt(xField.getText()) < DEFAULT_X) ? DEFAULT_X : Integer.parseInt(xField.getText()); 
	}
	
	public Integer yBlocks() {
		return (Integer.parseInt(yField.getText()) < DEFAULT_Y) ? DEFAULT_Y : Integer.parseInt(yField.getText());
	}
	
	public String getLocationName() {
		return locationName.getText();
	}
	
	public Button confirmButton() {
		return confirmButton;
	}
}
