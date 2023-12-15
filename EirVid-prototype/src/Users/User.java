/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Users;

/**
 *
 * @author 2021267
 */
public class User implements UserInterface{
    private String password;
    private String username;
    
    public void User(){}

    /**
     * Implementing an Interface method setPassord
     * Method will set User password
     * @param password 
     */
    @Override
    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public void setUserName(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUserName() {
        return this.username;
    }
 
    
}