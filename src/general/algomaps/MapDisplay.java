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
     * get the detailed description of startChunk and endChunk, both as startFragment/endFragment.
     * used in renderMap
     */
    private static void detailDrawPositions() {
        
    }
    /**
     * render mapdata (one or several mapchunks). Used to draw terrain 
     * Making it static too, it is used in many places in project
     * The epic method. Probably should split it to several
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
        java.util.ArrayList<Integer> startMarker = null; java.util.ArrayList<Integer> endMarker = null;
        Integer mvmntDirection=0;
        //modifying chunk markers
                //left start chunk
        if ((startX<0)) { 
                if (startY>general.algodata.GeneralParam.ChunkHeight) { //lower left chunk
                    //this chink is located to southwest
                    mvmntDirection = 8;
                }
                if (startY<0) { //upper left chunk 
                    //this chunk is located norhwest
                    mvmntDirection = 2;
                }
                if ((startY>=0)&&(startY<=general.algodata.GeneralParam.ChunkHeight)) { //just leftmost chunk
                    //this chunk is located to west
                    mvmntDirection = 1;
                }
                startMarker = MapProcessor.getNeighbourMapArea(startChunk,startFragment,mvmntDirection);
        }
                //right start chunk - this variant is geometrically impossible. Just refer to graph model
        if (startX>general.algodata.GeneralParam.ChunkWidth) {
            throw new ArithmeticException("Display out of bounds. startX>ChunkWidth");
        }
                //central (straight) start chunk
        if ((startX<general.algodata.GeneralParam.ChunkWidth)&&(startX>0)) {
            // lower (straight) start chunk
            if (startY>general.algodata.GeneralParam.ChunkHeight) {
                //this chunk is located to north
                mvmntDirection=3;
            }
            //higher (straight) start chunk
            if (startY<0) {
                //this chunk is located to south
                mvmntDirection=7;
            }
            //nothing to change. Standing in place
            if ((startY>=0)&&(startY<=general.algodata.GeneralParam.ChunkHeight)) { //the chunk remains the same. nothing to change
                mvmntDirection=0;
            }
            startMarker = MapProcessor.getNeighbourMapArea(startChunk, startFragment, mvmntDirection);
        }
            System.out.println("start MovementDirection = "+mvmntDirection+"; startMarker="+startMarker);
        //checking end chunk markers
                //left end chunk - this variant is geometrically impossible
        if ((endX<0)) { 
                 throw new ArithmeticException("Display out of bounds. endX<0");
        }
                //right end chunk
        if (endX>general.algodata.GeneralParam.ChunkWidth) {
            if (endY>general.algodata.GeneralParam.ChunkHeight) { // lower right end chunk
                mvmntDirection=6;
            }
            if (endY<0) { //higher (right) end chunk
                mvmntDirection=4;
            }
            if ((endY>=0)&&(endY<=general.algodata.GeneralParam.ChunkHeight)) { //straight right end chunk
                mvmntDirection=5;
            }
            endMarker = MapProcessor.getNeighbourMapArea(endChunk, endFragment, mvmntDirection);
        }
                //central (straight) start chunk
        if ((endX<=general.algodata.GeneralParam.ChunkWidth)&&(endX>=0)) {
            if (endY>general.algodata.GeneralParam.ChunkHeight) { // lower (straight) end chunk
                mvmntDirection=7;
            }
            if (endY<0) { //higher (straight) start chunk
                mvmntDirection=3;
            }
            if ((endY>=0)&&(endY<=general.algodata.GeneralParam.ChunkHeight)) { //the chunk remains the same. nothing to change
                mvmntDirection=0;
            }
            endMarker = MapProcessor.getNeighbourMapArea(endChunk, endFragment, mvmntDirection);
        }
        System.out.println("end MovementDirection="+mvmntDirection+"; endMarker="+endMarker);
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
        /*
        ++++++ Map display algorithm ++++++
        we have relative coordinates (startX, startY) and (endX, endY). These are relative to starting/ending 
        MapChunk and MapFragment. Starting mapChunk/mapFragment are in startFragment. This info is obtained from getNeighbourMapArea
        Ending mapChunk/mapFragment are in endFragment. We also have Player's current Fragment and Chunk.
        While drawing map, we start from (startX, startY, [mapChunk, mapFragment]). We move from left to right, crossing some borders of mapFragments/mapChunks.
        We take data from current-cycle MapChunk and draw these on screen
        */
        Integer currentCycleX=startX; Integer currentCycleY=startY; 
        Integer currentCycleChunkCoord=startMarker.get(0); Integer currentCycleFragmentCoord=startMarker.get(1);
        for (int i=0; i<general.algodata.GeneralParam.screenHeight; i++) { //filling by rows (SCREEN COORDS!)
            //remember where (chunk/fragment) we started horizontal steps
            Integer initLinearChunk = currentCycleChunkCoord;
            Integer initLinearFragment  = currentCycleFragmentCoord;
            for (int j=0; j<general.algodata.GeneralParam.screenWidth; j++) { //iterating over each symbl of row (SCREEN COORDS!)
                /*
                general.algomaps.MapDisplay.globalCsi.print(i,j,
                      general.algodata.PrototypeCollector.mapTilesData.get(general.algomaps.MapProcessor.currentMapBuffer.generalMap.get(currentCycleFragmentCoord).fragmentContainer.get(currentCycleChunkCoord).ChunkMapContainer.get(0).prototypeIndex).mapSymbol.toString()
                ); 
                */
                
                general.algomaps.MapDisplay.globalCsi.print(j, i, 
                general.algodata.PrototypeCollector.mapTilesData.get(
                    general.algomaps.MapProcessor.getMapTileByCoordinates(currentCycleFragmentCoord, currentCycleChunkCoord, currentCycleY, currentCycleX).prototypeIndex).mapSymbol.toString() );
                
                /*
                System.out.println("printing tile at coordinates: ["+"f="+currentCycleFragmentCoord+",c="+currentCycleChunkCoord+",("+currentCycleX+","+currentCycleY+") scr=("+i+","+j+")");
                System.out.println(general.algomaps.MapProcessor.getMapTileByCoordinates(currentCycleFragmentCoord, currentCycleChunkCoord, currentCycleY, currentCycleX));
                */
                currentCycleX+=1;
                //equality into condition was added because it used to cause an exception. Originally it was '>'...
                if (currentCycleX>=general.algodata.GeneralParam.ChunkWidth) { 
                    //we have changed the MapChunk while moving to East
                        System.out.println("current coordinates: ["+"f="+currentCycleFragmentCoord+",c="+currentCycleChunkCoord+",("+currentCycleX+","+currentCycleY+") scr=("+i+","+j+")");
                    java.util.ArrayList<Integer> newChunkFragmentData = MapProcessor.getNeighbourMapArea(currentCycleChunkCoord, currentCycleFragmentCoord, 5);
                        System.out.println("Changed mapchunk while drawing (horizontal movement) to "+newChunkFragmentData);
                    currentCycleX = 0;
                    currentCycleChunkCoord = newChunkFragmentData.get(0);
                    currentCycleFragmentCoord = newChunkFragmentData.get(1);
                        System.out.println("updated coordinates: ["+"f="+currentCycleFragmentCoord+",c="+currentCycleChunkCoord+",("+currentCycleX+","+currentCycleY+")"+"scr=("+i+","+j+")");
                }
            }
            currentCycleChunkCoord = initLinearChunk; currentCycleFragmentCoord = initLinearFragment;
            currentCycleY+=1;
            System.out.println("moving to next line #"+currentCycleY);
            //equality into condition was added because it used to cause an exception. Originally it was '>'...
            if (currentCycleY>=general.algodata.GeneralParam.ChunkHeight) {
                java.util.ArrayList<Integer> newChunkFragmentData = MapProcessor.getNeighbourMapArea(currentCycleChunkCoord, currentCycleFragmentCoord, 5);
                System.out.println("Changed mapchunk while drawing (vertical movement) to "+newChunkFragmentData);
                currentCycleY = 0;
                currentCycleChunkCoord = newChunkFragmentData.get(0);
                currentCycleFragmentCoord = newChunkFragmentData.get(1);
            }
        }
        System.out.println("done drawing");
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
