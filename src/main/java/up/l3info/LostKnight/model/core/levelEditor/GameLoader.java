package up.l3info.LostKnight.model.core.levelEditor;

import com.fasterxml.jackson.databind.ObjectMapper;
import up.l3info.LostKnight.model.core.character.GameCharacter;
import up.l3info.LostKnight.model.core.items.Item;
import up.l3info.LostKnight.model.core.map.Exit;
import up.l3info.LostKnight.model.core.map.Location;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameLoader {

    private final File srcLoc;

    private final HashMap<String, Location> locationMap;


    /*--------------------------------CONSTRUCTEUR----------------------------------------*/

    public GameLoader(File src) {
        this.srcLoc = src;
        this.locationMap = new HashMap<>();
    }

    /*-------------------------UTILITAIRES----------------------------------------*/

    private void loadItems(Location loc, HashMap<String, Object> contenue,
                           ObjectMapper mapper) throws IOException {
        Object itemsContenue = contenue.get("Items");
        //si y'a pas d'items on s'arrête
        if (itemsContenue == null)
            return;

        HashMap<String, Object> itemsMap = (HashMap<String, Object>) itemsContenue;

        for (Map.Entry<String, Object> entry : itemsMap.entrySet()) {

            // entry.getValue() est un objet Java générique (LinkedHashMap en pratique)
            // on doit le remettre en String JSON avec writeValueAsString()
            // puis on le re-désérialise avec readValue() en précisant Item.class
            // C'est à ce moment que @JsonTypeInfo sert
            // Jackson lit le champ "type" dans le JSON ("Food" ou "Weapon")
            String json = mapper.writeValueAsString(entry.getValue());
            Item item = mapper.readValue(json, Item.class);
            loc.addItem(item);
        }
    }

    private void loadCharacters(Location p_loc, HashMap<String, Object> p_content,
                                ObjectMapper mapper) throws IOException {
        Object charsContenue = p_content.get("characters");
        if (charsContenue == null) return;

        HashMap<String, Object> charsMap = (HashMap<String, Object>) charsContenue;

        for (Map.Entry<String, Object> entry : charsMap.entrySet()) {
            // Même principe que pour les items :
            String json = mapper.writeValueAsString(entry.getValue());
            GameCharacter character = mapper.readValue(json, GameCharacter.class);
            p_loc.addCharacters(character);
        }
    }

    private void loadExits(Location p_loc, HashMap<String, Object> p_content) {
        Object exitsContenue = p_content.get("exits");
        if (exitsContenue == null) return;

        HashMap<String, Object> exitsMap = (HashMap<String, Object>) exitsContenue;

        for (Map.Entry<String, Object> entry : exitsMap.entrySet()) {
            HashMap<String, Object> exitData = (HashMap<String, Object>) entry.getValue();
            String leadsTo = (String) exitData.get("leadsTo");
            int posX = (int) exitData.get("posX");
            int posY = (int) exitData.get("posY");

            //comme on a préalablement fait toutes les loc
            //on peut faire ça
            Location target = this.locationMap.get(leadsTo);
            if (target != null) {
                Exit exit = new Exit(entry.getKey(), target, posX, posY);
                p_loc.addExit(exit);
            }
        }
    }


    /*-------------------------CHARGEMENT---------------------------------------*/

    public Location load(String locationName) throws IOException {

        // C'est lui qui fait tout le travail de conversion JSON
        ObjectMapper mapper = new ObjectMapper();


        // readValue() lit le fichier JSON et le convertit en objet Java
        // constructMapType() sert à préciser le type générique de la map
        HashMap<String, Object> map = mapper.readValue(
                this.srcLoc,
                mapper.getTypeFactory().constructMapType(HashMap.class, String.class, Object.class)
        );

        // Passe 1 : créer toutes les Location vides d'abord
        // On est obligé de le faire AVANT de charger les exits
        // Sinon ça va déconner genre si la foret emmène vers la prairie mais que la prairie
        // n'existe pas encore 
        // (modifié: Thomas)
        for (String locName : map.keySet()) {
            HashMap<String, Object> locContent = (HashMap<String, Object>) map.get(locName);
            
            int sizeX = (int) locContent.getOrDefault("sizeX", 15);
            int sizeY = (int) locContent.getOrDefault("sizeY", 15);

            // Extraction des tiles
            String[] tilePaths = new String[0];
            HashMap<String, Object> tilesMap = (HashMap<String, Object>) locContent.get("tiles");
            if (tilesMap != null) {
                List<String> floorList = (List<String>) tilesMap.get("floor");
                if (floorList != null) {
                    tilePaths = floorList.toArray(new String[0]);
                }
            }

            this.locationMap.put(locName, new Location(locName, "", tilePaths, sizeX, sizeY));
        }

        // Passe 2 : maintenant que toutes les locations existent,
        // on peut remplir leurs items, characters et exits 
        // (modifié: Thomas)
        for (String locName : map.keySet()) {
            Location loc = this.locationMap.get(locName);
            HashMap<String, Object> locContent = (HashMap<String, Object>) map.get(locName);

            loadItems(loc, locContent, mapper);
            loadCharacters(loc, locContent, mapper);
            loadExits(loc, locContent);
        }

        // On retourne la première location comme spawn
        return this.locationMap.get(locationName);
    }


}