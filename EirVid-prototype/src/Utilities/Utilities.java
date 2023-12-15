/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import java.util.Scanner;

/**
 *
 * @author 2021267
 */
public class Utilities {
    private final Scanner myKB = new Scanner(System.in); // create myKb from scanner
    
    /**
     * Get integer value from user
     * Keep asking until user types it correctly
     * @param message - message to display to user
     * @return user input value as integer
     */
    public int getUserInteger(String message){
        int userInput = 0; //set userInput to 0 initially
        boolean isValid;
        do{
            System.out.println(message);
            try{
                userInput = this.myKB.nextInt();
                isValid = true;
            }catch(Exception e){
                isValid = false;
            }   
        }while(!isValid);
        return userInput;
    }
    
    /**
     * Get String value from user
     * Keep asking until user type it correctly
     * @param message - message to display to user
     * @return user input value as String
     */
    public String getUserText(String message){
        String userInput = ""; //set userInput to empty string initially
        boolean isValid;
        do{
            System.out.println(message);
            try{
                userInput = this.myKB.nextLine();
                isValid = true;
            }catch(Exception e){
                isValid = false;
            }   
        }while(!isValid);
        return userInput;
    }    
    
}
