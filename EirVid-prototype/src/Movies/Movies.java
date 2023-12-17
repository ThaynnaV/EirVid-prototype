/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Movies;

import java.util.ArrayList;

/**
 *
 * @author Alany 2021345
 */
public class Movies implements MoviesInterface {
    private final ArrayList<Movie> movies = new ArrayList<>();
        
    public Movies(){}
    
    @Override
    public ArrayList<Movie> getMoviesList(){
        return this.movies;
    }
    
    @Override
    public Movie getMovieByIndex(int index){
        return this.movies.get(index);
    }
    
    /**
     * Returns movie based on id
     * @param id
     * @return 
     */
    @Override
    public Movie getMovieById(int id){
        for (Movie movie : this.movies) {
            if (movie.getMovieId() == id) {
                return movie; 
            }
        }
        return null;
    }
    
    @Override
    public int getMovieIdByIndex(int index){
        return this.movies.get(index).getMovieId();
    }
    
    @Override
    public void listMovieTitles(){
        // Reference stack overflow: https://stackoverflow.com/questions/34526819/print-arraylist-in-java
        for(Movie mv : movies) {
            System.out.println(mv.getMovieTitle());
        }
    }
    
    
}
