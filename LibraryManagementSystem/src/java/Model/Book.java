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
public class Book {
    private int bookId;
    private String bookTitle;
    private String bookDescription;
    private String bookAuthor;
    private LocalDateTime bookregDateTime;
    
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public LocalDateTime gebookregDateTimetBookImage() {
        return bookregDateTime;
    }

    public void setbookregDateTime(LocalDateTime bookregDateTime) {
        this.bookregDateTime = bookregDateTime;
    }

    public LocalDateTime getBookRegDate() {
        return bookRegDate;
    }

    public void setBookRegDate(LocalDateTime bookRegDate) {
        this.bookRegDate = bookRegDate;
    }
    private LocalDateTime bookRegDate;
}
