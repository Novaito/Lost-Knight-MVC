package up.l3info.LostKnight.view;

import java.util.List;
import java.util.Map;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import up.l3info.LostKnight.mvc.View;

public class TileSelectorView extends TabPane implements View {
	
	private final SimpleStringProperty heldTexture;
	private SimpleStringProperty typeOfTile;
	
	@Override
	public void hide() {
		setVisible(false);
	}

	@Override
	public void show() {
		setVisible(true);
	}

	private TileSelectorView(Map<String, List<String>> srcFromFold, SimpleStringProperty holdingTexture) {
		super();
		
		setStyle("-fx-background-color:" + DEFAULT_BACKGROUND_COLOR + ";");
		
		setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		this.heldTexture = holdingTexture;
		this.typeOfTile = new SimpleStringProperty();
		
		for (Map.Entry<String, List<String>> set : srcFromFold.entrySet()) {
			getTabs().add(initTab(set.getKey(), set.getValue()));
		}
	}
	
	public static TileSelectorView create(Map<String, List<String>> srcFromFold, SimpleStringProperty holdingTexture) {
		TileSelectorView tileSelector = new TileSelectorView(srcFromFold, holdingTexture);
		
		return tileSelector;
	}
	
	private Tab initTab(String tabName, List<String> srcTiles) {
		Tab tab = new Tab();
		
		tab.setText(tabName);
		
		GridPane assetsGrid = new GridPane();
		String pathPrefix = "/img/" + tabName + "/";
		
		int i=0;
		int j=0;
		for (String tileSrc : srcTiles) {
			TilesView tile = TilesView.create(getClass().getResource(pathPrefix + tileSrc).toExternalForm(), null, i);
			tile.setOnMouseClicked((e) -> {
				if (heldTexture.getValue() != tile.getPrimaryTexture()) {
					System.out.println("Selecting " + tile.getPrimaryTexture());
					heldTexture.set(tile.getPrimaryTexture());
					typeOfTile.setValue(tabName);
				}
			});
			assetsGrid.add(tile, i, j);
			if (i == 2 ) {
				j++;
				i=0;
			} else i++;
		}

		tab.setContent(assetsGrid);
		return tab;
	}
	
	public String selectedTab() {
		String tabName = "";
		for (Tab tab: getTabs()) {
			if (tab.isSelected()) tabName = tab.getText();
		}
		return tabName;
	}
	
	public SimpleStringProperty typeOfTileProperty() {
		return typeOfTile;
	}
}
