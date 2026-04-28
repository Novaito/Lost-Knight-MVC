package up.l3info.LostKnight.view;

import java.util.List;
import java.util.Map;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import up.l3info.LostKnight.mvc.View;

public class LevelEditorView extends BorderPane implements View {

	private LocationBoardOverview locOverview;
	private EditorSidePanelView sidePanel;
	
	@Override
	public void hide() {
		setVisible(false);
	}

	@Override
	public void show() {
		setVisible(true);
	}

	private LevelEditorView() {
		super();
	}
	
	public static LevelEditorView create(Map<String,List<String>> assetsMap,
											int sizeX, 
											int sizeY,
											SimpleStringProperty holdingTexture,
											SimpleIntegerProperty tileIndexUpdate) {
		
		LevelEditorView lvlEditView = new LevelEditorView();
		lvlEditView.init(assetsMap, sizeX, sizeY, holdingTexture, tileIndexUpdate);
		lvlEditView.setup();
		return lvlEditView;
	}
	
	private void setup() {
		setCenter(locOverview);
		setRight(sidePanel);
	}
	
	
	public static LevelEditorView load(Map<String,List<String>> assetsMap, 
											String[] floorTiles,
											String[] otherTiles,
											int sizeX, 
											int sizeY,
											SimpleStringProperty holdingTexture,
											SimpleIntegerProperty tileIndexUpdate) {
		
		LevelEditorView lvlEditView = new LevelEditorView();
		lvlEditView.init(assetsMap, floorTiles, otherTiles, sizeX, sizeY, holdingTexture, tileIndexUpdate);
		return lvlEditView;
	}
	
	private void init(Map<String,List<String>> assetsMap, 
						String[] floorTiles, 
						String[] otherTiles,
						int sizeX, 
						int sizeY, 
						SimpleStringProperty holdingTexture,
						SimpleIntegerProperty tileIndexUpdate) {
		
		sidePanel = EditorSidePanelView.create(assetsMap, holdingTexture);
		locOverview = LocationBoardOverview.create(floorTiles, otherTiles, sizeX, sizeY, holdingTexture, tileIndexUpdate, sidePanel.typeOfTileProperty());
	}
	
	private void init(Map<String,List<String>> assetsMap,
						int sizeX, 
						int sizeY,
						SimpleStringProperty holdingTexture,
						SimpleIntegerProperty tileIndexUpdate) {
		
		sidePanel = EditorSidePanelView.create(assetsMap, holdingTexture);
		locOverview = LocationBoardOverview.create(sizeX, sizeY, holdingTexture, tileIndexUpdate, sidePanel.typeOfTileProperty());
		
		
	}
	
	public String getSelectedTab() {
		return sidePanel.getSelectedTab();
	}

	public Button getCancel() {
		return sidePanel.getCancel();
	}
	
	public Button getSave() {
		return sidePanel.getSave();
	}
}
