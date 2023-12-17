/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseManagment;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class that generates all tables and fill them with initial data.
 * It use DatabaseConnection class to connect to database
 * @author 2021240
 */
public class DatabaseCreator {
    private Statement stmt;
    private Connection conn;
    private String dbName = "";
    
     // CONSTRUCTOR
    public DatabaseCreator(Statement stmt, Connection conn, String dbName){
        this.conn = conn;
        this.stmt = stmt;
        this.dbName = dbName;
    }
    
    /**
     * Set up database.First creates an object DataBaseConnection class and use it to connect to database.Then create tables and populate them with initial values
     * @return 
     */
    public boolean setupDatabase(){
        try{
            // then create database and tables if not created already
            boolean isCreated = this.createDatabase();
            if(!isCreated){
                System.out.println("Can't create database");
                return false;
            }
            
            // then INSERT starting values
            // INSERT values for initial users if values already not exist
            boolean isUserCreated = this.createUsers();
            if(!isUserCreated){
                System.out.println("Error inserting values to user table");
                return false;
            }
            
            // INSERT values for initial movies if values already not exist
            //boolean isMoviesCreated = this.createMovies();
            //if(!isMoviesCreated){
            //    System.out.println("Error inserting values to movie table");
            //    return false;
            //}
            
            // INSERT values for initial rent options if values already not exist
            boolean isRentCreated = this.createRentOptions();
            if(!isRentCreated){
                System.out.println("Error inserting values to rent table");
                return false;
            }
            
            // INSERT values for initial menu options if values already not exist
            boolean isMenuOptionsCreated = this.createMenuOptions();
            if(!isMenuOptionsCreated){
                System.out.println("Error inserting values to menu options table");
                return false;
            }
            
            // INSERT values for initial menus if values already not exist
            boolean isMenusCreated = this.createMenus();
            if(!isMenusCreated){
                System.out.println("Error inserting values to menu table");
                return false;
            }
            
            // INSERT values for initial menu items (menu options on each menu) if values already not exist
            boolean isMenuItemsCreated = this.createMenuItems();
            if(!isMenuItemsCreated){
                System.out.println("Error inserting values to menu items table");
                return false;
            }          
            // if everything is ok return true
            return true;
        } catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e){
            System.out.println("Error setting up database: " +e.getMessage());
            return false;
        }
    }
    
    
    /**
     * Will create tables if they don't exist
     * @return true if database is created
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException 
     * @throws java.sql.SQLException 
     */
    public boolean createDatabase() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
       try{
            // Create database with dbName if not existing
            this.stmt.execute("CREATE SCHEMA IF NOT EXISTS " + this.dbName +";");
            // use created database
            this.stmt.execute("USE "+this.dbName+";");
            
            // Create table for Users
            this.stmt.execute(
                    "CREATE TABLE IF NOT EXISTS user ("
                            + "email VARCHAR(100) NOT NULL PRIMARY KEY,"
                            + "password VARCHAR(30)"
                            + ");"
            );
            
            // Create table for Movies
            /*this.stmt.execute(
                    "CREATE TABLE IF NOT EXISTS movie ("
                            + "movieId INT(10) NOT NULL PRIMARY KEY,"
                            + "title VARCHAR(30)"
                            + ");"
            );*/
            this.stmt.execute(
                "CREATE TABLE IF NOT EXISTS movie ("
                        + "movieId INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                        + "title VARCHAR(255),"
                        + "original_language VARCHAR(10),"
                        + "original_title VARCHAR(255),"
                        + "overview TEXT,"
                        + "popularity DOUBLE,"
                        + "release_date VARCHAR(28),"
                        + "runtime INT,"
                        + "tagline VARCHAR(255),"
                        + "vote_average DOUBLE,"
                        + "vote_count INT,"
                        + "price DOUBLE,"
                        + "UNIQUE KEY unique_title_release_date (title, release_date)"
                        + ");"
            );
            
            // Create table for Rent
            this.stmt.execute(
                    "CREATE TABLE IF NOT EXISTS rent ("
                            + "rentId INT(10) NOT NULL PRIMARY KEY,"
                            + "description VARCHAR(40),"
                            + "length INT(10),"
                            + "price INT(10)"
                            + ");"
            );
            
            // Create table Rented movies
            this.stmt.execute(
                    "CREATE TABLE IF NOT EXISTS rented ("
                            + "rentedId INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                            + "email VARCHAR(100),"
                            + "movieId INT(10),"
                            + "rentId INT(10),"
                            + "date TIMESTAMP,"
                            + "FOREIGN KEY(email) REFERENCES user(email),"
                            + "FOREIGN KEY(movieId) REFERENCES movie(movieId),"
                            + "FOREIGN KEY(rentId) REFERENCES rent(rentId)"
                            + ");"
            );
            
            // Create table for MenuOption
            this.stmt.execute(
                    "CREATE TABLE IF NOT EXISTS menuOption ("
                            + "menuOptionId INT(10) NOT NULL PRIMARY KEY,"
                            + "menuOption VARCHAR(40)"
                            + ");"
            );
            
            // Create table for Menu
            this.stmt.execute(
                    "CREATE TABLE IF NOT EXISTS menu ("
                            + "menuId INT(10) NOT NULL PRIMARY KEY,"
                            + "text VARCHAR(200),"
                            + "title VARCHAR(40)"
                            + ");"
            );
            
            // Create table for MenuItems
            this.stmt.execute(
                    "CREATE TABLE IF NOT EXISTS menuItems ("
                            + "menuItemsId INT(10) NOT NULL PRIMARY KEY,"
                            + "menuId INT(10),"
                            + "menuOptionId INT(10),"
                            + "FOREIGN KEY(menuId) REFERENCES menu(menuId),"
                            + "FOREIGN KEY(menuOptionId) REFERENCES menuOption(menuOptionId)"
                            + ");"
            );
                                
            return true;
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Error creating database tables: "+e.getMessage());
            return false;
        }
    }
    
    
     /**
     * Insert set up values for User
     * @return - true if is success
     */
    public boolean createUsers(){
        String value =  "INSERT IGNORE INTO user (email, password)\n"
                    + "VALUES ('admin@gmail.com', 'admin'),('test@gmail.com', '1234'); \n";
        return this.insertValuesToTable(value);
    }
    
     /**
     * Insert set up values for User
     * @return - true if is success
     */
    public boolean createMovies(){
        String value = "INSERT IGNORE INTO movie (movieId, title)\n"
                    + "VALUES (1, 'Dune'), (2,'Barbie'), (3,'Avatar'), (4,'Talk To Me'), (5,'Hobbit'), (6,'A Quiet Place'), (7,'The Nun'), (8,'Scary Movie'), (9,'Get Out'), (10,'Oppenheimer'); \n";
        return this.insertValuesToTable(value);
    }
    
    /**
     * Insert set up values for User
     * @return - true if is success
     */
    public boolean createRentOptions(){
        String value = "INSERT IGNORE INTO rent (rentId, description, length, price)\n"
                    + "VALUES (1,'One minute rent', 1, 5), (2,'One hour rent', 60, 10), (3,'One day rent', 1440, 20); \n";
        return this.insertValuesToTable(value);
    }
    
    /**
     * Insert set up values for menu options
     * @return - true if is success
     */
    public boolean createMenuOptions(){
        String value = "INSERT IGNORE INTO menuOption (menuOptionId, menuOption)\n"
                    + "VALUES (1,'Register'),(2,'Login'),(3,'Logout'),(4,'Back'),(5,'Exit'),(6,'Rent Movie'),(7,'See rented movies'),(8,'View Recommended Movies'); \n";
        return this.insertValuesToTable(value);
    }
    
    /**
     * Insert values for all available menus
     * @return - true if is success
     */
    public boolean createMenus(){
        String value = "INSERT IGNORE INTO menu (menuId, text, title)\n"
                    + "VALUES (1, 'Select option above by typing a number next to item', 'Login Menu'),(2,'Select option above by typing a number next to item', 'Main Menu'),(3,'Select Movie to Rent by typing a number next to item', 'Movie Menu'),(4,'Select Rent Duration by typing a number next to item', 'Rent Menu'); \n";
        return this.insertValuesToTable(value);
    }
    
     /**
     * Insert values for all available menus
     * @return - true if is success
     */
    public boolean createMenuItems(){
        String value = "INSERT IGNORE INTO menuItems (menuItemsId, menuId, menuOptionId)\n"
                    + "VALUES (1, 1, 2),(2, 1, 1),(3, 1, 5), \n" // login menu have options login, register, exit  
                    + "(4, 2, 6),(5, 2, 7),(6, 2, 8),(7, 2, 3),(8, 2, 5), \n" // main menu have options rent movie, see recommended movies, See your rented movies, logout, exit
                    + "(9,3, 4), \n" // movie menu will have option back and movies will be added later
                    + "(10, 4, 4); \n"; // rent menu will have option back and rent options will be added later
        return this.insertValuesToTable(value);
    }
    
    public boolean insertValuesToTable(String value){
        try{
            this.stmt.execute(value);
            return true;
        }catch (SQLException e){
            //e.printStackTrace();
            System.out.println("Error: "+e.getMessage());
            return false; 
        }  
    }
    
}



