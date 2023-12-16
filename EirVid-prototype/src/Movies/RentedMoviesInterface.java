/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Movies;

import Rent.Rented;
import java.util.ArrayList;

/**
 *
 * @author 2021345
 */
public interface RentedMoviesInterface {
    ArrayList<RentedMovie> getRentedMovies();
    void setRentedMovies(ArrayList<RentedMovie> rentedMovies);
    void setRentedMoviesByEmail(String email);
    void displayAllRentedMovies();
    boolean isRentedMovies();
    boolean save(Rented rented);
}
