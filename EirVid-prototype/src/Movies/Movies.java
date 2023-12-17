/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Movies;

import DatabaseManagment.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Alany 2021345
 */
public class Movies {
    private final ArrayList<Movie> movies = new ArrayList<>();
    private final Database db;
    public Movies(Database db ) throws SQLException{
        this.db = db;
        this.getMoviesFromDatabase();
    }
    
    private void getMoviesFromDatabase() throws SQLException{
        String useDatabaseByName = "USE " + this.db.getDbName();
        this.db.executeStmt(useDatabaseByName);
        try (ResultSet rs = this.db.executeStmtQuery("SELECT * from movie")) {
            while(rs.next()){
                int id = rs.getInt("movieId");
                String title = rs.getString("title");
                Movie newMovie = new Movie(id, title);
                this.movies.add(newMovie);
            }
        }
    }
    
    public ArrayList<Movie> getMoviesList(){
        return this.movies;
    }
    
    public void listMovieTitles(){
        // Reference stack overflow: https://stackoverflow.com/questions/34526819/print-arraylist-in-java
        for(Movie mv : movies) {
            System.out.println(mv.getMovieTitle());
        }
    }
    
}
