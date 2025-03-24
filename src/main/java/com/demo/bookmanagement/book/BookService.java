package com.demo.bookmanagement.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponse addBook(BookAddRequest request) {
        Book book = request.toBook();
        bookRepository.save(book);

        return new BookResponse(book);
    }

    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookResponse::new)
                .toList();
    }

    public BookResponse getBookById(UUID id) {
        Book book = bookRepository.findById(id).orElse(null);

        if (book == null) {
            return null;
        }

        return new BookResponse(book);
    }

    public List<BookResponse> getBooksByAttributes(String title, String author, Integer year) {
        List<Book> books = bookRepository.findAll();

        if (title != null && !title.isBlank()) {
            books = books.stream()
                    .filter(book -> book.getTitle().contains(title))
                    .collect(Collectors.toList());
        }

        if (author != null && !author.isBlank()) {
            books = books.stream()
                    .filter(book -> book.getAuthor().contains(author))
                    .collect(Collectors.toList());
        }

        if (year != null) {
            books = books.stream()
                    .filter(book -> book.getYear().equals(year))
                    .collect(Collectors.toList());
        }

        return books.stream()
                .map(BookResponse::new)
                .toList();
    }

    public BookResponse updateBook(UUID id, BookUpdateRequest request) {
        Book book = bookRepository.findById(id).orElse(null);

        if (book == null) {
            return null;
        }

        if (request.getTitle() != null && !request.getTitle().isBlank()) {
            book.setTitle(request.getTitle());
        }

        if (request.getAuthor() != null && !request.getAuthor().isBlank()) {
            book.setAuthor(request.getAuthor());
        }

        if (request.getYear() != null || !request.getYear().equals(0)) {
            book.setYear(request.getYear());
        }

        bookRepository.save(book);

        return new BookResponse(book);
    }

    public boolean deleteBook(UUID id) {
        Book book = bookRepository.findById(id).orElse(null);

        if (book == null) {
            return false;
        }

        bookRepository.deleteById(id);
        return true;
    }
}
