/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Menu;

import java.util.ArrayList;

/**
 *
 * @author 2021288
 */
public interface MenuInterface {
    void addMenuItem(int selector, String description);
    int showMenu();
    ArrayList<MenuItem> getMenuItems();
    String getMenuTitle();
    String getMenuText();
    int getMenuItemsSize();
    MenuItem getSelectedOption();
    MenuItem getMenuItemByIndex(int index);
    void setMenuItems(ArrayList<MenuItem> menuItems);
    void setMenuTitle(String menuTitle);
    void setMenuText(String menuText);
    void setSelectedOption(MenuItem selectedOption);
    void resetSelectedOption();
}
