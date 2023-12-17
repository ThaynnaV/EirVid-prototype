/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menu;

/**
 *
 * @author 2021288
 */
public class MenuItem implements MenuItemInterface{
    private int selector;
    private String description;
    
    public MenuItem(){}
    
    public MenuItem(int selector, String description){
        this.selector = selector;
        this.description = description;
    }
    
    @Override
    public void createMenuItem (int selector, String description){
        this.selector = selector;
        this.description = description;        
    }
    
    @Override
    public int getSelector(){
        return this.selector;
    }
    
    @Override
    public String getDescription(){
        return this.description;
    }
}

