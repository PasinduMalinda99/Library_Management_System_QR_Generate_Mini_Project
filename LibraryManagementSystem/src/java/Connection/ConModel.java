/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connection;

/**
 *
 * @author Avindu Aloka
 */
public class ConModel {
    private static String url = "jdbc:mysql://localhost:3306/library";
    private static String username = "root";
    private static String password = "1234";

    public static String getUrl() {
        return url;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }
    
}
