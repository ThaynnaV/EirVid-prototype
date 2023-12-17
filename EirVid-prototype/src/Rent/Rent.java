/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Rent;

/**
 * For renting
 * @author Gabriel 2021240
 */
public class Rent implements RentInterface{
    private int rentId;
    private String description;
    private int length; //  rent length in minutes
    private int price;
    
    /**
     * Constructor that creates object of class Rent
     */
    public Rent(){}
    
    /**
     * Creates object of class Rent
     * With parameters
     * @param _rentId
     * @param _description
     * @param _length
     * @param _price 
     */
    public Rent(int _rentId, String _description, int _length, int _price){
        this.rentId = _rentId;
        this.description = _description;
        this.length = _length;
        this.price = _price;
    }
    // GETTERS
    @Override
    public int getRentId(){
        return this.rentId;
    }
    @Override
    public String getDescription(){
        return this.description;
    }
    @Override
    public int getLength(){
        return this.length;
    }
    @Override
    public int getPrice(){
        return this.price;
    }
    
    // SETTERS
    @Override
    public void setRentId(int _rentId){
        this.rentId = _rentId;
    }
    @Override
    public void setDescription(String _description){
        this.description = _description;
    }
    @Override
    public void setLength(int _length){
        this.length = _length;
    }
    @Override
    public void setPrice(int _price){
        this.price = _price;
    }
    // METHODS
    /**
     * 
     * @return description, length and price formatted for display as Sting 
     */
    @Override
    public String getFullRentItem(){
       return "Description: " + this.description + ", Length: " + this.length + " min, Increase of Movie Price for this duration: "+ this.price + " EUR";
    }
    
}

