/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menu;

import Utilities.Utilities;
import java.util.ArrayList;

/**
 *
 * @author 2021288
 */
public class Menu implements MenuInterface {
    ArrayList<MenuItem> menuItems = new ArrayList<>();
    int menuCounter = 0;
    String menuTitle;
    String menuText;
    private Utilities utility = new Utilities();
    
    public Menu(){}
    
    public Menu(String title){
        this.menuTitle = title;
    }
    
    public Menu(String menuTitle, String menuText, ArrayList<MenuItem> menuItems){
        this.menuTitle = menuTitle;
        this.menuText = menuText;
        this.menuItems = menuItems;
    }
    
    public void addMenuItem(int selector, String description){
        menuCounter++;
        MenuItem newItem = new MenuItem(selector,description);
        menuItems.add(menuCounter, new MenuItem(selector, description));
    }
    
    public void addMenuItems(String[] optionsArray){
        for( int i=0; i< optionsArray.length; i++){
            this.addMenuItem(i, optionsArray[i]);
        }
    }
    
    @Override
    public int showMenu(){
        System.out.println(this.menuTitle);
        // Reference stack overflow: https://stackoverflow.com/questions/34526819/print-arraylist-in-java
        for(MenuItem menu_item : this.menuItems) {
            System.out.println(menu_item.getSelector() + ". " + menu_item.getDescription());
        }
        return this.utility.getUserInteger(this.menuText);
    }
      
}


