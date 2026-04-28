package up.l3info.LostKnight.model.core.levelEditor;

import com.fasterxml.jackson.databind.ObjectMapper;
import up.l3info.LostKnight.model.core.character.GameCharacter;
import up.l3info.LostKnight.model.core.game.Game;
import up.l3info.LostKnight.model.core.items.Item;
import up.l3info.LostKnight.model.core.map.Exit;
import up.l3info.LostKnight.model.core.map.Location;
import up.l3info.LostKnight.model.core.miscellaneous.GameObject;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class GameSaver {

    //Tout ce qui concerne le jeu : Hero,spawn, item.. MAIS pas les exit
    private File srcLoc;

    //Hero,spawn
    private Game game;

    //Map globale
    //a partir du nom de la loc, ça renvoie 3 champs qui ont eux-meme une map
    //voir illustration
    private HashMap<String,HashMap<String , Object>> gameMap;

    //Comme j'arrivais pas à gérer une récursion infini
    //je mets une liste des locations dejà visitées
    private ArrayList<String> visited;



    /*-----------------------------Illustration-------------------------------------------*/

    // { "Meadow": {
    //     "items": { ""
    //              },
    //     "characters": HashMap<String, GameCharacter>,
    //     "exits":      HashMap<String, Object>
    // }}



    /*--------------------------------CONSTRUCTEUR----------------------------------------*/
    public GameSaver(File src,Game game){
        this.game = game;
        this.srcLoc = src;
        this.gameMap = new HashMap<>();
        this.visited = new ArrayList<>();
    }

    /*---------------------------GETTERS-----------------------------------------*/
    //getter
    public File getSrcLoc(){ return this.srcLoc; }

    public HashMap<String, Object> getLocObject(String loc) {
        return gameMap.get(loc);
    }

    public HashMap<String, HashMap<String,Object>> getGameMap() {return gameMap; }

    /*-----------------------------SETTERS----------------------------------------------*/

    //normalement pas de setters mais je vais les faire dans le doute
    public void setSrcLoc(File srcLoc) {this.srcLoc = srcLoc;}


    /*-----------------------------------------------------------------------------------*/

    // on remplace la référence Location par son nom (String)
    // Sinon on avait une boucle infinie, là on stock juste le nom
    //mais comme le nom est unique, c'est good
    //on reste sur Object comme ça on stock tout ce q'uon veut
    private HashMap<String, Object> exitToMap(Exit exit) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("leadsTo", exit.getLocation().getName());
        map.put("posX",    exit.getPosX());
        map.put("posY",    exit.getPosY());
        return map;
    }
    //On ne fera pas l'équivalent genre itemToMap parcequ'avec les items
    //il n'y a pas de serpent qui se mord la queue comme là


    public void addAllLoc(){
        //clear des map et listes de base, par mesure de sécurité
        visited.clear();
        gameMap.clear();
        addAllLocAux(game.getSpawn());
    }

    private void addAllLocAux(Location loc){
        if(visited.contains(loc.getName()))
            return;
        visited.add(loc.getName());

        HashMap<String, Object> locData = new HashMap<>();
        HashMap<String, Item> items = new HashMap<>();
        HashMap<String, GameCharacter> chars = new HashMap<>();
        HashMap<String, Object> exits = new HashMap<>();

        for(Item i : loc.getItems().values())
            items.put(i.getName(),i);

        for(GameCharacter c : loc.getCharacters().values())
            chars.put(c.getName(),c);

        for(Exit e : loc.getExits().values())
            exits.put(e.getName(),exitToMap(e));

        locData.put("sizeY", loc.getSizeY());
        locData.put("sizeX", loc.getSizeX());
        locData.put("tiles", loc.getTiles());
        locData.put("Items" , items);
        locData.put("characters" , chars);
        locData.put("exits" , exits);

        gameMap.put(loc.getName() , locData);

        //récursion
        for(Exit e : loc.getExits().values())
            addAllLocAux(e.getLocation());
    }



    /*--------------------------------SERIALISATION---------------------------------------*/
    //Méthodes pour sérialiser, en privé car elle sert juste après
    private void serializeGame(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(srcLoc, gameMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Save the game with adding new location
     * @param newLocation can be null
     */
    public void save(Location newLocation){
        addAllLoc();
        if (newLocation != null) {
        	addAllLocAux(newLocation);
        }
        serializeGame();
    }

}
