package com.demo.bookmanagement.core.application.dtos;
import com.demo.bookmanagement.core.domain.entities.Book;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
public class BookAddRequest {
    private String title;
    private String author;
    private Integer year;

    public Book toBook(){
        return Book.builder()
                .id(UUID.randomUUID())
                .title(this.title)
                .author(this.author)
                .year(this.year)
                .build();
    }
}
