/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Rent;

import DatabaseManagment.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author 2021240
 */
public class RentOptions {
    private Database db;
    private ArrayList<Rent> options = new ArrayList<>(); 
    
    /**
     * Constructor that creates object of class RentOptions
     */
    public RentOptions(){}
    
    /**
     * Creates object of class RentOptions
     * constructor that takes Database db as parameter
     * @param db 
     */
    public RentOptions(Database db){
        this.db = db;
        this.getRentOptionsFromDatabase();
    }
    
    /**
     * Get all rent options from database
     */
    private void getRentOptionsFromDatabase(){
        String useDatabaseByName = "USE " + this.db.getDbName();
        try {
            this.db.executeStmt(useDatabaseByName);
            try (ResultSet rs = this.db.executeStmtQuery("SELECT * from rent")) {
                while(rs.next()){
                    int rentId = rs.getInt("rentId");
                    String description = rs.getString("description");
                    int length = rs.getInt("length");
                    int price = rs.getInt("price");
                    this.options.add(new Rent(rentId, description, length, price));
                }
                rs.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void displayRentOptions(){
        if(this.options.isEmpty()){
            System.out.println("No available rent options");
            return;
        }
         // Reference stack overflow: https://stackoverflow.com/questions/34526819/print-arraylist-in-java
        for(Rent options : this.options) {
            System.out.println(options.getFullRentItem());
        }
    }
    
    public ArrayList<Rent> getOptions(){
        return this.options;
    }
}
