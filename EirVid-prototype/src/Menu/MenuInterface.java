/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Menu;

/**
 *
 * @author 2021288
 */
public interface MenuInterface {
    void addMenuItem(int selector, String description);
    void addMenuItems(String[] optionsArray);
    int showMenu();
}
