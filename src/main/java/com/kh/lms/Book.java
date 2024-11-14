package com.kh.lms;

public class Book
{
    private Integer bookId;
    private String bookName;
    private String author;
    private Double price;
    private boolean isAvailable;

    public Book(Integer bookId, String bookName, String author, Double price, boolean isAvailable) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean borrowed) {
        isAvailable = borrowed;
    }
}
