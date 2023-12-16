/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Rent;

import java.sql.Timestamp;
import java.time.Instant;


/**
 *
 * @author 2021240
 */
public class Rented implements RentedInterface{
    private String email;
    private int movieId;
    private int rentOptionId;
    private Timestamp date;
    
    public Rented(){}
    
    public Rented(String email,int movieId, int rentOptionId){
        this.email = email;
        this.movieId = movieId;
        this.rentOptionId = rentOptionId;
    }
    // GETTERS
    @Override
    public String getEmail(){
        return this.email;
    }
    @Override
    public int getMovieId(){
        return this.movieId;
    }
    @Override
    public int getRentOptionId(){
        return this.rentOptionId;
    }
    @Override
    public Timestamp getDate(){
        return this.date;
    }
    // SETTERS
    @Override
    public void setEmail(String email){
        this.email = email;
    }
    @Override
    public void setMovieId(int movieId){
        this.movieId = movieId;
    }
    @Override
    public void setRentOptionId( int rentOptionId){
        this.rentOptionId = rentOptionId;
    }
    @Override
    public void setDate(Timestamp date){
        this.date = date;
    }
    @Override
    public void setDateToNow(){
        Instant instantTime = Instant.now();
        this.date = Timestamp.from(instantTime);
    }
 
}
