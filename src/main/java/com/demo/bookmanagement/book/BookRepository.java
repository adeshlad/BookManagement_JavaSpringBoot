package com.demo.bookmanagement.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // JPQL Query
    @Query("SELECT b FROM Book b WHERE " +
            "(:title = '' OR LOWER(b.title) LIKE %:title%) AND " +
            "(:author = '' OR LOWER(b.author) LIKE %:author%) AND " +
            "(:year = 0 OR b.year = :year)")
    List<Book> findByAttributes(@Param("title") String title, @Param("author") String author, @Param("year") Integer year);

}