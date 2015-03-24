/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general.servitor;

import general.algodata.GameActions;
import general.algodata.GameStates;
import java.util.HashMap;
import java.util.Map;
import org.javatuples.Pair;

/**
 * used to load config from files and contains some basic static declarations
 * @author ivan
 */
public class ConfigProcessor {
    /*here goes pseudostatic structure mapping: (key, gamestate) -> gameaction*/
    //private Map<Map<String, GameStates>, GameActions> AllKeyConfig;
    private Map<org.javatuples.Pair<Integer, GameStates>, GameActions> AllKeyConfig;
    private static ConfigProcessor myConfigProcessor;
    private ConfigProcessor() {
        //AllKeyConfig = new HashMap<Map<String, GameStates>, GameActions>();
        AllKeyConfig = new HashMap<>();
    }
    public ConfigProcessor getConfigProcessor() {
        if (myConfigProcessor==null) {myConfigProcessor = new ConfigProcessor();}
        return myConfigProcessor;
    }
    public void defineStaticKeys() {
        if (AllKeyConfig == null) {
            //AllKeyConfig = new HashMap<Map<String, GameStates>, GameActions>(); }
            AllKeyConfig = new HashMap<>();
        }
        AllKeyConfig.put(
                new Pair<Integer, GameStates>(new Integer(net.slashie.libjcsi.CharKey.DARROW), GameStates.MAINMENU), 
                GameActions.MAINMENU_DOWN);
        AllKeyConfig.put(
                new Pair<Integer, GameStates>(new Integer(net.slashie.libjcsi.CharKey.DARROW), GameStates.MAINMENU),
                GameActions.MAINMENU_UP);
        AllKeyConfig.put(
                new Pair<Integer, GameStates>(new Integer(net.slashie.libjcsi.CharKey.Q), GameStates.MAINMENU),
                GameActions.MAINMENU_QUIT);
    }
}
