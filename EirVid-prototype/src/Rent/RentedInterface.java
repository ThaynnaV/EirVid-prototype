/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Rent;

import java.sql.Timestamp;

/**
 *
 * @author Gabriel 2021240
 */
public interface RentedInterface {
    String getEmail();
    int getMovieId();
    int getRentOptionId();
    Timestamp getDate();
    double getTotalPrice();
    void setEmail(String email);
    void setMovieId(int movieId);
    void setRentOptionId( int rentOptionId);
    void setDate(Timestamp date);
    void setDateToNow();
    void setTotalPrice(double totalPrice);
}

