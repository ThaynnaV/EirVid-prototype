/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Users;

import DatabaseManagment.Database;
import Utilities.Utilities;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 2021267 / Magdalena
 */
public class UserManagment implements UserManagmentInterface{
    private final Utilities utility = new Utilities();
    private Database db;
    private User currentUser = new User();
    
    public UserManagment(Database db){
        this.db = db;
    }
    
    // GETTERS
    public User getCurrentUser(){
        return this.currentUser;
    }
    /**
     * Keep asking to login until logged in or exit option selected
     * @return 
     */
    @Override
    public boolean login() {
        boolean isLogin = true;
        boolean isLoggedIn = false;
        while(isLogin && !isLoggedIn){
            System.out.println("LOGIN");
            // Get user email
            String email = this.utility.getUserText("Enter your email: "); // to do add check for email validity later?
            // Get user password
            String password = this.utility.getUserText("Enter your password: "); // to do add minimum password complexity later?
            // create loging user with credentials that user provided
            this.currentUser = new User(email, password);
            isLoggedIn = this.checkUserCredentials(this.currentUser);
            if(!isLoggedIn){
                int option = this.utility.getUserInteger("Error on login. Type 1 to try again type 2 or any other number to exit");
                if(option != 1){
                    isLogin = false;
                }
            }
        }
        return isLoggedIn;
    }
    
    @Override
    public boolean logout() {
       // Reset current user
       this.currentUser.setPassword(null);
       this.currentUser.setEmail(null);
       return true;
    }
    
    @Override
    public boolean register() {
        boolean isRegistering = true;
        boolean isRegistered = false;
        while(isRegistering && !isRegistered){
            System.out.println("REGISTER");
            // Get email from user
            String email = this.utility.getUserText("Enter email: "); 
            // create new user user with credentials that user provided
            User newUser = new User();
            newUser.setEmail(email);
            // if user with that email already exist return false
            if (this.checkIfUserExists(newUser)){
                System.out.println("User with that email already exist.");
                int option = this.utility.getUserInteger("Type 1 to try with other email, type any other number to exit.");
                if(option == 1){
                     continue; // Skip the rest of the loop and go to the next iteration
                }else{
                    isRegistering = false; // exit the while
                    continue; // Skip the rest of the loop and go to the next iteration
                }
            }
            
            // Get password from user
            String password = this.utility.getUserText("Enter password: "); // to do add minimum password complexity later?
            newUser.setPassword(password);
            // insert new user (register) to database
            if(!insertUserToDatabase(newUser)){
                // if problem inserting ask again
                System.out.println("User could not be registred");
                int option = this.utility.getUserInteger("Type 1 to try wagain, type any other number to exit.");
                if(option != 1){
                     isRegistering = false; // if exit selected exit while loop
                }
            }else{
                isRegistered = true;
            }
        }
        return isRegistered;
    }

    //Check if credentials inputed are valid.
    private boolean checkUserCredentials(User user){
        String useDatabaseByName = "USE " + this.db.getDbName();
        boolean isSuccess = false;
        try {
            this.db.executeStmt(useDatabaseByName);
            String query = "SELECT * from user \n" +
                            "WHERE email = '" + user.getEmail() + "'"; // email will be unique so result set should return one value
            try (ResultSet rs = this.db.executeStmtQuery(query)) {
                
                // checking if ResultSet is empty 
                if (rs.next() == false) { 
                    System.out.println("No user with that email found."); 
                } else{
                    do{
                        String password = rs.getString("password");
                        //isSuccess = (password == null ? user.getPassword() == null : password.equals(user.getPassword()));
                        if(password == null || user.getPassword() == null || !password.equals(user.getPassword())){
                            System.out.println("Wrong password");
                        } else{
                           isSuccess = true;
                        }
                    }while(rs.next());
                }
                rs.close();
            }
            return isSuccess;
        }catch (SQLException e){
            System.out.println("Error executing statment to database: " +e.getMessage());
            return false;
        }
    }
   
    //Check with database if the user is already there.
    private boolean checkIfUserExists(User user){
        String useDatabaseByName = "USE " + this.db.getDbName();
        boolean isUserFound = false;
        try {
            this.db.executeStmt(useDatabaseByName);
            String query = "SELECT * from user \n" +
                            "WHERE email = '" + user.getEmail() + "'"; // email should be unique
            try (ResultSet rs = this.db.executeStmtQuery(query)) {
                if (rs.next() == false) { 
                    System.out.println("No user with that email found."); 
                }else{
                    isUserFound = true;
                }
                rs.close();
            }
            return isUserFound;
        }catch (SQLException e){
            System.out.println("Error executing statment to database: " +e.getMessage());
            return false;
        }
    }
    
    //add User to db
    private boolean insertUserToDatabase(User user){
        String useDatabaseByName = "USE " + this.db.getDbName();
        try {
            this.db.executeStmt(useDatabaseByName);
            String query = String.format("INSERT INTO user (email, password) VALUES (\"%s\", \"%s\");",
                            user.getEmail(), user.getPassword());
            
            this.db.executeStmt(query);
            return true;
        }catch (SQLException e){
            System.out.println("Error executing statment to database: " +e.getMessage());
            return false;
        }
        
    }

}
