/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import java.util.ArrayList;

/**
 * structure for holding the smallest map segment
 * @author ivan
 */
public class MapChunk {
    //public ArrayList<MapTile> ChunkMapContainer = new ArrayList<>(general.algodata.GeneralParam.ChunkHeight*general.algodata.GeneralParam.ChunkWidth);
    public ArrayList<ArrayList<MapTile>> ChunkMapContainer = null;
    //to save memory we may store uniformly filled MapChunk in a different manner
    //(for chunks which are filled with one MapTile, such as sky chunk for example)
    /**
     * if uniformTile is not null then this MapChunk is filled only with this MapTile
     * and ChunkMapContainer is ignored
     */
    public MapTile uniformTile = null; 
}
