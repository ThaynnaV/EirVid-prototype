/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseManagment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class for all Database methods 
 * Used MySQL Workbench on localhost 
 * @author 2021240
 */
public class Database {
    //create an object of DataBaseConnection class
    private DatabaseConnection dBConnection = new DatabaseConnection();
    private DatabaseCreator myDatabase;
    private Connection conn;
    private Statement stmt;
    private String dbName;
    
    // CONSTRUCTOR
    public Database(){
        //Connect to database
        try {
           boolean isConnectedToDatabase = dBConnection.connectToDatabase();
           // if we can't connect display error message
            if(!isConnectedToDatabase){
                System.out.println("Can't connect to database");
                return;
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException  e) {
            System.out.println("Error setting up database: " +e.getMessage());
            return;
        }
        // if connected set statment, connection and db name
        this.conn = dBConnection.getConn();
        this.stmt = dBConnection.getStmt();
        this.dbName = dBConnection.getDbName();
        // Connect create tables and add initial values if they don't exist
        this.myDatabase = new DatabaseCreator(this.stmt, this.conn, this.dbName);
        boolean isDatabaseSetUp = myDatabase.setupDatabase();
        if(!isDatabaseSetUp){
            // if error setting up data and database break application
            System.out.println("Error setting up data and database");
        }
    }
    
    public String getDbName(){
        return this.dbName;
    }
    
    public void executeStmt(String query) throws SQLException, SQLException{
        this.stmt.execute(query);
    }
    
    public ResultSet executeStmtQuery(String query) throws SQLException{
        return this.stmt.executeQuery(query);
    }
    
    
}


