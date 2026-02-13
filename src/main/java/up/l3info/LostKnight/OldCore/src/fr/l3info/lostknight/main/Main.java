   package fr.l3info.lostknight.main;


import java.util.ArrayList;


import fr.l3info.lostknight.character.Darriwil;
import fr.l3info.lostknight.character.Dragon;
import fr.l3info.lostknight.character.Hero;
import fr.l3info.lostknight.character.Npc;
import fr.l3info.lostknight.character.Orc;
import fr.l3info.lostknight.character.Undead;
import fr.l3info.lostknight.game.Game;
import fr.l3info.lostknight.items.Chest;
import fr.l3info.lostknight.items.Food;
import fr.l3info.lostknight.items.Item;
import fr.l3info.lostknight.items.Key;
import fr.l3info.lostknight.items.MagicWeapon;
import fr.l3info.lostknight.items.Potion;
import fr.l3info.lostknight.items.Weapon;
import fr.l3info.lostknight.map.Exit;
import fr.l3info.lostknight.map.Location;
import fr.l3info.lostknight.map.LockedExit;
import fr.l3info.lostknight.spells.DamageSpell;
import fr.l3info.lostknight.spells.HealSpell;
import fr.l3info.lostknight.spells.StaminaSpell;
import fr.l3info.lostknight.spells.StarvationSpell;
import fr.l3info.lostknight.spells.WeaknessSpell;

