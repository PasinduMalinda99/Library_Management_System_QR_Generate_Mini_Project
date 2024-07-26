/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDateTime;

/**
 *
 * @author Avindu Aloka
 */
public class BoolList {
    private int bookId;
    private String bookTitle;
    private String bookDescription;
    private String bookAuthor;
    private LocalDateTime bookregDateTime;

    public BoolList(int bookId, String bookTitle, String bookDescription, String bookAuthor, LocalDateTime bookregDateTime) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookDescription = bookDescription;
        this.bookAuthor = bookAuthor;
        this.bookregDateTime = bookregDateTime;
    }

    public LocalDateTime getBookregDateTime() {
        return bookregDateTime;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }
    
    
}
