package up.l3info.LostKnight.view;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.GridPane;
import up.l3info.LostKnight.mvc.View;

public class LocationBoardOverview extends GridPane implements View {

	private final int nbColumns;
	private final int nbRows;
	private List<TilesView> tilesList;
	private final String DEFAULT_TILE = getClass().getResource("/img/defaultTile.png").toExternalForm();
	private SimpleStringProperty typeOfTileHeld;
	private final SimpleStringProperty heldTexture;
	private final SimpleIntegerProperty tileIndexUpdate;
	
	private final boolean CREATE;
	
	@Override
	public void hide() {
		setVisible(false);		
	}

	@Override
	public void show() {
		setVisible(true);
	}
	
	private LocationBoardOverview(List<TilesView> tiles, 
									int sizeX, 
									int sizeY, 
									SimpleStringProperty heldTexture,
									SimpleIntegerProperty tileIndexUpdate,
									SimpleStringProperty tileType) {
		super();
		CREATE = false;
		nbColumns = sizeX;
		nbRows = sizeY;
		this.tilesList = tiles;
		this.heldTexture = heldTexture;
		this.tileIndexUpdate = tileIndexUpdate;
		this.typeOfTileHeld = tileType;
	}
	
	private LocationBoardOverview(int sizeX, 
									int sizeY, 
									SimpleStringProperty heldTexture,
									SimpleIntegerProperty tileIndexUpdate,
									SimpleStringProperty tileType) {
		super();
		CREATE = true;
		nbRows = sizeY;
		nbColumns = sizeX;
		this.heldTexture = heldTexture;
		this.tileIndexUpdate = tileIndexUpdate;
		this.typeOfTileHeld = tileType;
		this.tilesList = new ArrayList<TilesView>();
	}
	
	public static LocationBoardOverview create(int sizeX, 
												int sizeY, 
												SimpleStringProperty heldTexture,
												SimpleIntegerProperty tileIndexUpdate,
												SimpleStringProperty tileType) {
		
		LocationBoardOverview locOverview = new LocationBoardOverview(sizeX, sizeY, heldTexture, tileIndexUpdate, tileType);
		locOverview.init();
		locOverview.style();
		return locOverview;
	}
	
	public static LocationBoardOverview create(List<TilesView> tilesView, 
												int sizeX, 
												int sizeY,
												SimpleStringProperty heldTexture,
												SimpleIntegerProperty tileIndexUpdate,
												SimpleStringProperty tileType) {
		
		LocationBoardOverview locOverView = new LocationBoardOverview(tilesView, sizeX, sizeY, heldTexture, tileIndexUpdate, tileType);
		locOverView.addCells();
		locOverView.style();
		return locOverView;
	}
	
	private void init() {
		//setConstraints();
		initCells();
		addCells();
	}
	
	private void style() {
		setHgap(1);
		setVgap(1);
		setStyle("-fx-background-color:" + DEFAULT_BACKGROUND_COLOR + ";");
	}
	
	private void setEvent(TilesView tile) {
		tile.setOnMouseClicked((e) -> {
			if (heldTexture.getValue() != "" && tile.getPrimaryTexture() != heldTexture.getValue()) {
				if (!typeOfTileHeld.getValue().equals("sprite")) {
					tile.setTile(heldTexture.getValue());							
				} else {
					tile.setSndTile(heldTexture.getValue());
				}
				System.out.println("Index of the tile : " + tile.INDEX);
				tileIndexUpdate.set(tile.INDEX);
			}
		});
	}
	
	private void initCells() {
		for (int i=0; i<nbRows * nbColumns; i++) {
			TilesView tile;
			tile = TilesView.create(DEFAULT_TILE, null, 40, i);
			heldTexture.set(DEFAULT_TILE);
			tileIndexUpdate.setValue(i);
			
			tilesList.add(tile);
			setEvent(tile);
		}
	}
	
	private void addCells() {
		int i=0;
		int j=0;
		for (TilesView view : tilesList) {
			add(view, j, i);
			if (!CREATE) setEvent(view);
			if (j < nbRows - 1) j++;
			else {
				j=0;
				i++;
			}
		}
	}
}
