/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Rent;

/**
 For renting
 * @author 2021240
 */
public interface RentInterface {
    int getRentId();
    String getDescription();
    int getLength();
    int getPrice();
    void setRentId(int _rentId);
    void setDescription(String _description);
    void setLength(int _length);
    void setPrice(int _price);
    String getFullRentItem();
}

