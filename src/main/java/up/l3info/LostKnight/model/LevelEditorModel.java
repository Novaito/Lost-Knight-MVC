package up.l3info.LostKnight.model;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import up.l3info.LostKnight.model.core.character.GameCharacter;
import up.l3info.LostKnight.model.core.character.Orc;
import up.l3info.LostKnight.model.core.game.Game;
import up.l3info.LostKnight.model.core.items.Food;
import up.l3info.LostKnight.model.core.items.Item;
import up.l3info.LostKnight.model.core.levelEditor.GameLoader;
import up.l3info.LostKnight.model.core.levelEditor.GameSaver;
import up.l3info.LostKnight.model.core.map.Location;
//import up.l3info.LostKnight.model.core.levelEditor.GameLoader;
import up.l3info.LostKnight.mvc.Model;

public class LevelEditorModel implements Model {

	private Game game;
	private String locName;
	private String[] backLayerSrc;
	private String[] upLayerSrc;
	private int sizeX;
	private int sizeY;
	private final String SAVE_FILE = "./save/gamesave.json";
	
	@Override
	public void run() {}
	
	public LevelEditorModel(Game game, String locName, int sizeX, int sizeY, boolean isExist) {
		this.game = game;
		this.locName = locName;
		
		if (isExist) {
			GameLoader loader = new GameLoader(new File(SAVE_FILE));
			Location loc;
			try {
				loc = loader.load(locName);
				this.sizeX = loc.getSizeX();
				this.sizeY = loc.getSizeY();
				backLayerSrc = loc.getTiles();
				upLayerSrc = new String[loc.getSizeX()*loc.getSizeY()];
				
				addOtherTiles(loc);
			} catch (IOException e) {
				System.out.println(">>> Error loading file saving <<<");
			}
			
		} else {
			this.sizeX = sizeX;
			this.sizeY = sizeY;
			backLayerSrc = new String[sizeX*sizeY];
			upLayerSrc = new String[sizeX*sizeY];			
		}
		
	}
	
	public void updateTileSrc(String tileSrc, int index) {
		String[] splitPath = tileSrc.split("/");
		String layerType = splitPath[splitPath.length - 2];
		if (layerType.equals("sprite") || layerType.equals("items")) addSpriteSrc(tileSrc, index);
		else addFloorSrc(tileSrc, index);
	}
	
	private void addFloorSrc(String tileSrc, int index) {
		backLayerSrc[index] = formatPath(tileSrc);
	}
	
	private void addSpriteSrc(String tileSrc, int index) {
		upLayerSrc[index] = formatPath(tileSrc);
	}
	
	private void addOtherTiles(Location loc) {
		for(GameCharacter gc: loc.getCharacters().values()) {
			upLayerSrc[gc.getPosX() * gc.getPosY()] = gc.getTilePath();
		}
		for(Item it: loc.getItems().values()) {
			upLayerSrc[it.getPosX() * it.getPosY()] = it.getTilePath();
		}
	}
	
	public String[] getFloorTiles() {
		return backLayerSrc;
	}
	
	public String[] getOthersTiles() {
		return upLayerSrc;
	}
	
	public Map<String, List<String>> extractAssets(String path) {
		Map<String, List<String>> assetsMap = new HashMap<>();
		
		URI url;
		try {
			url = getClass().getResource(path).toURI();
		} catch (URISyntaxException e) {
			System.out.println(e.getStackTrace());
			return assetsMap;
		}
		
		File imgDir = new File(url);
		
		for (final File f: imgDir.listFiles()) {
			if (f.isDirectory()) {
				List<String> listFile = new ArrayList<>();
				
				String[] dirList = f.getPath().split("/");
				String dirName = dirList[dirList.length-1];
				
				Stream.of(f.listFiles())
					.filter(file -> !file.isDirectory())
					.forEach(file -> listFile.add(path + dirName + "/" + file.getName()));
				// Debug paths
				//Stream.of(listFile).forEach(x -> System.out.println(x));
				
				assetsMap.put(f.getName(), listFile);
			}
		}
		
		return assetsMap;
	}
	
	public void save() {
		Location loc = new Location(locName, "", backLayerSrc, sizeX, sizeY);
		int j=1;
		int k=1;
		for (int i=0; i<upLayerSrc.length; i++) {
			if (upLayerSrc[i] != null ) {
				String type = getTileType(upLayerSrc[i]);
				if (type.equals("sprite")) {
					loc.addCharacters(new Orc(type + j, 100, null, upLayerSrc[i], null, (i / sizeX), (i % sizeY)));
					j++;
				}
				if (type.equals("item")) {
					loc.addItem(new Food(type + k, 15, upLayerSrc[i], (i / sizeX), (i % sizeY)));
					k++;
				}				
			}
		}
		
		File json = new File(SAVE_FILE);
		
		GameSaver saver = new GameSaver(json, game);
		saver.save(loc);
		
	}
	
	public int getSizeX() {
		return sizeX;
	}
	
	public int getSizeY() {
		return sizeY;
	}
	
	private final String getTileType(String absolutePath) {
		String[] splitPath = absolutePath.split("/");
		return splitPath[splitPath.length - 2];
	}
	
	private final String formatPath(String absolutePath) {
		String[] splitPath = absolutePath.split("/");
		StringBuilder sb = new StringBuilder();
		for (int i=3; i>0; i--) {
			sb.append("/");
			sb.append(splitPath[splitPath.length - i]);	
		}
		return sb.toString();
	}
}
