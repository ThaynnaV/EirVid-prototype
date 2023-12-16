/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menu;

import Rent.Rent;
import Rent.RentOptions;
import java.util.ArrayList;

/**
 *
 * @author 2021288
 */
public class RentMenu implements RentMenuInterface{
    private Menu menu = new Menu("Rent Menu", "Select Rent Duration by typing a number next to item");
        
    public RentMenu(){}
    
    public RentMenu(RentOptions rentOptions){
        int selector = 1;
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        // for each rent option
        for(Rent options : rentOptions.getOptions()) {
            this.menu.addMenuItem(selector, options.getFullRentItem());
            selector++;
        }
        // add go Back Selector
        this.menu.addMenuItem(selector, "Back");
    }
    
    public Menu getMenu(){
        return this.menu;
    }
    
    /**
     * 
     * @return selected rent option as integer 
     */
    public int showMenu(){
        return this.menu.showMenu();
    }
}
