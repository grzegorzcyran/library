package com.grcy.library;

import com.grcy.library.dao.BookRepository;
import com.grcy.library.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LibraryApplicationTests {

    @LocalServerPort
    private int localPort;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void testAllRowsAreReadFromH2() {
        final int noRows = 8;
        int elements = bookRepository.findAll().size();

        assertThat(elements).as("Should have %d elements in DB", noRows).isEqualTo(noRows);
    }

    @Test
    void testAllBooksOfAndrzejPilipiuk() {
        final int noRows = 3;
        int elements = bookRepository.findBooksByAuthor("Andrzej Pilipiuk").size();

        assertThat(elements).as("Should have %d elements in DB", noRows).isEqualTo(noRows);
    }

    @Test
    void testGetAllBooksWithRest() {
        Book[] actual = restTemplate.getForObject("http://localhost:" + localPort + "/library/books/", Book[].class);
        assertThat(actual.length).as("Should return list")
                .isEqualTo(bookRepository.findAll().size());
    }

    @Test
    void testPostBookWithRest() {
        Book book = new Book();
        book.setTitle("Test title");
        book.setAuthor("Test author");
        Book actual = restTemplate.postForObject("http://localhost:" + localPort + "/library/books/", book, Book.class);
        assertThat(actual.getId()).as("Should return list")
                .isEqualTo(9);
    }

}
