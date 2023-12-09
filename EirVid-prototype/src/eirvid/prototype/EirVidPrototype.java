/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package eirvid.prototype;

import DatabaseManagment.DatabaseCreator;
import Utilities.Utilities;

/**
 *
 * @author user
 */
public class EirVidPrototype {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Utilities utility = new Utilities();
        // Connect to database and create tables and add values
        DatabaseCreator myDatabase = new DatabaseCreator();
        boolean isDatabaseSetUp = myDatabase.setupDatabase();
        if(!isDatabaseSetUp){
            // if we can't connect to database break application
            return;
        }else{
            System.out.println("Success");
        }
    }
    
}
