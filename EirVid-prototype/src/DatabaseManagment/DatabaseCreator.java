/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseManagment;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author 2021240
 */
public class DatabaseCreator {
    private Statement stmt;
    private Connection conn;
    private String dbName = "";
    
     // CONSTRUCTOR
    public DatabaseCreator(){}
    
    /**
     * Set up database.
     * First creates an object DataBaseConnection class and use it to connect to database.
     * Then create tables and populate them with initial values
     * @return 
     */
    public boolean setupDatabase(){
        try{
            //create an object of DataBaseConnection class
            DatabaseConnection dBConnection = new DatabaseConnection();
            // first connect to database
            boolean isConnectedToDatabase = dBConnection.connectToDatabase();
            // stop program if we can't connect to database
            if(!isConnectedToDatabase){
                System.out.println("Can't connect to database");
                return false;
            }else{
                // if connected set statment, connection and db name
                this.conn = dBConnection.getConnection();
                this.stmt = dBConnection.getStatement();
                this.dbName = dBConnection.getDbName();
            }
            
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
            boolean isMoviesCreated = this.createMovies();
            if(!isMoviesCreated){
                System.out.println("Error inserting values to movie table");
                return false;
            }
            
            // INSERT values for initial rent options if values already not exist
            boolean isRentCreated = this.createRentOptions();
            if(!isRentCreated){
                System.out.println("Error inserting values to rent table");
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
                            + "userId INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                            + "username VARCHAR(30),"
                            + "password VARCHAR(30)"
                            + ");"
            );
            
            // Create table for Movies
            this.stmt.execute(
                    "CREATE TABLE IF NOT EXISTS movie ("
                            + "movieId INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                            + "title VARCHAR(30)"
                            + ");"
            );
            
            // Create table for Rent
            this.stmt.execute(
                    "CREATE TABLE IF NOT EXISTS rent ("
                            + "rentId INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                            + "description VARCHAR(40),"
                            + "length INT(10),"
                            + "price INT(10)"
                            + ");"
            );
            
            // Create table Rented movies
            this.stmt.execute(
                    "CREATE TABLE IF NOT EXISTS rented ("
                            + "rentedId INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                            + "userId INT(10),"
                            + "movieId INT(10),"
                            + "rentId INT(10),"
                            + "date TIMESTAMP,"
                            + "FOREIGN KEY(userId) REFERENCES user(userId),"
                            + "FOREIGN KEY(movieId) REFERENCES movie(movieId),"
                            + "FOREIGN KEY(rentId) REFERENCES rent(rentId)"
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
        try{
            this.stmt.execute(
                    "INSERT IGNORE INTO user (username, password)\n"
                    + "VALUES ('admin', 'admin'); \n"    
                         
            );
            this.stmt.execute(
                    "INSERT IGNORE INTO user (username, password)\n"
                    + "VALUES ('test', '123'); \n"    
                         
            );
            return true;
        }catch (SQLException e){
            //e.printStackTrace();
            System.out.println("Error: "+e.getMessage());
            return false; 
        }      
    }
    
     /**
     * Insert set up values for User
     * @return - true if is success
     */
    public boolean createMovies(){
        try{
            this.stmt.execute(
                    "INSERT IGNORE INTO movie (title)\n"
                    + "VALUES ('Dune'), ('Barbie'), ('Avatar'), ('Talk To Me'), ('Hobbit'), ('A Quiet Place'), ('The Nun'), ('Scary Movie'), ('Get Out'), ('Oppenheimer'); \n"    
            );
            return true;
        }catch (SQLException e){
            //e.printStackTrace();
            System.out.println("Error: "+e.getMessage());
            return false; 
        }      
    }
    
      /**
     * Insert set up values for User
     * @return - true if is success
     */
    public boolean createRentOptions(){
        try{
            this.stmt.execute(
                    "INSERT IGNORE INTO rent (description, length, price)\n"
                    + "VALUES ('One minute rent', 1, 5), ('One hour rent', 60, 10), ('One day rent', 1440, 20); \n"    
            );
            return true;
        }catch (SQLException e){
            //e.printStackTrace();
            System.out.println("Error: "+e.getMessage());
            return false; 
        }      
    }
}

