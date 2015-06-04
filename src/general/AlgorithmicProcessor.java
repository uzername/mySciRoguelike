package general;

import general.algomaps.MapDisplay;
import general.algomaps.MapProcessor;

/**
 * Another class with static-only routines, 
 * which handles:
 * 'new game' data generation (initial map generation, character generation), 
 * 'save game' data dumping
 * 'load game' data retrieving from save
 * @author ivan
 */
public class AlgorithmicProcessor {
    public static void newGameDataGeneration() {
        //init (generate and load) map (in MapProcessor)
            MapProcessor.initMapProcessor();
            MapProcessor.testFillMap();
        //init player (in Player.java)
                //no need for this here, getPlayer initializes Player internal record by itself 
                //and it is called many times, especially in drawmap routines
                //it's good to make adjustments to Player entity here
            general.Player.getPlayer(); 
        //draw map and everything in it.
            MapDisplay.renderMap();
    }
    public static void loadGameDataRetrieval() {
        
    }
}
