/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Movies;

import Rent.Rent;
import Users.User;

/**
 *
 * @author 2021345
 */
public interface RentedMovieInterface {
    User getPerson();
    Movie getMovie();
    Rent getRentInformation();
    String getRentedMovieInfo();
    void setPerson(User person);
    void setMovie(Movie movie);
    void setRentInformation(Rent rentInformation);
}
