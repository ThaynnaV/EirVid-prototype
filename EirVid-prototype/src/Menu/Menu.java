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
    private ArrayList<MenuItem> menuItems = new ArrayList<>();
    private String menuTitle;
    private String menuText;
    private Utilities utility = new Utilities();
    private MenuItem selectedOption;
    
    public Menu(){}
    
    public Menu(String title){
        this.menuTitle = title;
    }
    
    public Menu(String title, String menuText){
        this.menuTitle = title;
        this.menuText = menuText;
    }
    
    public Menu(String menuTitle, String menuText, ArrayList<MenuItem> menuItems){
        this.menuTitle = menuTitle;
        this.menuText = menuText;
        this.menuItems = menuItems;
    }
    
    @Override
    public void addMenuItem(int selector, String description){
        MenuItem newItem = new MenuItem(selector,description);
        menuItems.add(new MenuItem(selector, description));
    }
    
    // GETTERS 
    @Override
    public ArrayList<MenuItem> getMenuItems(){
        return this.menuItems;
    }
    @Override
    public String getMenuTitle(){
        return this.menuTitle;
    }
    @Override
    public String getMenuText(){
        return this.menuText;
    }
    @Override
    public int getMenuItemsSize(){
        return this.menuItems.size();
    }
    @Override
    public MenuItem getSelectedOption(){
        return this.selectedOption;
    }
    @Override
    public MenuItem getMenuItemByIndex(int index){
        return this.menuItems.get(index);
    }
        
    // SETTERS
    @Override
    public void setMenuItems(ArrayList<MenuItem> menuItems){
        this.menuItems = menuItems;
    }
    @Override
    public void setMenuTitle(String menuTitle){
        this.menuTitle = menuTitle;
    }
    @Override
    public void setMenuText(String menuText){
        this.menuText = menuText;
    }
    @Override
    public void setSelectedOption(MenuItem selectedOption){
        this.selectedOption = selectedOption;
    }
    @Override
    public void resetSelectedOption(){
        this.selectedOption = null;
    }
    // METHODS
    @Override
    public int showMenu(){
        System.out.println("\n");
        System.out.println(this.menuTitle);
        // Reference stack overflow: https://stackoverflow.com/questions/34526819/print-arraylist-in-java
        for(MenuItem menu_item : this.menuItems) {
            System.out.println(menu_item.getSelector() + ". " + menu_item.getDescription());
        }
        return this.utility.getUserOptionInRange(this.menuText,1,this.menuItems.size());
    }
      
}


