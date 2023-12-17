/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Movies;

import DatabaseManagment.Database;
import Rent.Rented;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 2021345
 */
public class RecommendedMovies {
    private Database db;
    private ArrayList<RecommendedMovie> recomendations = new ArrayList<>();
    
    public RecommendedMovies(Database db){
        this.db = db;
    }
    
    /**
     * Display top 5 recommended movies based on 5 most rented movies in last 5 minutes
     */
    public void displayRecommendedMovies(){
        this.recomendations.clear();
        this.getRentedMovies();
        if(this.recomendations.isEmpty()){
            System.out.println("There were no rented movies in last 5 minutes");
            return;
        }
        // Sort the recommendations list based on rent count (descending order)
        Collections.sort(this.recomendations, (rec1, rec2) -> Integer.compare(rec2.getRentCount(), rec1.getRentCount()));
        // Display the top 5 recommended movies
        int topCount = Math.min(5, this.recomendations.size());
        for (int i = 0; i < topCount; i++) {
            RecommendedMovie recMovie = this.recomendations.get(i);
            System.out.println("* Title: " + recMovie.getMovie().getMovieTitle() 
                    + " - Price: " + recMovie.getMovie().getPrice()
                    + " - Rent Count: " + recMovie.getRentCount());
        }
    }
    
    private boolean getRentedMovies(){
        String useDatabaseByName = "USE " + this.db.getDbName();
        boolean isFound = false;
        // Integer map to store movies and count repeat of same movie
        Map<Integer, RecommendedMovie> movieMap = new HashMap<>();
        try {
            this.db.executeStmt(useDatabaseByName);
            String query = "SELECT * FROM rented r INNER JOIN movie m on r.movieId = m.movieId WHERE r.date >= NOW() - INTERVAL 5 MINUTE; ";
            
            try (ResultSet rs = this.db.executeStmtQuery(query)) {
                do{
                    if (rs.next() == false) { 
                        System.out.println("No movies rented in last 5 minutes."); 
                    } else {
                        // rented
                        int rentedId = rs.getInt("rentedId");
                        String email = rs.getString("email");
                        int movie_Id = rs.getInt("movieId");
                        int rentId = rs.getInt("rentId");
                        Timestamp date = rs.getTimestamp("date");
                        double totalPrice = rs.getDouble("totalPrice");
                        Rented newRented = new Rented(email, movie_Id, rentId, totalPrice);
                        newRented.setDate(date);
                        // movie
                        int movieId = rs.getInt("movieId");
                        String title  = rs.getString("title");
                        String original_language  = rs.getString("original_language");
                        String original_title  = rs.getString("original_title");
                        String overview  = rs.getString("overview");
                        double popularity = rs.getDouble("popularity");   
                        String release_date = rs.getString("release_date");
                        int runtime = rs.getInt("runtime");
                        String tagline  = rs.getString("tagline");
                        double vote_average = rs.getDouble("vote_average");
                        int vote_count = rs.getInt("vote_count");
                        double price = rs.getDouble("price");
                        Movie newMovie = new Movie(movieId, title, original_title, original_language, overview, popularity, release_date, runtime, tagline, vote_average, vote_count, price);
                                                
                        // Check if the movie is already in the map
                        if (movieMap.containsKey(movieId)) {
                            RecommendedMovie existingRecMovie = movieMap.get(movieId);
                            existingRecMovie.setRentCount(existingRecMovie.getRentCount() + 1);
                        } else {
                            // Create a new RecommendedMovie with the count
                            RecommendedMovie recMovie = new RecommendedMovie(newRented, newMovie);
                            recMovie.setRentCount(1);
                            movieMap.put(movieId, recMovie);
                        }
                        
                        isFound = true;
                    }
                } while(rs.next());
                // Add the values from the map to the recommendations list
                this.recomendations.addAll(movieMap.values());
                rs.close();    
            }
        }catch (SQLException e){
            System.out.println("Error executing statment to database: " +e.getMessage());
        }
        return isFound;
    }
    
    
    
    

}
