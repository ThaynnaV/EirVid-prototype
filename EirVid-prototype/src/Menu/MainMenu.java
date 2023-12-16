/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menu;

import DatabaseManagment.Database;
import Movies.Movies;
import Movies.RentedMovies;
import Rent.Rent;
import Rent.Rented;
import Users.UserManagment;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author 2021288
 */
public class MainMenu implements MainMenuInterface {

    private Database db;
    private Menu menu;

    public MainMenu() {
    }

    public MainMenu(Database db) throws SQLException {
        this.db = db;
        this.getMainMenuItemsFromDatabase();
    }

    private void getMainMenuItemsFromDatabase() throws SQLException {
        String useDatabaseByName = "USE " + this.db.getDbName();
        this.db.executeStmt(useDatabaseByName);
        String query = "SELECT * from menuItems menu_items \n"
                + "INNER JOIN menu as menu ON menu_items.menuId = menu.menuId \n"
                + "INNER JOIN menuoption as menu_option ON menu_items.menuOptionId = menu_option.menuOptionId \n"
                + "WHERE menu_items.menuId = 2";
        String menuTitle = null;
        String menuText = null;
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        int menuItemCount = 0;
        try (ResultSet rs = this.db.executeStmtQuery(query)) {
            while (rs.next()) {
                menuItemCount++;
                // Menu -- same for all menu options
                menuTitle = rs.getString("title");
                menuText = rs.getString("text");
                // menu options 
                String menuOption = rs.getString("menuOption");
                // First create all menu items for menu and add them to List
                MenuItem newMenuItem = new MenuItem(menuItemCount, menuOption);
                menuItems.add(newMenuItem);
            }
            // Create a menu with all menu items
            this.menu = new Menu(menuTitle, menuText, menuItems);
        }
    }

    @Override
    public Menu getMenu() {
        return this.menu;
    }

    /**
     * Main menu has following options 1.Rent Movie 2.See rented movies 3.View Recommended Movies 4.Logout 5.Exit
     *
     * @param userManagment
     * @param rentMenu
     * @param movieMenu
     * @param rentedMovies
     * @param movies
     * @return false to exit application
     */
    @Override
    public boolean showMenu(UserManagment userManagment, RentMenu rentMenu, MovieMenu movieMenu, RentedMovies rentedMovies, Movies movies ) {
        boolean isDisplayMenu = true;
        boolean isReturn = false;
        Rent rent = new Rent();
        //boolean isLoggedIn = true;
        do {
            int selectedOption = this.menu.showMenu();
            switch (selectedOption) {
                case 1:
                    // Rent Movie
                    Rented rented = new Rented();
                    // if movie and rent option are selected 
                    if (this.rentMovie(rentMenu, movieMenu, rented)) {
                        String currentUserEmail = userManagment.getCurrentUser().getEmail(); 
                        rented.setEmail(currentUserEmail);
                        rentedMovies.save(rented);
                    }
                    break;
                case 2:
                    this.seeMyRentedMovies(userManagment, rentedMovies);
                    break;
                case 3:
                    // View recommended Movies
                    // rent.viewRecomendedMovies();
                    
                    break;
                case 4:
                    // Logout --? return to login screen
                    userManagment.logout();
                    isReturn = true;
                    isDisplayMenu = false;
                    break;
                case 5:
                default:
                    // Exit application
                    isDisplayMenu = false;
                    break;
            }
        } while (isDisplayMenu);

        return isReturn;
    }

    private boolean rentMovie(RentMenu rentMenu, MovieMenu movieMenu, Rented rented) {
        int selectedMovie = 0;
        int rentMenuOption = 0;
        do {
            System.out.println("RENT MOVIES");
            selectedMovie = movieMenu.showMenu();
            // if selected movie id is last in menu, then we selected back button
            if (selectedMovie == movieMenu.getMenu().getMenuItemsSize()) {
                // reset selceted movie to 0
                selectedMovie = 0;
                continue;
            }
            // if is sleceted any other movie select rent time
            rentMenuOption = rentMenu.showMenu();
            // if selected movie id is last in menu, then we selected back button
            if (rentMenuOption == rentMenu.getMenu().getMenuItemsSize()) {
                // reset rentMenuOption to 0
                rentMenuOption = 0;
            }
        } while (selectedMovie > 0 && rentMenuOption == 0);

        if (selectedMovie > 0 && rentMenuOption > 0) {
            rented.setMovieId(selectedMovie);
            rented.setRentOptionId(rentMenuOption);
            rented.setDateToNow();
            return true;
        }
        return false;
    }
    
    private void seeMyRentedMovies(UserManagment userManagment, RentedMovies rentedMovies){
        // curretn user email
        String currentUserEmail = userManagment.getCurrentUser().getEmail();
        //Get my rented movies 
        rentedMovies.setRentedMoviesByEmail(currentUserEmail);
        // display rented movies -- if there is any rented movie
        if(rentedMovies.isRentedMovies()){
            System.out.println("All Rented movies");
            rentedMovies.displayAllRentedMovies();
        }
    }
}

