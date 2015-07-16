package general.algomaps;

import general.AllMaps;
import general.FragmentMap;
import general.MapChunk;
import general.MapTile;
import general.algodata.GeneralParam;
import general.algodata.PrototypeCollector;
import java.util.ArrayList;

/**
 * this class is used to generate dynamic map data during runtime
 * @author ivan
 */
public class MapGenerator {
        public static void testFillMap() { //test routine of map
        //map chunks are initialized by this moment
        MapChunk firstMapChunk = general.algomaps.MapProcessor.currentMapBuffer.generalMap.get(0).fragmentContainer.get(0);
                for (int j=0; j<(GeneralParam.ChunkHeight); j++) {
                    ArrayList<MapTile> singleChunkRow = new ArrayList<>();
                    for (int k=1; k<GeneralParam.ChunkWidth; k++) {
                        singleChunkRow.add(new MapTile(1));
                    }
                    firstMapChunk.ChunkMapContainer.set(j, singleChunkRow);
                }
        
        for (int i=1; i<=8; i++) {
            MapChunk theMapChunk =general.algomaps.MapProcessor.currentMapBuffer.generalMap.get(0).fragmentContainer.get(i);
            System.out.println("TestFillMap: now working with Chunk #"+i+" : "+(theMapChunk==null ? "null!":"not null"));
                for (int j=0; j<(GeneralParam.ChunkHeight); j++) {
                    ArrayList<MapTile> singleChunkRow = new ArrayList<>();
                    singleChunkRow.add(new MapTile(2));
                    for (int k=1; k<GeneralParam.ChunkWidth; k++) {
                        singleChunkRow.add(new MapTile(2));
                    }
                    theMapChunk.ChunkMapContainer.set(j, singleChunkRow);
                }
        }
    }
}
