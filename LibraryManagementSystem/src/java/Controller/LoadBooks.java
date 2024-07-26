/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Connection.ConnectionSQL;
import Model.Book;
import Model.BoolList;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

/**
 *
 * @author Avindu Aloka
 */
public class LoadBooks {
    private Statement state = null;
    private PreparedStatement prepair = null;
    ConnectionSQL cons = new ConnectionSQL();
    public List<BoolList> books = new ArrayList<>();
    
    
    public void listBooks(){
        try{
            cons.ConnectionSuccess();
            state = cons.Con.createStatement();
            ResultSet res = state.executeQuery("SELECT * FROM books");
            
            while(res.next()){
                Timestamp timestamp = res.getTimestamp(4);
                books.add(new BoolList(res.getInt(1), res.getString(2), res.getString(3), res.getString(5), timestamp.toLocalDateTime()));
            }
            
            res.close();
            state.close();
            cons.ConnectionClose();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    /*public static void main(String[] args) {
        LoadBooks bkso = new LoadBooks();
        List<BoolList> booksload = bkso.sendBook();
        for(var booksloads : booksload){
            System.out.println(booksloads.getBookTitle());
        }
    }*/
}
