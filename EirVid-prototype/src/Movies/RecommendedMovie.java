/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Movies;

import Rent.Rented;

/**
 *
 * @author 2021345
 */
public class RecommendedMovie {
    private Rented rented;
    private Movie movie;
    private int rentCount;
    
    public RecommendedMovie(){}
    public RecommendedMovie(Rented rented, Movie movie){
        this.rented = rented;
        this.movie = movie;
    }
    
    public Movie getMovie(){
        return this.movie;
    }
    
    public Rented getRented(){
        return this.rented;
    }
    
    public int getRentCount(){
        return this.rentCount;
    }
    
    public void setMovie(Movie movie){
        this.movie = movie;
    }
    
    public void setRented(Rented rented){
        this.rented = rented;
    }
    
     public void setRentCount(int rentCount){
        this.rentCount = rentCount;
    }
}
