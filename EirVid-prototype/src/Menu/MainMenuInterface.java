/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Menu;

import Movies.Movies;
import Movies.RentedMovies;
import Users.UserManagment;

/**
 *
 * @author 2021288
 */
public interface MainMenuInterface {
     Menu getMenu();
     boolean showMenu(UserManagment userManagment, RentMenu rentMenu, MovieMenu movieMenu, RentedMovies rentedMovies, Movies movies);
}
