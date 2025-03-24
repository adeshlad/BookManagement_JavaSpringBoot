package com.demo.bookmanagement.book;

import jakarta.validation.constraints.*;
import java.util.UUID;

public class BookAddRequest {

    @NotBlank(message = "Title is required.")
    private String title;

    @NotBlank(message = "Author is required.")
    private String author;

    @NotNull(message = "Year is required.")
    private Integer year;


    public BookAddRequest() {
    }

    public BookAddRequest(String title, String author, Integer year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }


    public Book toBook(){
        return new Book(UUID.randomUUID(), this.title, this.author, this.year);
    }

}
