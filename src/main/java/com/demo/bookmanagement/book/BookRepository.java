package com.demo.bookmanagement.book;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.azure.spring.data.cosmos.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CosmosRepository<Book, String> {

//    JPQL Query
    @Query("SELECT * FROM c " +
            "WHERE (CONTAINS(LOWER(c.title), LOWER(@title)))" +
            "AND (CONTAINS(LOWER(c.author), LOWER(@author)))" +
            "AND (c.year = @year)")
    List<Book> findByAttributes(String title, String author, Integer year);

}