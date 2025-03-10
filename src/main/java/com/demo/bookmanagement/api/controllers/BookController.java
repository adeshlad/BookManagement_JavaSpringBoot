package com.demo.bookmanagement.api.controllers;

import com.demo.bookmanagement.core.application.dtos.BookAddRequest;
import com.demo.bookmanagement.core.application.dtos.BookResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/books")
public class BookController {
    @PostMapping
    public ResponseEntity<BookResponse> AddBook(@RequestBody BookAddRequest request){
        return null;
    }
}