public class Main {
    public static void main(String[] args){

    	String dialogsPath = "/dialogs/";
    	//String dialogsPath = "src/fr/l3info/lostknight/ressources/dialogs/";
    	
    	
        Hero hero = new Hero("Lost Knight" ,50, "Who Am i ?");
        Game game;
        Weapon rbs = new Weapon("Rusty_broken_sword", 2);
        Food mushroom = new Food("Mushroom??", -5);
        hero.addItem(rbs);
        hero.addItem(mushroom);

        // ALL EXIT , pas les lockedDoor
        Exit toVillage;
        Exit toMeadow;
        Exit toMayor;
        Exit toTreasurer;
        Exit toTreasurerFloor;
        Exit toLostWoods;
        Exit toDeepLostWoods;
        Exit toBanishedZone;
        Exit toStorage;
        Exit toUnderground;
        Exit toInfirmary;
        Exit toForge;
        Exit toCastle;
        Exit toCastleFloor1;
        Exit toF2;
        Exit toArena;
        Exit toGarden;
        Exit toRoof;

        // ALL LOCATIONS?


        //Prairie étrange (lien vers cabanon,village et chateau | une pomme) revoir strawberry ptet
        
        Location strangeMeadow = new Location("Strange_meadow" , "A strange meadow, it looks like the spawn point of a bad game. "); 
        game = new Game(hero , strangeMeadow);

            Location shed = new Location("Shed" , "The shed is locked... There must be a way to get in.\nBut why would I want to get in, actually?");
            toMeadow = new Exit("to_meadow" , strangeMeadow);
            shed.addExit(toMeadow);
        
            //Cabanon (lien vers prairie étrange | coffre -> clé du chateau et une hache)
            Weapon axe = new Weapon("Axe", 6);
            Chest chestShed = new Chest("chestShed" , 2);
            chestShed.addItem(axe);

            shed.addItem(chestShed);

        LockedExit toShed = new LockedExit("Shed_door", strangeMeadow , "The heavy metal padlock prevents me from entering.");
        strangeMeadow.addExit(toShed);

        Food strawberry = new Food("Strawberry", 10);
        strangeMeadow.addItem(strawberry);

        // prairie -> village
        
        Location village = new Location("Village : Quedlinburg" , "A small village with about fifty houses.\nMost of them look dilapidated and in poor condition.");
        toVillage = new Exit("Village", village);
        toMeadow = new Exit("Meadow", strangeMeadow);
        village.addExit(toMeadow);
        strangeMeadow.addExit(toVillage);
        
        //Village (lien vers maison du maire, forêt , maison du trésorier, et le retour vers la prairie | Kalé et Alexander,un puit )
        
        village.addExit(toMeadow);
        
            //Maison du maire, juste le maire, élément si j'ai le temps
            Location mayorHouse = new Location("Mayors_house" , "The mayor's house in Quedlinburg.\n" + 
                                "Contrary to what the exterior might suggest, it is in good condition and the fireplace provides a comforting warmth.");
            mayorHouse.addExit(toVillage);
            Npc mayor = new Npc("Rogier : treasurer's son", dialogsPath + "Mayor.txt", null);
            mayorHouse.addCharacters(mayor);
          
        toMayor = new Exit("Mayor_house", mayorHouse);    
        village.addExit(toMayor);

            //maison du trésorier,le fils du tresorier , a un étage
            Location treasurerHouse = new Location("Treasurers_house" , "A very traditional house.\nIt's cold inside, and there's a whistling sound caused by the wind rushing through the nearly extinguished fireplace.");
            treasurerHouse.addExit(toVillage);
                //étage de la maison de trésorier
                Location treasurerFloor = new Location("Floor_of_the_house" , "Do people really live here ??");
                toTreasurerFloor = new Exit("Treasurer_upstairs", treasurerFloor);
                toTreasurer = new Exit("Treasurers_house", treasurerHouse);
                treasurerFloor.addExit(toTreasurer);
                village.addExit(toTreasurer);
                
                    //coffre et son contenu
                    HealSpell hs10 = new HealSpell("Spell to regen 10HP", 10);
                    Potion healPotion10 = new Potion("Heal_10", hs10);
                    Key keyShed = new Key("Key for the shed, in the meadow" , toShed);                      //A revoir
                    Chest chestTreasurer = new Chest("chestTreasurer" , 2);
                    chestTreasurer.addItem(healPotion10);
                    chestTreasurer.addItem(keyShed);     
                treasurerFloor.addItem(chestTreasurer);
            treasurerHouse.addExit(toTreasurerFloor);
            treasurerHouse.addExit(toVillage);
            Npc rogier = new Npc("Rogier", dialogsPath + "Rogier.txt", null , true);
            treasurerHouse.addCharacters(rogier);

        Npc hanna = new Npc("Hanna", dialogsPath + "Hanna.txt", null , true);
        village.addCharacters(hanna);
        Npc dane = new Npc("Ludwig", dialogsPath + "Ludwig.txt", null, true);
        village.addCharacters(dane);

        
        // village <-> sous-terrain (par le puit)
        Location underground = new Location("Underground", "An underground area accessed by descending through the village well.");
        LockedExit toUndergroundLocked = new LockedExit("Well", village, "You can climb down into the well using a rope.");
        village.addExit(toUndergroundLocked);
        underground.addExit(toVillage);

        //sous-terrain

        Npc solaire = new Npc("Solaire", dialogsPath + "Solaire.txt", null , true);
        underground.addCharacters(solaire);
        Chest undergroundChest = new Chest("Underground_Chest", 0); //coffre vide possible ?
        underground.addItem(undergroundChest);

            //entrepot
            toStorage = new Exit("Storage_door", underground, "The door to the storage room");
            underground.addExit(toStorage);
            toUnderground = new Exit("Underground_door", underground, "The door to the underground");
            Location storageArea = new Location("Storage_area", "A dark room with barely any light.\n There is damaged equipment that cannot be used, but the potions are in perfect condition.");
            storageArea.addExit(toUnderground);

                StaminaSpell ss15 = new StaminaSpell("Stamina_spell_15", 15);
                Potion staminaPotion15 = new Potion("Stamina_potion_15", ss15);
            
                HealSpell hs15 = new HealSpell("Heal_spell_15", 15);
                Potion healPotion15 = new Potion("Heal_potion_15", hs15);

                StarvationSpell starvs15 = new StarvationSpell("Starvation_spell_15", 15);
                Potion starvationPotion15 = new Potion("Starvation_potion_15", starvs15);

                WeaknessSpell ws15 = new WeaknessSpell("Weakness_spell_15", 15);
                Potion weaknessPotion15 = new Potion("Weakness_potion_15", ws15);

                DamageSpell ds15 = new DamageSpell("Damage_spell_15", 15);
                Potion damagePotion15 = new Potion("Damage_potion_15", ds15);

            storageArea.addItem(damagePotion15);
            storageArea.addItem(weaknessPotion15);
            storageArea.addItem(starvationPotion15);
            storageArea.addItem(healPotion15);
            storageArea.addItem(staminaPotion15);

            //Infirmerie
            Location infirmary = new Location("Infirmary", "A makeshift infirmary to treat the wounded as best they could");
            infirmary.addExit(toUnderground);
            toInfirmary = new Exit("Infirmary_door", infirmary);
            underground.addExit(toInfirmary);

            Npc pasteur = new Npc("Pasteur", dialogsPath + "Pasteur.txt", null , true);
            Npc urgas = new Npc("Urgas", ". . .", null);
            infirmary.addCharacters(urgas);
            infirmary.addCharacters(pasteur);

            //forge
            Location forge = new Location("Forge","A simple forge where the heat is unbearable.\nThe sound of the hammer striking the metal echoes throughout the room." );
            toForge = new Exit("Forge_door", forge);
            underground.addExit(toForge);
            forge.addExit(toUnderground);
            Npc brigit = new Npc("Brigit", dialogsPath + "Brigit.txt", null, true);
            forge.addCharacters(brigit);
                //arrière forge
                Location backForge = new Location("Back_forge", "A dark room where the air is dry and dusty.\nThere is a pile of weapons of all kinds.\nA two-handed sword is stuck in a pedestal in the middle of the room.");
                LockedExit toBackForge = new LockedExit("Back_forge_door", backForge, "The locked door to back forge.\nThe orc in the room across the hall stole the key to prevent the villagers from getting weapons.");
                forge.addExit(toBackForge);
                backForge.addExit(toForge);
            
                Weapon zweihander = new Weapon("Zweihander", 10, "Two-handed sword.\nThis is the best weapon forged by Brigit.\nIt's not exceptional either...");
                backForge.addItem(zweihander);

            //Zone combat
            Location arena = new Location("Arena", "The arena where villagers and fighters once trained.\nAn enemy far too powerful for the villagers is now kept locked in the arena by a spell. "); 
            toArena = new Exit("Arena_door", arena);
            underground.addExit(toArena);
            arena.addExit(toUnderground);

            Key keyBackForge = new Key("Key_back_forge", toBackForge);
            ArrayList<Item> lootZamor = new ArrayList<>();
            lootZamor.add(keyBackForge);
            Undead zamor = new Undead("Zamor", 30, null, null , 10);
            arena.addCharacters(zamor);
            


        //village -> bois perdus
        Location lostWoods = new Location("Lost_woods" , "You had never seen such a dense forest.\nIn fact, since you lost your memory, you don't remember any forest at all.");
        toLostWoods = new Exit("Dont_go_here", lostWoods);
        village.addExit(toLostWoods);
        lostWoods.addExit(toVillage);    
        
        
        //bois perdus -> bois très perdus

        Location deepLostWood = new Location( "Depp_lost_woods" , "What's your problem?\nHow long have you been walking there?\n You've already been there, haven't you?");
        toDeepLostWoods = new Exit("RUN_AWAY_!", deepLostWood);
        lostWoods.addExit(toDeepLostWoods);
        deepLostWood.addExit(toVillage);

        //btp -> Zone du banni
        Location banishedZone = new Location( "Banished_zone" , "A sort of clearing with a sort of hole in the ground in the middle" );
        toBanishedZone = new Exit("Banished_zone", banishedZone);
        deepLostWood.addExit(toBanishedZone);
        Key rope = new Key("Well" , toUndergroundLocked);
        ArrayList<Item> lootZombie = new ArrayList<>();
        lootZombie.add(rope);
        Undead zombie = new Undead("zombie", 6, lootZombie, "UUhhh UUuhh-");
        deepLostWood.addCharacters(zombie);

        //Banished zone
        banishedZone.addExit(toVillage);  //je skip les deux zones d'avant qui servent à rien en vrai sauf à l'ambiance
        Food fruit = new Food("strange_fruit", 1);
        strangeMeadow.addItem(fruit);

        Key keyDarriwil = new Key("Key_to_Darriwil_cell");
        ArrayList<Item> treasurerDrop = new ArrayList<>();
        treasurerDrop.add(keyDarriwil);
        Npc treasurer = new Npc("Treasurer", dialogsPath + "Treasurer.txt", treasurerDrop , true);
        banishedZone.addCharacters(treasurer);

        //Banished zone -> gêole
        Location cell =  new Location("Cell" , "A cell holding Darriwil prisoner, a former knight of Quedlinburg who has completely lost his mind");
        LockedExit cellDoor = new LockedExit("Darriwil_cell", cell, "");
        keyDarriwil.setExit(cellDoor);
        banishedZone.addExit(cellDoor);
        
        //Banished zone
            //zone de combat
        Weapon bf = new Weapon("Bloodhound's fang", 20);
        ArrayList<Item> darriwilItem = new ArrayList<>();
        darriwilItem.add(bf);
        Darriwil darriwil = new Darriwil("Darriwil",25 ,darriwilItem , "..");
        cell.addCharacters(darriwil);
        cell.addExit(toBanishedZone);
        


        //prairie -> castle entrance
        Location castleEntrance = new Location("Castle_entrance", "The entrance to the castle is very clean and there are torches on the wall.\nThere are people who live here regularly.\n There are stairs to go tu first floor and a locked trapdoor to go to the basement");
        LockedExit toCastleLocked = new LockedExit("Castle_door",castleEntrance , "");
        Key castleKey = new Key("Key_castle", toCastleLocked);
        castleKey.setExit(toCastleLocked);
        strangeMeadow.addExit(toCastleLocked);
        castleEntrance.addExit(toMeadow);

        
        //castle entrance <- Castle F1
        Location castleFloor1 = new Location("Castle_first_floor", "First floor of the castle, no time to look around, it would be wiser to kill the orc as soon as possible.");
        toCastleFloor1 = new Exit("Stairs_floor_1", castleFloor1, "A spiral staircase leading to the first floor");
        toCastle = new Exit("Castle_entrance", castleEntrance, "Back to the entrance");
        castleEntrance.addExit(toCastleFloor1);
        castleFloor1.addExit(toCastle);
        
        //f1
        Key keyF2 = new Key("Key_2F");
        ArrayList<Item> orcLoot = new ArrayList<>();
        orcLoot.add(keyF2);
        Orc orc = new Orc("Orc", 50 ,orcLoot,"I really don't know how you achieve the castle but your journey stop here");
        castleFloor1.addCharacters(orc);

            //Ajout de la BT dans le coffre, l'arme pour finir le jeu sans combat en gros
            HealSpell forBTspell = new HealSpell("", 15);
            Potion forBTpotion = new Potion("", forBTspell);
            MagicWeapon bloodthirster = new MagicWeapon("Blood_thrister", 15, "Easy win",forBTpotion );
            Chest castleChest = new Chest("Castle_chest", 1);
            castleChest.addItem(bloodthirster);
        castleFloor1.addItem(castleChest);

        //f1 <-> f2
        Location f2 = new Location( "Castle_second_floor" , "The second floor of the castle.\n Contrary to the first floot, it's very cold here.\n" +
                                                                                "You can see someone in the cell in the corner of the room\n He seems to be human.");
        toF2 = new Exit("Stairs_floor_2", f2, "Concrete stairs that contrast with those leading to the first floor");
        f2.addExit(toCastleFloor1);
        castleFloor1.addExit(toF2);

        //f2
        Key keyBasement = new Key("Key_basement");      //Setteur l297
        ArrayList<Item> whoLoot = new ArrayList<>();
        whoLoot.add(keyBasement);
        Npc who = new Npc("Who?", dialogsPath +"Who.txt", whoLoot , true);
        f2.addCharacters(who);

        //entrance <-> basement
        Location basement = new Location("Castle_basement", "Three against one?\nNot fair play.");  
        LockedExit trapdoor = new LockedExit("Entrance_trapdoor", basement, "Heavy and rusty metal trapdoor");
        keyBasement.setExit(trapdoor);
        castleEntrance.addExit(trapdoor);
        basement.addExit(toCastle);

        //basement
        Undead altSoldier = new Undead("Altered_knight", 30, null, null , 5);
        Undead mummy = new Undead("Mummy", 20, null, null , 10);
        Undead dancer = new Undead("Dancer", 10, null, null , 15);
        basement.addCharacters(dancer);
        basement.addCharacters(mummy);
        basement.addCharacters(altSoldier);

            //basement clé, f2 casser mur, garder
            Location garden = new Location("Castle_garden", "A beautifull garden.\n You can tell it's regularly maintained");
            LockedExit wall = new LockedExit("Wall" , garden);
            f2.addExit(wall);
            Key hammer = new Key("hammer", wall);
            Chest basemChest = new Chest("Basement_chest", 1);
            basemChest.addItem(hammer);

        //garden 
        Food apple = new Food("Apple", 10);
        Food pear = new Food("Pear", 15);
        Food mango = new Food("Mango", 18);
        Food nut = new Food("Nut", 12);
        
        ArrayList<Item> listGardenFood = new ArrayList<>();
        listGardenFood.add(pear);
        listGardenFood.add(apple);
        listGardenFood.add(mango);
        listGardenFood.add(nut);
        
        for(Item i : listGardenFood){
            garden.addItem(i);
        }
        
        Key lighter = new Key("Lighter");                 
        ArrayList<Item> lootCorhyn = new ArrayList<>();
        lootCorhyn.add(lighter);
        Npc corhyn = new Npc("Corhyn", dialogsPath + "Corhyn.txt", lootCorhyn , true);
        garden.addCharacters(corhyn);
        
        //garden <-> toit

        Location roof = new Location("Catle_roof", "It's very high !");
        toRoof = new Exit("ladder_Castle_Roof", roof);
        toGarden = new Exit("Garden_ladder", garden);
        garden.addExit(toRoof);
        roof.addExit(toGarden);

        //roof + chambre final
        Location queenRoom = new Location("Queens_Room", "The end ?");
        LockedExit hole = new LockedExit("Hole_roof", null, "A hole is present on the roof.\n Sadly, it seems like someone put a wood board to prevent anyone to entering");
        lighter.setExit(hole);
        lighter.setExit(hole);
        
        //mettre des potions pour se heal avant le combat final

        //final room <-> roof
        queenRoom.addExit(toRoof);
        Dragon dragon = new Dragon("Bayle", 100, null, "");        
        queenRoom.addCharacters(dragon);
        
        game.renderGameHeader();
        
        game.gameLoop();
    }

}
