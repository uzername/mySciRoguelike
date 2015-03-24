package general.algomaps;

import net.slashie.libjcsi.ConsoleSystemInterface;

/**
 * class for displaying/rendering map on page
 * @author ivan
 */
public class MapDisplay {
    public static ConsoleSystemInterface globalCsi;
    /**
     * render mapdata (one or several mapchunks)
     */
    public void renderMap() {
        //we have index of current mapchunk
        //draw this mapchunk, centering it on screen.
        
        for (int i=0; i<general.algodata.GeneralParam.screenHeight; i++) {
            for (int j=0; j<general.algodata.GeneralParam.screenWidth; j++) {
                
            }
        }
        
    }
}
