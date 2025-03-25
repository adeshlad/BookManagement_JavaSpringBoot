package com.demo.bookmanagement.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponse addBook(BookAddRequest request) {
        Book book = new Book(request.getTitle(), request.getAuthor(), request.getYear());
        bookRepository.save(book);

        return new BookResponse(book);
    }

    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookResponse::new)
                .toList();
    }

    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id).orElse(null);

        if (book == null) {
            return null;
        }

        return new BookResponse(book);
    }

    public List<BookResponse> getBooksByAttributes(String title, String author, Integer year) {
        List<Book> books = bookRepository.findByAttributes(title, author, year);

        return books.stream()
                .map(BookResponse::new)
                .toList();
    }

    public BookResponse updateBook(Long id, BookUpdateRequest request) {
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

        if (request.getYear() != null && !request.getYear().equals(0)) {
            book.setYear(request.getYear());
        }

        bookRepository.save(book);

        return new BookResponse(book);
    }

    public boolean deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElse(null);

        if (book == null) {
            return false;
        }

        bookRepository.deleteById(id);
        return true;
    }
}
