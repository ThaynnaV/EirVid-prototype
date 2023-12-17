/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Movies;

import DatabaseManagment.Database;
import Rent.Rent;
import Rent.Rented;
import Users.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author 2021345
 */
public class RentedMovies implements RentedMoviesInterface{
    private Database db;
    private ArrayList<RentedMovie> rentedMovies = new ArrayList<>();
    
    
    public RentedMovies(Database db){
        this.db = db;
    }
    
    @Override
    public ArrayList<RentedMovie> getRentedMovies(){
        return this.rentedMovies;
    }
    
    @Override
    public void setRentedMovies(ArrayList<RentedMovie> rentedMovies){
        this.rentedMovies = rentedMovies ;
    }
    
    @Override
    public void setRentedMoviesByEmail(String email){
        this.rentedMovies = this.getRentedMoviesFromDatabase(email);
    }
    
    @Override
    public void displayAllRentedMovies(){
        // Reference stack overflow: https://stackoverflow.com/questions/34526819/print-arraylist-in-java
        for(RentedMovie mv : this.rentedMovies) {
            System.out.println(mv.getRentedMovieInfo());
        }
    }
       
    /**
     * 
     * @return true if there are rented movies
     */
    @Override
    public boolean isRentedMovies(){
        return !this.rentedMovies.isEmpty();
    }
    
    /**
     * Save rented movie to database
     * @param rented
     * @return 
     */
    @Override
    public boolean save(Rented rented){
        String useDatabaseByName = "USE " + this.db.getDbName();
        try {
            this.db.executeStmt(useDatabaseByName);
            String query = String.format("INSERT INTO rented (email, movieId, rentId, date, totalPrice ) VALUES (\"%s\", \"%d\", \"%d\", \"%s\", \"%f\" );",
                            rented.getEmail(), rented.getMovieId(), rented.getRentOptionId(), rented.getDate(), rented.getTotalPrice());
            
            this.db.executeStmt(query);
            return true;
        }catch (SQLException e){
            System.out.println("Error executing statment to database: " +e.getMessage());
            return false;
        }
    }
    
    /**
     * get rented from database
     * @param email
     * @return 
     */
    private ArrayList<RentedMovie> getRentedMoviesFromDatabase(String email){
        String useDatabaseByName = "USE " + this.db.getDbName();
        ArrayList<RentedMovie> _rentedMovies = new ArrayList<>();
        try {
            this.db.executeStmt(useDatabaseByName);
            String query = "SELECT * from rented rtd \n" +
                           "INNER JOIN movie m ON rtd.movieId = m.movieId \n" +
                           "INNER JOIN rent r ON rtd.rentId = r.rentId \n" +
                           "INNER JOIN user u ON rtd.email = u.email \n" +
                           "WHERE rtd.email = '" + email + "'"; // email is unique
            try (ResultSet rs = this.db.executeStmtQuery(query)) {
                if (rs.next() == false) { 
                    System.out.println("No Rented movies for user found."); 
                }else{
                    do{
                        // from movie table
                        int movieId = rs.getInt("m.movieId"); 
                        String movieTitle = rs.getString("m.title");
                        Movie newMovie = new Movie(movieId, movieTitle );
                        // from rent table
                        int rentId = rs.getInt("r.rentId");
                        String rentDescription = rs.getString("r.description"); 
                        int rentLength = rs.getInt("r.length");
                        int rentPrice = rs.getInt("r.price");
                        Rent rentInformation = new Rent(rentId, rentDescription, rentLength, rentPrice);
                        // from user table
                        String userEmail = rs.getString("u.email"); 
                        String userPassword = rs.getString("u.password"); 
                        User person = new User(userEmail,userPassword);
                        // from rented
                        Timestamp rentedDate = rs.getTimestamp("rtd.date"); 
                        RentedMovie rentedMovie = new RentedMovie(person, newMovie, rentInformation, rentedDate  ); // User person, Movie movie,Rent rent, Timestamp date
                        _rentedMovies.add(rentedMovie);
                    }while(rs.next());
                }
                rs.close();
            }
        }catch (SQLException e){
            System.out.println("Error executing statment to database: " +e.getMessage());
        }
        return _rentedMovies;
    }
}

