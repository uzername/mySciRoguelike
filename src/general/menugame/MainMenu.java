/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general.menugame;

/**
 * this class is used to display game menu and handle user's input
 * it's good to make MainMenu static because it's single for all game
 */
public class MainMenu {
    /** 
     * Shows what current menu item is being selected. 
     */
    private static Integer menuIndex=0;
    private static String[] menuItems = {"New Game","Load Game"};
    public static void displayMenu() {
        general.algomaps.MapDisplay.globalCsi.cls();
        Integer currentRow = general.algodata.GeneralParam.screenHeight-menuItems.length-2;
        for (String menuItem : menuItems) {
            general.algomaps.MapDisplay.globalCsi.print(3, currentRow, menuItem, 0x1122CC);
            currentRow++;
        }
        general.algomaps.MapDisplay.globalCsi.saveBuffer();
        //highlight initial menu item
        highlightMenu();
    }
    /**
     * print out current menu item with different color. uses menuIndex
     */
    public static void highlightMenu() {
        general.algomaps.MapDisplay.globalCsi.restore();
        Integer theLineIndex=general.algodata.GeneralParam.screenHeight-menuItems.length-2+menuIndex;
        general.algomaps.MapDisplay.globalCsi.print(3, theLineIndex, menuItems[menuIndex], 0x11BBCC);
        general.algomaps.MapDisplay.globalCsi.refresh();
    }
    /**
     * @return the menuIndex
     */
    public static Integer getMenuIndex() {
        return menuIndex;
    }

    /**
     * @param menuIndex the menuIndex to set
     */
    public static void setMenuIndex(Integer menuIndexInp) {
        menuIndex = menuIndexInp;
    }
}
