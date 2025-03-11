package com.demo.bookmanagement.api.controllers;

import com.demo.bookmanagement.core.application.dtos.BookAddRequest;
import com.demo.bookmanagement.core.application.dtos.BookResponse;
import com.demo.bookmanagement.core.application.dtos.BookUpdateRequest;
import com.demo.bookmanagement.core.application.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/")
    public ResponseEntity<BookResponse> addBook(@Valid @RequestBody BookAddRequest request) {
        BookResponse response = bookService.addBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/")
    public ResponseEntity<List<BookResponse>> getBooks(@RequestParam(required = false) String title, @RequestParam(required = false) String author, @RequestParam(required = false) Integer year) {
        List<BookResponse> responses;

        if ((title != null && !title.isBlank()) || (author != null && !author.isBlank()) || year != null) {
            responses = bookService.getBooksByAttributes(title, author, year);
        } else {
            responses = bookService.getAllBooks();
        }

        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable UUID id) {
        BookResponse response = bookService.getBookById(id);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBookById(@PathVariable UUID id, @RequestBody BookUpdateRequest request) {
        BookResponse response = bookService.updateBook(id, request);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable UUID id) {
        boolean response = bookService.deleteBook(id);

        if (!response) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
