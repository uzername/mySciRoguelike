/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;
import general.algodata.*;
import general.algomaps.*;
import net.slashie.libjcsi.CharKey;
/**
 * Static class which contains processing routines for user's input. 
 * necessary mapings of key codes and corresponding actions are defined in ConfigProcessor
 * feels like it's the largest class in whole program
 * @author ivan
 */
public class ContextKbProcessor {
    /**
     * entry point for keyboard input processing.
     * Corresponding to user's input, current GameState, previous GameState (maybe) 
     * we choose GameAction perform the action and then set the GameState, using GameStateResolver
     */
    public static void performAndChooseAction(int processKeyCode) {
        switch (processKeyCode) {
            case (CharKey.Q): {
                
                break;
            }
        }
    }
    
}
