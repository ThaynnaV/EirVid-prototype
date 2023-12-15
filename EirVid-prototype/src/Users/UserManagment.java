/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Users;

import Utilities.Utilities;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 2021267
 */
public class UserManagment implements UserManagmentInterface{
    private final Utilities utility = new Utilities();
    // list of registred users, keep in memory for now, later implement database
    private static final Map<String, String> registeredUsers = new HashMap<>();
    
    @Override
    public void performLogin(User user) {
        // Get user email
        String username = this.utility.getUserText("Enter your username (email): "); // to do add check for email validity later?
        String password = this.utility.getUserText("Enter your password: "); // to do add minimum password complexity later?
        
        // Check if the user is already registered
        if (this.login(username, password)) {
            System.out.println("Login successful! Welcome, " + username);
        } else {
            System.out.println("Invalid login. Please check your username and password.");
        }
    }
    
    @Override
    public boolean register(String username, String password) {
         if (!registeredUsers.containsKey(username)) {
            // Register the user
            registeredUsers.put(username, password);
            return true;
        }
        return false; // User with the given email already exists

    }

    @Override
    public boolean login(String username, String password) {
        return registeredUsers.containsKey(username) && registeredUsers.get(username).equals(password);
    }
    
    public void loginScreen(){
        
    }

}
