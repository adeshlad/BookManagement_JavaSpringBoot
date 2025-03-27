package com.demo.bookmanagement.book;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/books/")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("")
    public ResponseEntity<BookResponse> addBook(@Valid @RequestBody BookAddRequest request) {
        BookResponse response = bookService.addBook(request);
        URI location = URI.create("api/books/" + response.getId());

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("")
    public ResponseEntity<List<BookResponse>> getBooks(@RequestParam(required = false, defaultValue = "") String title, @RequestParam(required = false, defaultValue = "") String author, @RequestParam(required = false, defaultValue = "0") Integer year) {
        List<BookResponse> responses;

        if (!title.isBlank() || !author.isBlank() || year != 0) {
            responses = bookService.getBooksByAttributes(title, author, year);
        } else {
            responses = bookService.getAllBooks();
        }

        return ResponseEntity.ok().body(responses);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {
        BookResponse response = bookService.getBookById(id);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<BookResponse> updateBookById(@PathVariable Long id, @RequestBody BookUpdateRequest request) {
        BookResponse response = bookService.updateBook(id, request);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id) {
        boolean response = bookService.deleteBook(id);

        if (!response) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
