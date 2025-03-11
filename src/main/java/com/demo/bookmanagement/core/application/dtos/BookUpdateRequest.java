package com.demo.bookmanagement.core.application.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookUpdateRequest {

    private String title;
    private String author;
    private Integer year;

}
