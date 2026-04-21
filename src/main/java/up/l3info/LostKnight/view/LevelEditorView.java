package up.l3info.LostKnight.view;

import java.util.List;
import java.util.Map;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.BorderPane;
import up.l3info.LostKnight.mvc.View;

public class LevelEditorView extends BorderPane implements View {

	private LocationBoardOverview locOverview;
	private TileSelectorView tileSelector;
	
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
		setRight(tileSelector);
	}
	
	
	public static LevelEditorView load(Map<String,List<String>> assetsMap, 
											List<TilesView> listImgSrc,
											int sizeX, 
											int sizeY,
											SimpleStringProperty holdingTexture,
											SimpleIntegerProperty tileIndexUpdate) {
		
		LevelEditorView lvlEditView = new LevelEditorView();
		lvlEditView.init(assetsMap, listImgSrc, sizeX, sizeY, holdingTexture, tileIndexUpdate);
		return lvlEditView;
	}
	
	private void init(Map<String,List<String>> assetsMap, 
						List<TilesView> listTiles, 
						int sizeX, 
						int sizeY, 
						SimpleStringProperty holdingTexture,
						SimpleIntegerProperty tileIndexUpdate) {
		
		tileSelector = TileSelectorView.create(assetsMap, holdingTexture);
		locOverview = LocationBoardOverview.create(listTiles, sizeX, sizeY, holdingTexture, tileIndexUpdate, tileSelector.typeOfTileProperty());
	}
	
	private void init(Map<String,List<String>> assetsMap,
						int sizeX, 
						int sizeY,
						SimpleStringProperty holdingTexture,
						SimpleIntegerProperty tileIndexUpdate) {
		
		tileSelector = TileSelectorView.create(assetsMap, holdingTexture);
		locOverview = LocationBoardOverview.create(sizeX, sizeY, holdingTexture, tileIndexUpdate, tileSelector.typeOfTileProperty());
	}
	
	public String getSelectedTab() {
		return tileSelector.selectedTab();
	}
}
