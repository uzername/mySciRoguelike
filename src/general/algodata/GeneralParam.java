package general.algodata;

/**
 * Contains general parameters in static class, available in whole project
 * @author ivan
 */
public class GeneralParam {
    //by now storing current map chunk here. later it should be moved to Player class
    public static Integer currentMapChunkIndex=0;
    
    public static Integer ChunkWidth;
    public static Integer ChunkHeight;
    public static void defineChunkSize(Integer inpWidth, Integer inpHeight) {
        ChunkWidth = inpWidth;
        ChunkHeight = inpHeight;
    }
    //ChunkWidth and ChunkHeight are the sizes of internal array for storing mapdata
    //screenWidth, screenHeight are the sizes of our actual display, passed to ConsoleInterface
    public static Integer screenWidth = 80;
    public static Integer screenHeight = 25;
    /**
     * number of chunks in FragmentMap
     */
    public static Integer numberChunks = 9;
    /**
     * number of fragments in AllMaps
     */
    public static Integer numberFragments = 9;
}
