/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Movies;

import Rent.Rent;
import Users.User;
import java.sql.Timestamp;

/**
 *
 * @author 2021345
 */
public class RentedMovie implements RentedMovieInterface {
    private User person;
    private Movie movie;
    private Rent rentInformation;
    private Timestamp date;
    
    public RentedMovie(){
    }
    
    public RentedMovie(User person, Movie movie,Rent rent, Timestamp date){
        this.person = person;
        this.movie = movie;
        this.rentInformation = rent;
        this.date = date;
    }
    
    // GETTERS
    @Override
    public User getPerson() {
        return person;
    }

    @Override
    public Movie getMovie() {
        return movie;
    }

    @Override
    public Rent getRentInformation() {
        return rentInformation;
    }
    
    @Override
    public String getRentedMovieInfo(){
        return "Movie: " + this.movie.getMovieTitle() + " | Rent: " + this.rentInformation.getDescription() + " | RentPrice: " + this.rentInformation.getPrice() + " | Date Time: " + this.date;
                
    }
    
    //SETTERS
    @Override
    public void setPerson(User person) {
        this.person = person;
    }

    @Override
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public void setRentInformation(Rent rentInformation) {
        this.rentInformation = rentInformation;
    }

}
