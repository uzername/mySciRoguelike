/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general.menugame;

import general.AlgorithmicProcessor;

/**
 * this class is used to display game menu and handle user's input
 * it's good to make MainMenu static because it's single for all game
 */
public class MainMenu {
    /** 
     * Shows what current menu item is being selected. 
     */
    private static Integer menuIndex=0;
    private static String[] menuItems = {"New Game","Load Game","Quit Game"};
    private static int highlightedColor = 0x11BBCC; //color of highlighted menu item
    private static int regularColor = 0x1122CC;
    public static void displayMenu() {
        general.algomaps.MapDisplay.globalCsi.cls();
        Integer currentRow = general.algodata.GeneralParam.screenHeight-menuItems.length-2;
        for (String menuItem : menuItems) {
            general.algomaps.MapDisplay.globalCsi.print(3, currentRow, menuItem, regularColor);
            currentRow++;
        }
        general.algomaps.MapDisplay.globalCsi.saveBuffer();
        //highlight initial menu item
        highlightMenu(highlightedColor);
    }
    /**
     * print out current menu item with different color. uses menuIndex
     * @param hexintColor - highlight menu item with 
     */
    public static void highlightMenu(int hexintColor) {
        general.algomaps.MapDisplay.globalCsi.restore();
        Integer theLineIndex=general.algodata.GeneralParam.screenHeight-menuItems.length-2+menuIndex;
        general.algomaps.MapDisplay.globalCsi.print(3, theLineIndex, menuItems[menuIndex], hexintColor);
        general.algomaps.MapDisplay.globalCsi.refresh();
    }
    /**
     * @return the menuIndex
     */
    public static Integer getMenuIndex() {
        return menuIndex;
    }
    /**
     * @param menuIndexInp the menuIndex to set
     */
    public static void setMenuIndex(Integer menuIndexInp) {
        menuIndex = menuIndexInp;
    }
    //this code may be merged into single subroutine
    public static void highlightNextItem() {
        highlightMenu(regularColor);
        menuIndex++;
        if (menuIndex>=menuItems.length) {
            menuIndex=0;
        }
        highlightMenu(highlightedColor);
    }
    public static void highlightPreviousItem() {
        highlightMenu(regularColor);
        menuIndex--;
        if (menuIndex<0) {
            menuIndex=menuItems.length-1;
        }
        highlightMenu(highlightedColor);
    }
    /**
     * Call processing routines for selected Menu item
     */
    public static void processCurrentMenuItem() {
        if (menuIndex==menuItems.length-1) { //quit game menu item
            general.algodata.GameStateResolver.changeGameState(general.algodata.GameStates.QUITGAME);
        }
        if (menuIndex==0) { //'new game' menu item
            general.AlgorithmicProcessor.newGameDataGeneration();
            general.algodata.GameStateResolver.changeGameState(general.algodata.GameStates.MAINGAME);
        }
    }
}
