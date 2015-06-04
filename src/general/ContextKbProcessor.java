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
public class ContextKbProcessor{
    /**
     * entry point for keyboard input processing.
     * Corresponding to user's input, current GameState, previous GameState (maybe) 
     * we choose GameAction perform the action and then set the GameState, using GameStateResolver
     */
    //=============================
    /*a semantic layer subroutines (wrappers) to be executed inside performAndChooseAction*/
    private static void kbNextMenuItemWrapper() {
        general.menugame.MainMenu.highlightNextItem();
    }
    private static void kbPrevMenuItemWrapper() {
        general.menugame.MainMenu.highlightPreviousItem();
    }
    private static void kbProcessCurrentMenuItemWrapper() {
        general.menugame.MainMenu.processCurrentMenuItem();
    }
    //=============================
    public static void performAndChooseAction(int processKeyCode) {
        //System.out.println("inside performAndChooseAction: "+processKeyCode);
        switch (processKeyCode) {
            case (CharKey.Q): {
                if (general.algodata.GameStateResolver.getCurrGameState()==GameStates.MAINMENU) {
                    //quit game by pressing Q key, while in main menu
                    general.algodata.GameStateResolver.changeGameState(GameStates.QUITGAME);
                }
                break;
            }
            case (CharKey.q): {
                if (general.algodata.GameStateResolver.getCurrGameState()==GameStates.MAINMENU) {
                    general.algodata.GameStateResolver.changeGameState(GameStates.QUITGAME);
                }
                break;
            }
            
            case (CharKey.UARROW): {
                if (general.algodata.GameStateResolver.getCurrGameState()==GameStates.MAINMENU) {
                    //move to previous menu item and highlight it
                    kbPrevMenuItemWrapper();
                    //do not change state, we're still in main menu
                }
                break;
            }
            case (CharKey.DARROW): {
                if (general.algodata.GameStateResolver.getCurrGameState()==GameStates.MAINMENU) {
                    //move to next menu item and highlight it
                    kbNextMenuItemWrapper();
                    //do not change state, we're still in main menu
                }
                break;
            }
            case (CharKey.ENTER): {
                if (general.algodata.GameStateResolver.getCurrGameState()==GameStates.MAINMENU){
                    kbProcessCurrentMenuItemWrapper();
                }
            }
            
        }
        //performAction(theChosenAction)
    }
    
}

