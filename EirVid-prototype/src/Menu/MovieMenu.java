/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menu;

import Movies.Movie;
import Movies.Movies;
import java.util.ArrayList;

/**
 *
 * @author Thaynna 2021288
 */
public class MovieMenu implements MovieMenuInterface{
   private Menu menu = new Menu("Movie Menu", "Select Movie by typing a number next to movie");
   
    public MovieMenu(){}
    
    public MovieMenu(Movies movies){
        int selector = 1;
        ArrayList<MenuItem> movieItems = new ArrayList<>();
        // for each rent option
        for(Movie movie : movies.getMoviesList()) {
            this.menu.addMenuItem(selector, movie.getMovieTitle());
            selector++;
        }
        // add go Back Selector
        this.menu.addMenuItem(selector, "Back");
    }
    
    public Menu getMenu(){
        return this.menu;
    }
    
    /**
     * 
     * @return selected movie as integer 
     */
    public int showMenu(){
        return this.menu.showMenu();
    }
}
