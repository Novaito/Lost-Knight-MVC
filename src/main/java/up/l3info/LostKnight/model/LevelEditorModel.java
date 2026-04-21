package up.l3info.LostKnight.model;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

//import up.l3info.LostKnight.model.core.levelEditor.GameLoader;
import up.l3info.LostKnight.mvc.Model;

public class LevelEditorModel implements Model {

	private String[] backLayerSrc;
	private String[] upLayerSrc;
	private boolean isExistedBefore;
	
	
	// TODO : Faire une diff entre item/sprite et le reste
	
	@Override
	public void run() {}
	
	public LevelEditorModel(String locName, int size, boolean isExist) {
		// IMPORTANT : Quand le loader sera implémenté 
		//tilesSrc = isExist ? GameLoader.getTilesFromLocation(locName) : new ArrayList<String>();
		tilesSrc = new String[size*size];
		
		isExistedBefore = isExist;
	}
	
	public void updateTileSrc(String tileSrc, int index) {
		System.out.print(tilesSrc[index] + " -> ");
		tilesSrc[index] =  tileSrc;
		System.out.println(tilesSrc[index]);
	}
	
	public String[] getTiles() {
		return tilesSrc;
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
		//System.out.println(imgDir.getAbsolutePath() + " " + imgDir.isDirectory());
		
		for (final File f: imgDir.listFiles()) {
			if (f.isDirectory()) {
				List<String> listFile = new ArrayList<>();
				Stream.of(f.listFiles())
					.filter(file -> !file.isDirectory())
					.forEach(file -> listFile.add(file.getName()));
				// Debug paths
				//Stream.of(listFile).forEach(x -> System.out.println(x));
				
				assetsMap.put(f.getName(), listFile);
			}
		}
		
		return assetsMap;
	}
}
