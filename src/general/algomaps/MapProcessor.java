/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general.algomaps;

import general.AllMaps;
import general.FragmentMap;
import general.MapChunk;
import general.MapTile;
import general.algodata.GeneralParam;
/**
 * contains algorithms that operate on maps.  
 * Uses classes AllMaps, FragmentMap, MapChunk. 
 * @author ivan
 */
public class MapProcessor {
    /**
     * contains the actual game map data
     */
    public static AllMaps currentMapBuffer;
    public MapProcessor() {
        currentMapBuffer = new AllMaps();
    }
    public void testFillMap() {
        currentMapBuffer.generalMap.add( new FragmentMap());
        currentMapBuffer.generalMap.get(0).fragmentContainer.add(new MapChunk());
        MapChunk theMapChunk = currentMapBuffer.generalMap.get(0).fragmentContainer.get(0);
        //making borders of theMapChunk with walls
        for (int i=0; i<(GeneralParam.ChunkWidth*GeneralParam.ChunkHeight); i++) {
            theMapChunk.ChunkMapContainer.add(new MapTile());
        }
        
    }
}
