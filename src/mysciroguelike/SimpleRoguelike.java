package mysciroguelike;
 
//import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.jcurses.JCursesConsoleInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
 
        
public class SimpleRoguelike {
    //private ConsoleSystemInterface csi = new JCursesConsoleInterface();
    //private ConsoleSystemInterface csi = new WSwingConsoleInterface(/*"Simple Roguelike - libjcsi Testing Grounds"*/);
    //private ConsoleSystemInterface csi = new WSwingConsoleInterface(new java.awt.Dimension(640, 480));
    private int a, b;
    
    public static void main(String[] p) {
        
        new SimpleRoguelike().run(); 
		//multithreaded app. Basically, program finishes when all threads are done.
    }
    public void paramInit() {
        general.algomaps.MapDisplay.globalCsi = new WSwingConsoleInterface(new java.awt.Dimension(640, 480));
        ((WSwingConsoleInterface)(general.algomaps.MapDisplay.globalCsi)).setConsoleCaption("The Scientific Roguelike");
        general.algodata.GeneralParam.defineChunkSize(40, 40);
        //general.algodata.PrototypeCollector.loadMapTilesData();
    }
    
    public void run () {
        paramInit();
        int key;
        boolean exit = false;
        //display main menu here and handle it's control
        general.menugame.MainMenu.displayMenu();
        exit = false;
        while (!exit) {
            key = general.algomaps.MapDisplay.globalCsi.inkey().code;
            switch (key){
                case CharKey.Q: case CharKey.q:
                    exit = true;
            }
        }
        //======================
    	general.algomaps.MapDisplay.globalCsi.cls();
        general.algomaps.MapDisplay.globalCsi.print(5, 5, "Welcome to The game!", 0xAABBDD);
        
        for (int i=0; i<general.algomaps.MapDisplay.globalCsi.screenGetWidth(); i++) {
            if (i%10 == 0) {
                general.algomaps.MapDisplay.globalCsi.print(i, 1, "+", 0xAABBDD); 
            } else {general.algomaps.MapDisplay.globalCsi.print(i, 1, "0", 0xAABBDD);}
        }
        general.algomaps.MapDisplay.globalCsi.saveBuffer();
        exit = false;
        while (!exit){
        	general.algomaps.MapDisplay.globalCsi.restore();
            general.algomaps.MapDisplay.globalCsi.print(a, b, "@", 0x11BBCC);
            general.algomaps.MapDisplay.globalCsi.refresh();
             key = general.algomaps.MapDisplay.globalCsi.inkey().code;
            switch (key){
            case CharKey.UARROW:
            	b--;
            	break;
            case CharKey.DARROW:
            	b++;
            	break;
            case CharKey.LARROW:
            	a--;
            	break;
            case CharKey.RARROW:
            	a++;
            	break;
            case CharKey.Q: case CharKey.q:
            	exit = true;
            }
        }
        general.algomaps.MapDisplay.globalCsi.print(1, 20, "Press space to continue");
        general.algomaps.MapDisplay.globalCsi.refresh();
        general.algomaps.MapDisplay.globalCsi.waitKey(CharKey.SPACE);
        System.exit(0);
    }
}