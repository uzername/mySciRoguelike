package general.algomaps;

import general.Player;
import general.algodata.GeneralParam;
import net.slashie.libjcsi.ConsoleSystemInterface;

/**
 * class for displaying/rendering map on page
 * @author ivan
 */
public class MapDisplay {
    public static ConsoleSystemInterface globalCsi;
    private static Integer playerPositionX =new Integer(GeneralParam.screenWidth/2) + 1;
    private static Integer playerPositionY =new Integer(GeneralParam.screenHeight/2) + 1;
    /**
     * render mapdata (one or several mapchunks). Used to draw terrain 
     * Making it static too, it is used in many places in project
     */
    public static void renderMap() {
        //we have index of current mapchunk
        //draw this mapchunk, centering it on screen.
        general.algomaps.MapDisplay.globalCsi.cls();
        Integer playerFragmentMapIndex=general.Player.getCurrMapFragment();
        Integer playerChunkMapIndex=general.Player.getCurrMapChunk();
        Integer playerChunkMapXCoord = general.Player.getMapChunkXCoord();
        Integer playerChunkMapYCoord = general.Player.getMapChunkYCoord();
        //defining the coordinates to draw. Screen may contain map tiles from  
        //different Fragments and Chunks
        Integer startFragment = playerFragmentMapIndex;
        Integer endFragment = playerFragmentMapIndex;
        Integer startChunk = playerChunkMapIndex;
        Integer endChunk = playerChunkMapIndex;
        //coordinates of start and finish area to display. 
        //Must be used with context of corresponding chunk and even a fragment
        Integer startX=playerChunkMapXCoord-playerPositionX; Integer startY=playerChunkMapYCoord-playerPositionY;
        Integer endX=playerChunkMapXCoord+playerPositionX; Integer endY=playerChunkMapYCoord+playerPositionY;
        //modifying chunk markers
                //left start chunk
        if ((startX<0)) { 
                if (startY>general.algodata.GeneralParam.ChunkHeight) { //lower left chunk
                    
                }
                if (startY<0) { //upper left chunk 
                    
                }
                if ((startY>=0)&&(startY<=general.algodata.GeneralParam.ChunkHeight)) { //just leftmost chunk
                    
                }
        }
                //right start chunk - this variant is geometrically impossible. Just refer to graph model
        if (startX>general.algodata.GeneralParam.ChunkWidth) {
            throw new ArithmeticException("Display out of bounds. startX>ChunkWidth");
        }
                //central (straight) start chunk
        if ((startX<general.algodata.GeneralParam.ChunkWidth)&&(startX>0)) {
            if (startY>general.algodata.GeneralParam.ChunkHeight) { // lower (straight) start chunk
                
            }
            if (startY<0) { //higher (straight) start chunk
            
            }
            if ((startY>=0)&&(startY<=general.algodata.GeneralParam.ChunkHeight)) { //the chunk remains the same. nothing to change
                
            }
        }
        //checking end chunk markers
                //left end chunk - this variant is geometrically impossible
        if ((endX<0)) { 
                 throw new ArithmeticException("Display out of bounds. endX<0");
        }
                //right end chunk
        if (endX>general.algodata.GeneralParam.ChunkWidth) {
            
        }
                //central (straight) start chunk
        if ((endX<=general.algodata.GeneralParam.ChunkWidth)&&(endX>=0)) {
            if (endY>general.algodata.GeneralParam.ChunkHeight) { // lower (straight) start chunk
                
            }
            if (endY<0) { //higher (straight) start chunk
            
            }
            if ((endY>=0)&&(endY<=general.algodata.GeneralParam.ChunkHeight)) { //the chunk remains the same. nothing to change
                
            }
        }
        
        
        //checking boundaries and adjusting display positions
        if (startX<0) {
            startX=general.algodata.GeneralParam.ChunkWidth+startX;        
        }
        if (endX>general.algodata.GeneralParam.ChunkWidth) {
            endX=general.algodata.GeneralParam.ChunkWidth-endX;
        }
        if (startY<0) {
            startY=general.algodata.GeneralParam.ChunkHeight+startY;
        }
        if (endY>general.algodata.GeneralParam.ChunkHeight) {
            endY=general.algodata.GeneralParam.ChunkHeight-endY;
        }
        //draw the stuff, each symbol of display screen
        System.out.println("Printing map");
        System.out.println("(startX="+startX+"; startY="+startY+"); endX="+endX+"; endY="+endY);
        for (int i=0; i<general.algodata.GeneralParam.screenHeight; i++) {
            for (int j=0; j<general.algodata.GeneralParam.screenWidth; j++) {
                
            }
        }
        general.algomaps.MapDisplay.globalCsi.refresh();
    }
    /**
     * render only characters on map (including monsters, NPCs and Player)
     */
    public static void renderCharacters() {
        //render player
        general.algomaps.MapDisplay.globalCsi.print(playerPositionX, playerPositionY, Player.playerMainCharacter, Player.playerMainColor);
            general.algomaps.MapDisplay.globalCsi.refresh();
    }
}
