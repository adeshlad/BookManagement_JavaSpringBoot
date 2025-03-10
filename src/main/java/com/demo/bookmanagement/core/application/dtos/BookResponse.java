package com.demo.bookmanagement.core.application.dtos;
import com.demo.bookmanagement.core.domain.entities.Book;
import lombok.*;

@Getter
@Setter
public class BookResponse {
    private String title;
    private String author;
    private Integer year;

    public BookResponse(Book book){
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.year = book.getYear();
    }
}
