/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package eirvid.prototype;

import DatabaseManagment.Database;
import Menu.LoginMenu;
import Menu.MainMenu;
import Menu.MovieMenu;
import Menu.RentMenu;
import Movies.MovieReader;
import Movies.Movies;
import Movies.RecommendedMovies;
import Movies.RentedMovies;
import Rent.RentOptions;
import Users.UserManagment;
import java.sql.SQLException;

/**
 *
 * @authors 2021345, 2021267, 2021288, Gabriel 2021240   
 */
public class EirVidPrototype {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        // CREATE OBJECT of all CLASSSES we will need
        // Database class object connect to database and create initial data
        Database db = new Database();
        // Movies class object -- has all available movies
        Movies movies = new Movies();
        // Movie Reader object -- use to reads CSV file and save movies to database
        MovieReader movieReader = new MovieReader(movies, db);
        // if file are read is siccess
        boolean success = movieReader.readMoviesFromCSV("./Movie_Metadata.csv");
        // if movie file is not read break application with error message
        if(!success){
            System.out.println("Error reading movies from file");
        }
        // Rent Options class object -- has all available rent options (different rent prices and durations)
        RentOptions rentOptions = new RentOptions(db);
        // User managment class object -- to handle user managment 
        UserManagment userManagment = new UserManagment(db);
        // MENUS
        // Login Menu object -- to hanfle log in and registration
        LoginMenu loginMenu = new LoginMenu(db);
        // Main menu object -- to handle main meu options
        MainMenu mainMenu = new MainMenu(db);
        // Movie Menu Object -- for selecting movie to rent
        MovieMenu movieMenu = new MovieMenu(movies);
        // Rent Menu object -- for displaying rent options
        RentMenu rentMenu = new RentMenu(rentOptions);
        // Rented movies object -- to get rented movies from database
        RentedMovies rentedMovies = new RentedMovies(db);
        // Recommended movies object -- to get recomended movies from database
        RecommendedMovies recMovies = new RecommendedMovies(db);
        // VARIABLES
        boolean isLoggedIn; // check if user is logged in
        boolean isExit = false; // if isExit is true exit application
        do{
            // Show login menu with options: to login, register or exit application
            isLoggedIn = loginMenu.showMenu(userManagment);
            // If logged in  
            if(isLoggedIn){
                // Show Main menu options rent a movie, See my rented movies, view recommended movies, logout, exit
                boolean isMainMenuBack = mainMenu.showMenu(userManagment, rentMenu, movieMenu, rentedMovies, movies, recMovies);
                // if true returned go back to login, if false returned exit application by exiting while loop
                if(!isMainMenuBack){
                    isExit = true;
                }
            }else{
                isExit = true;
            }
        }while(!isExit);
    }
    
}
