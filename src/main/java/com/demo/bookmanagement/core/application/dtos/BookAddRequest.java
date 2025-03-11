package com.demo.bookmanagement.core.application.dtos;

import com.demo.bookmanagement.core.domain.entities.Book;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookAddRequest {

    @NotBlank(message = "Title is required.")
    private String title;

    @NotBlank(message = "Author is required.")
    private String author;

    @NotNull(message = "Year is required.")
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
