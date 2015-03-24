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
    public ArrayList<MapTile> ChunkMapContainer = new ArrayList<>(general.algodata.GeneralParam.ChunkHeight*general.algodata.GeneralParam.ChunkWidth);
}
