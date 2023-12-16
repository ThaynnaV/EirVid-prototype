/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Movies;

import java.util.ArrayList;

/**
 *
 * @author 2021345
 */
public interface MoviesInterface {
     ArrayList<Movie> getMoviesList();
     Movie getMovieByIndex(int index);
     int getMovieIdByIndex(int index);
     void listMovieTitles();
}