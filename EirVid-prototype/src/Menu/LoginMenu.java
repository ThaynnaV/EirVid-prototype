/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menu;

import DatabaseManagment.Database;
import Users.UserManagment;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author 2021288
 */
public class LoginMenu implements LoginMenuInterface {
    private Database db;
    private Menu menu;
    
    public LoginMenu(Database db)throws SQLException{
        this.db = db;
        this.geLoginMenuFromDatabase();
    }
    
    private void geLoginMenuFromDatabase() throws SQLException{
        String useDatabaseByName = "USE " + this.db.getDbName();
        this.db.executeStmt(useDatabaseByName);
        String query = "SELECT * from menuItems menu_items \n" +
                        "INNER JOIN menu as menu ON menu_items.menuId = menu.menuId \n" +
                        "INNER JOIN menuoption as menu_option ON menu_items.menuOptionId = menu_option.menuOptionId \n" +
                        "WHERE menu_items.menuId = 1";
        String menuTitle = null;
        String menuText = null;
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        int menuItemCount = 0;
        try (ResultSet rs = this.db.executeStmtQuery(query)) {
            while(rs.next()){
                menuItemCount++;
                // Menu -- same for all menu options
                menuTitle = rs.getString("title");
                menuText = rs.getString("text");
                // menu options -- distinct
                String menuOption = rs.getString("menuOption");
                // First create all menu items for menu and add them to List
                MenuItem newMenuItem = new MenuItem(menuItemCount, menuOption);
                menuItems.add(newMenuItem);
            }
            rs.close();
            // Create a menu with all menu items
            this.menu = new Menu(menuTitle, menuText,menuItems);
        }
    }
    
    @Override
    public Menu getMenu(){
        return this.menu;
    }
    
    /**
     * 
     * @param userManagment
     * @return true if logged in false if exit application 
     */
    @Override
    public boolean showMenu(UserManagment userManagment){
        boolean isDisplayMenu = true;
        boolean isLoggedIn = false;
        do{
            int selectedOption = this.menu.showMenu();
            switch(selectedOption){
                // Login
                case 1: if (userManagment.login()){
                            isLoggedIn = true;
                        }; 
                        break;
                // Register    
                case 2: userManagment.register(); break;
                // Exit application    
                case 3: 
                default:
                    isDisplayMenu = false; 
                    isLoggedIn = false; 
                    break;
            }
        }while(isDisplayMenu && !isLoggedIn);
        
        return isLoggedIn;        
    }
}