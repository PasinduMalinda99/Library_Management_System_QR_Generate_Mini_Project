/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Connection.ConnectionSQL;
import Model.User;
import java.io.*;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
/**
 *
 * @author Avindu Aloka
 */
public class Registration {
    private Statement state = null;
    private PreparedStatement prepair = null;
    private User registerDoc = null;
    ConnectionSQL cons = new ConnectionSQL();
    
    public void setregisterUser(User registerDoc){
        this.registerDoc = registerDoc;
        registerUser();
    }
    
    private void registerUser(){
        try{
            cons.ConnectionSuccess();
            state = cons.Con.createStatement();
            ResultSet res = state.executeQuery("SELECT MAX(`UserID`) FROM `library`.users");
            int LastIntId = 0;
            while (res.next()) {
                LastIntId = res.getInt(1);
            }
            LocalDateTime cDateTime = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(cDateTime);
            prepair = cons.Con.prepareStatement("INSERT INTO users VALUES(?,?,?,?,?);");
            if (LastIntId == 0) {
                prepair.setInt(1, 1);
                prepair.setString(4, "Admin");
            } else {
                prepair.setInt(1, LastIntId + 1);
                prepair.setString(4, "Client");
            }
            prepair.setString(2, registerDoc.getUserName());
            prepair.setString(3, registerDoc.getPassWord());
            prepair.setTimestamp(5, timestamp);

            int i = prepair.executeUpdate();
            System.out.println("Recode Success");
            
            cons.ConnectionClose();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
