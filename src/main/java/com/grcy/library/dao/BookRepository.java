package com.grcy.library.dao;

import com.grcy.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
public interface BookRepository extends JpaRepository<Book,String> {

    Book findById(Long id);

    @Query(value = "SELECT nextval('hibernate_sequence')", nativeQuery = true)
    Long getNextSeriesId();

    List<Book> findBooksByAuthor(@RequestParam String name);

}
