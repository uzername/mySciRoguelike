/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import java.util.ArrayList;

/**
 * Single map fragment. Consists of 8 ScreenMap
 * @author ivan
 */
public class FragmentMap {
    public ArrayList<MapChunk> fragmentContainer = new ArrayList<>(general.algodata.GeneralParam.numberChunks);
}
