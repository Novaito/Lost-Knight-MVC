package up.l3info.LostKnight.model.core.levelEditor;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    //Map qui prends d'abord le nom de la loc, qui renvoie une deuxième map où avec le nom d'un gameobject le renvoie
    private HashMap<String,HashMap<String , ? extends  GameObject>> gameMap;

    //ce qui concerne uniquement les exit
    private File srcExit;
    private HashMap<String, Exit> exitMap;

    //Hero,spawn
    private Game game;

    /*-----------------------------Illustration-------------------------------------------*/

    /*
     * srcExit.json
     * [{"nom1" : Sortie1,
     *  {"nom2" : Sortie2}]
     *
     * exemple avec les exits mais y'aura aussi les items, monstre, etc
     * srcLoc.json
     * [{"nom" : "Forest",
     *    "exit : [0,1],
     *    "exitPos" : [{"x","y"} , {"x","y"}]
     *   {"nom" : "city",
     *    "exits" : [0];
     * }]
     *
     * Dans ce cas, Forest a 2 exits
     *
     * */



    /*--------------------------------CONSTRUCTEUR----------------------------------------*/
    public GameSaver(File src){
        try {
            this.srcLoc = src;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.gameMap = new HashMap<>();

    }

    /*---------------------------GETTERS-----------------------------------------*/
    //getter
    public File getSrcLoc(){ return this.srcLoc; }

    public HashMap<String, ? extends GameObject> getLocObject(String loc) {
        return gameMap.get(loc);
    }

    public HashMap<String, HashMap<String, ? extends GameObject>> getGameMap() {return gameMap; }

    /*-----------------------------SETTERS----------------------------------------------*/

    //normalement pas de setters mais je vais les faire dans le doute
    public void setSrcLoc(File srcLoc) {this.srcLoc = srcLoc;}

    public void setObject(String loc , HashMap<String , ? extends GameObject> object){
        this.gameMap.put(loc,object);
    }

    public void setGameMap(HashMap<String, HashMap<String, ? extends GameObject>> gameMap) {this.gameMap = gameMap;}

    /*---------------------------------------------------------------------------------*/

    //privé car normalement j'en aurais besoin qu'ici
    private void addLoc(String loc , HashMap<String, ? extends GameObject> locObject){
        gameMap.putIfAbsent(loc , locObject);
    }

    private void addExit(Exit e) {
        this.exitMap.putIfAbsent(e.getName(), e);
    }

    private void addAllExitAux(Location loc){
        loc.getExits().forEach((str , e) -> {
            addExit(e);
            addAllExitAux(e.getLocation());
        });
    }

    public void addAllExit(){
        game.getSpawn().getExits().forEach((str,e) ->{
            addExit(e);
            addAllExitAux(game.getSpawn());
        });
    }



    private void addAllLocAux(Location loc){
            addLoc(loc.getName() , (HashMap<String, Item>) loc.getItems());
            loc.getExits().forEach((str , e) -> {
                if(!this.gameMap.containsKey(e.getLocation()))
                addAllLocAux(e.getLocation());
            });

    }

    public void addAllLoc(){
            addAllLocAux(game.getSpawn());
    }











    /*--------------------------------SERIALISATION---------------------------------------*/
    //Méthodes pour sérialiser
    public void serializeGame(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(srcLoc, gameMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void serializeExit(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(srcExit, exitMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
