/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Users;

/**
 *
 * @author 2021267
 */
public interface UserManagmentInterface {
    void performLogin(User user);
    boolean register(String email, String password);
    boolean login(String email, String password);
}
