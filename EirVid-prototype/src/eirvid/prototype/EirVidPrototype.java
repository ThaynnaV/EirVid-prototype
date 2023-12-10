/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package eirvid.prototype;

import DatabaseManagment.Database;
import Movies.Movies;
import Utilities.Utilities;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class EirVidPrototype {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Utilities utility = new Utilities();
        // connect to database and create initial data
        Database db = new Database();
         // Create object of movies class and get all available movies
        Movies movies = new Movies(db);
        movies.listMovieTitles();
    }
    
}
