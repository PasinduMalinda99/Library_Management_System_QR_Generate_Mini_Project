/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Connection.ConnectionSQL;
import Model.Book;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author Avindu Aloka
 */
public class AddBook {

    private Statement state = null;
    private PreparedStatement prepair = null;
    private Book Logbook = null;
    ConnectionSQL cons = new ConnectionSQL();

    public boolean setCreateBook(Book Logbook) {
        this.Logbook = Logbook;
        return CreateBook();
    }

    private boolean CreateBook() {
        boolean Result = false;
        try {
            cons.ConnectionSuccess();
            state = cons.Con.createStatement();
            ResultSet res = state.executeQuery("SELECT MAX(`BookID`) FROM `library`.books");
            int LastIntId = 0;
            while (res.next()) {
                LastIntId = res.getInt(1);
            }
            
            LocalDateTime cDateTime = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(cDateTime);
            prepair = cons.Con.prepareStatement("INSERT INTO books VALUES(?,?,?,?,?);");
            if (LastIntId == 0) {
                prepair.setInt(1, 1);
            } else {
                prepair.setInt(1, LastIntId + 1);
            }
            prepair.setString(2, Logbook.getBookTitle());
            prepair.setString(3, Logbook.getBookDescription());
            prepair.setTimestamp(4, timestamp);
            prepair.setString(5, Logbook.getBookAuthor());
            
            int i = prepair.executeUpdate();
            System.out.println("Recode Success");
            
            cons.ConnectionClose();
            Result = true;
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return Result;
    }
    
    /*public static void main(String[] args) {
        AddBook adbk = new AddBook();
        adbk.Logbook = new Book();
        adbk.Logbook.setBookTitle("Toloka");
        adbk.Logbook.setBookDescription("Hello, How Are you?");
        adbk.Logbook.setBookAuthor("Toloka");
        System.out.println(adbk.CreateBook());
    }*/
}
