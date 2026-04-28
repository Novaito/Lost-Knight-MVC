package up.l3info.LostKnight.view;

import java.util.List;
import java.util.Map;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import up.l3info.LostKnight.mvc.View;

public class EditorSidePanelView extends VBox implements View {
	
	private TileSelectorView tileSelector;
	private SavingSection savingSection;

	@Override
	public void hide() {
		setVisible(false);
	}

	@Override
	public void show() {
		setVisible(true);
	}

	private EditorSidePanelView() {
		super();
	}
	
	public static EditorSidePanelView create(Map<String, List<String>> srcFromFold, SimpleStringProperty holdingTexture) {
		EditorSidePanelView sidePanel = new EditorSidePanelView();
		sidePanel.init(srcFromFold, holdingTexture);
		return sidePanel;
	}
	
	private void init(Map<String, List<String>> srcFromFold, SimpleStringProperty holdingTexture) {
		tileSelector = TileSelectorView.create(srcFromFold, holdingTexture);
		savingSection = SavingSection.create();
		
		VBox.setVgrow(tileSelector, Priority.ALWAYS);
		getChildren().addAll(tileSelector, savingSection);
		
	}
	
	public Button getCancel() {
		return savingSection.getCancel();
	}
	
	public Button getSave() {
		return savingSection.getSave();
	}
	
	public String getSelectedTab() {
		return tileSelector.selectedTab();
	}
	
	public SimpleStringProperty typeOfTileProperty() {
		return tileSelector.typeOfTileProperty();
	}
}
