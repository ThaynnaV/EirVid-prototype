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
                System.out.println("Invalid input. Please enter a valid integer.");
                isValid = false;
            }
            // Consume the input
            this.myKB.nextLine();
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
    /**
     * Get user option from menu as integer
     * Keep asking until user type it correctly
     * @param message - message to display to user
     * @param minAlowed - limit min value
     * @param maxAllowed - limit max value
     * @return - the users input as a String
    */
    public int getUserOptionInRange(String message, int minAlowed, int maxAllowed){
        int userInput = 0; //set userInput to 0 initially
        boolean isValid = false;
        try {
            // prompt user until input is valid
            do{
                System.out.println(message);
                String input = this.myKB.nextLine();
                //check if is number
                if(input.matches("[0-9]+")){
                    //set user input to number
                    userInput = Integer.parseInt(input);
                    //check if is available option
                    if(userInput == 0 || userInput > maxAllowed || userInput < minAlowed){
                        System.out.println("There is no that option available. Please try again.");
                        userInput = 0;
                    }
                } else {
                    System.out.println("Try again. Please choose number.");
                    userInput = 0;
                }
            } while (userInput == 0);
        } catch(Exception e){
            System.out.println("Error "+e);
        }
        // return user input
        return userInput;
    }    
}