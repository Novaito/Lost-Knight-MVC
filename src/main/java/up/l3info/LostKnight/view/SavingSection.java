package up.l3info.LostKnight.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import up.l3info.LostKnight.mvc.View;

public class SavingSection extends HBox implements View {
	
	private final Button CANCEL;
	private final Button SAVE;

	@Override
	public void hide() {
		setVisible(false);
	}

	@Override
	public void show() {
		setVisible(true);
	}
	
	private SavingSection() {
		super();
		setAlignment(Pos.CENTER);
		setSpacing(5);
		setPadding(new Insets(10, 0, 10, 0));
		setStyle("-fx-background-color:" + DEFAULT_BACKGROUND_COLOR + ";");
		
		CANCEL = new Button("Cancel");
		SAVE = new Button("Save");
		
		getChildren().addAll(CANCEL, SAVE);
	}
	
	public static SavingSection create() {
		return new SavingSection();
	}
	
	public Button getCancel() {
		return CANCEL;
	}
	
	public Button getSave() {
		return SAVE;
	}

}
