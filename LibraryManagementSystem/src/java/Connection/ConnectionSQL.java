/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connection;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author Avindu Aloka
 */
public class ConnectionSQL extends ConModel{
    public Connection Con = null;
    
    //Connecting The Connection of SQL
    public void ConnectionSuccess(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Con = DriverManager.getConnection(getUrl(), getUsername(), getPassword());
            System.out.println("Connection Success!");
        }
        catch(Exception ex){
            System.out.println("Sorry Sql Not Connected! Error : " + ex );
        }
    }
    
    //Conenct Connection Close
    public void ConnectionClose(){
        try{
            if(Con != null){
                Con.close();
            }
            System.out.println("Connection Closed!");
        }
        catch(Exception ex){
            System.out.println("Sorry Sql Not Connected! Error : " + ex );
        }
    }
    
}
