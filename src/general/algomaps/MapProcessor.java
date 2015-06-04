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
import general.algodata.PrototypeCollector;
import java.util.ArrayList;
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
    public static void initMapProcessor() {
        //load tileset data
        PrototypeCollector.loadMapTilesData();
        
        currentMapBuffer = new AllMaps(); //initializing currently used MapBuffer
        currentMapBuffer.generalMap.add(new FragmentMap());
        currentMapBuffer.generalMap.get(0).fragmentContainer.add(new MapChunk());
    }
    public static void testFillMap() { //test routine of map
        MapChunk theMapChunk = currentMapBuffer.generalMap.get(0).fragmentContainer.get(0);
        //making borders of theMapChunk with walls
        for (int i=0; i<(GeneralParam.ChunkWidth*GeneralParam.ChunkHeight); i++) {
            theMapChunk.ChunkMapContainer.add(new MapTile(1));
        }
        
    }
    /**
     * finds a neighbour MapChunk. Service routine, used in MapDisplay routines
     * @param initialChunkCoord - index of MapChunk for which we need to get an index
     * @param initialFragmentCoord - index of MapFragment. A chunk may be contained in another map fragment
     * @param movementDirection - direction that shows how to find neighbour: 
     * NW:2, N:3, NE:4, W:1, E:5, SW:8, S:7, SE:6
     * @return ArrayList. First number contains a coordinate of MapChunk, second number contains number of MapFragment
     */
    public static java.util.ArrayList getNeighbourMapArea(Integer initialChunkCoord, Integer initialFragmentCoord, Integer movementDirection) {
        java.util.ArrayList<Integer> result = new java.util.ArrayList<>();
        result.add(initialChunkCoord); result.add(initialFragmentCoord);
        switch (movementDirection) {
            case 1: { //moving to west
                switch (initialChunkCoord) {
                    //not switching MapFragment
                    case 0: { result.set(0, 1); break; }
                    case 3: { result.set(0, 2); break; }
                    case 7: { result.set(0, 8); break; }
                    case 4: { result.set(0, 3); break; }
                    case 5: { result.set(0, 0); break; }
                    case 6: { result.set(0, 7); break; }
                    //switching MapFragment, to left
                    case 2: { 
                        result.set(0, 4); 
                        switch (initialFragmentCoord) {
                            case 0: { result.set(1, 1); break;}
                            case 3: { result.set(1, 2); break;}
                            case 7: { result.set(1, 8); break;}
                            case 2:
                            case 1:
                            case 8: {throw new UnsupportedOperationException("moving away from AllMaps array from "+initialFragmentCoord);}
                            case 4: { result.set(1, 3); break;}
                            case 5: { result.set(1, 0); break;}
                            case 6: { result.set(1, 7); break;}
                            default: {
                                throw new IllegalArgumentException("Invalid Fragment:"+initialFragmentCoord);
                            }
                        }
                        break; 
                    }
                    case 1: {
                        result.set(0, 5); 
                        switch (initialFragmentCoord) {
                            case 0: { result.set(1, 1); break;}
                            case 3: { result.set(1, 2); break;}
                            case 7: { result.set(1, 8); break;}
                            case 2:
                            case 1:
                            case 8: {throw new UnsupportedOperationException("moving away from AllMaps array from "+initialFragmentCoord);}
                            case 4: { result.set(1, 3); break;}
                            case 5: { result.set(1, 0); break;}
                            case 6: { result.set(1, 7); break;}
                            default: {
                                throw new IllegalArgumentException("Invalid Fragment:"+initialFragmentCoord);
                            }
                        }
                        break;
                    }
                    case 8: {
                        result.set(0, 6); 
                        switch (initialFragmentCoord) {
                            case 0: { result.set(1, 1); break;}
                            case 3: { result.set(1, 2); break;}
                            case 7: { result.set(1, 8); break;}
                            case 2:
                            case 1:
                            case 8: {throw new UnsupportedOperationException("moving away from AllMaps array from "+initialFragmentCoord);}
                            case 4: { result.set(1, 3); break;}
                            case 5: { result.set(1, 0); break;}
                            case 6: { result.set(1, 7); break;}
                            default: {
                                throw new IllegalArgumentException("Invalid Fragment:"+initialFragmentCoord);
                            }
                        }
                        break;
                    }
                }
                break;
            }
            case 2: { //moving north-west
                Boolean switchChunk = false;
                switch (initialChunkCoord) {
                    case 2: { //switching Fragment
                        result.set(0,6);
                        switchChunk=true;
                        break;
                    }
                    case 3: { //switching Fragment
                        result.set(0,8);
                        switchChunk=true;
                        break;
                    }
                    case 4: { //switching Fragment
                        result.set(0, 7);
                        switchChunk=true;
                        break;
                    }
                    case 1: { //switching Fragment
                        result.set(1, 4);
                        switchChunk=true;
                        break;
                    }
                    case 0: { //not switchng Fragment
                        result.set(0, 2);
                        break;
                    }
                    case 5: { //not switching Fragment
                        result.set(0, 3);
                        break;
                    }
                    case 8: { //switching Fragment
                        result.set(0, 5);
                        switchChunk=true;
                        break;
                    }
                    case 7: { //not switching Fragment
                        result.set(0, 1);
                        break;
                    }
                    case 6: { //not switching
                        result.set(0, 0);
                        break;
                    }
                    default: {
                       throw new IllegalArgumentException("Invalid Chunk:"+initialChunkCoord);
                            }
                }
                if (switchChunk==true) {
                    switch (initialFragmentCoord) {
                            case 0: { result.set(1, 2); break;}
                            case 7: { result.set(1, 8); break;}
                            case 2:
                            case 3:
                            case 4:
                            case 1:
                            case 8: {throw new UnsupportedOperationException("moving away from AllMaps array from "+initialFragmentCoord);}
                            case 5: { result.set(1, 3); break;}
                            case 6: { result.set(1, 7); break;}
                            default: {
                                throw new IllegalArgumentException("Invalid Fragment:"+initialFragmentCoord);
                            }
                        }
                }
                break;
            }
            case 3: { //moving north
                Boolean switchChunk = false;
                switch (initialChunkCoord) {
                    case 2: {
                        result.set(0,8);
                        switchChunk = true;
                        break;
                    }
                    case 3: {
                        result.set(0,7);
                        switchChunk = true;
                        break;
                    }
                    case 4: {
                        result.set(0,4);
                        switchChunk = true;
                        break;
                    }
                    
                    case 1: {
                        result.set(0, 2);
                        break;
                    }
                    case 0: {
                        
                        break;
                    }
                    case 5: {
                        
                        break;
                    }
                    case 8: {
                        
                        break;
                    }
                    case 7: {
                        
                        break;
                    }
                    case 6: {
                        
                        break;
                    }
                    default: {
                       throw new IllegalArgumentException("Invalid Chunk:"+initialChunkCoord);
                            }
                }
                break;
            }
            case 4: { //moving north-east
                switch (initialChunkCoord) {
                    case 2: {
                        
                        break;
                    }
                    case 3: {
                        
                        break;
                    }
                    case 4: {
                        
                        break;
                    }
                    case 1: {
                        
                        break;
                    }
                    case 0: {
                        
                        break;
                    }
                    case 5: {
                        
                        break;
                    }
                    case 8: {
                        
                        break;
                    }
                    case 7: {
                        
                        break;
                    }
                    case 6: {
                        
                        break;
                    }
                    default: {
                       throw new IllegalArgumentException("Invalid Chunk:"+initialChunkCoord);
                            }
                }
                break;
            }
            case 5: { //moving east
                switch (initialChunkCoord) {
                    case 2: {
                        
                        break;
                    }
                    case 3: {
                        
                        break;
                    }
                    case 4: {
                        
                        break;
                    }
                    case 1: {
                        
                        break;
                    }
                    case 0: {
                        
                        break;
                    }
                    case 5: {
                        
                        break;
                    }
                    case 8: {
                        
                        break;
                    }
                    case 7: {
                        
                        break;
                    }
                    case 6: {
                        
                        break;
                    }
                    default: {
                       throw new IllegalArgumentException("Invalid Chunk:"+initialChunkCoord);
                            }
                }
                break;
            }
            case 6: { //moving south-east
                switch (initialChunkCoord) {
                    case 2: {
                        
                        break;
                    }
                    case 3: {
                        
                        break;
                    }
                    case 4: {
                        
                        break;
                    }
                    case 1: {
                        
                        break;
                    }
                    case 0: {
                        
                        break;
                    }
                    case 5: {
                        
                        break;
                    }
                    case 8: {
                        
                        break;
                    }
                    case 7: {
                        
                        break;
                    }
                    case 6: {
                        
                        break;
                    }
                    default: {
                       throw new IllegalArgumentException("Invalid Chunk:"+initialChunkCoord);
                            }
                }
                break;
            }
            case 7: { //moving south
                switch (initialChunkCoord) {
                    case 2: {
                        
                        break;
                    }
                    case 3: {
                        
                        break;
                    }
                    case 4: {
                        
                        break;
                    }
                    case 1: {
                        
                        break;
                    }
                    case 0: {
                        
                        break;
                    }
                    case 5: {
                        
                        break;
                    }
                    case 8: {
                        
                        break;
                    }
                    case 7: {
                        
                        break;
                    }
                    case 6: {
                        
                        break;
                    }
                    default: {
                       throw new IllegalArgumentException("Invalid Chunk:"+initialChunkCoord);
                            }
                }
                break;
            }
            case 8: { //moving south-wesst
                switch (initialChunkCoord) {
                    case 2: {
                        
                        break;
                    }
                    case 3: {
                        
                        break;
                    }
                    case 4: {
                        
                        break;
                    }
                    case 1: {
                        
                        break;
                    }
                    case 0: {
                        
                        break;
                    }
                    case 5: {
                        
                        break;
                    }
                    case 8: {
                        
                        break;
                    }
                    case 7: {
                        
                        break;
                    }
                    case 6: {
                        
                        break;
                    }
                    default: {
                       throw new IllegalArgumentException("Invalid Chunk:"+initialChunkCoord);
                            }
                }
                break;
            }
            default: {
                throw new IllegalArgumentException("Invalid direction for neighbour");
            }
        }
        return result;
    }
}
