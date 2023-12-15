/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menu;


import DatabaseManagment.Database;
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
        
        try (ResultSet rs = this.db.executeStmtQuery(query)) {
            while(rs.next()){
                // Menu -- same for all menu options
                rs.getInt("menuId");
                menuTitle = rs.getString("title");
                menuText = rs.getString("text");
                // menu options -- distinct
                int menuOptionId = rs.getInt("menuOptionId");
                String menuOption = rs.getString("menuOption");
                // First create all menu items for menu and add them to List
                MenuItem newMenuItem = new MenuItem(menuOptionId, menuOption);
                menuItems.add(newMenuItem);
            }
            // Create a menu with all menu items
            this.menu = new Menu(menuTitle, menuText,menuItems);
        }
    }
    
    public Menu getMenu(){
        return this.menu;
    }
    
    public void showMenu(){
        this.menu.showMenu();
    }
}
