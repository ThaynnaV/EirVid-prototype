/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Users;

/**
 *
 * @author 2021267 / Magdalena
 */
public class User implements UserInterface{
    private String password;
    private String email;
    
    public User(){}
    
    public User(String email, String password){
        this.email = email;
        this.password = password;
    }
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
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getEmail() {
        return this.email;
    }
 
    
}