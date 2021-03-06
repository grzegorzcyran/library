package com.grcy.library;

import com.grcy.library.dao.BookRepository;
import com.grcy.library.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;


import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class LibraryApplicationTests {

    @LocalServerPort
    private int localPort;

    private String BASE_URL;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TestRestTemplate restTemplate;

    @BeforeEach
    void setup() {
        BASE_URL = "http://127.0.0.1:" + localPort + "/library/books/";
    }

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
        Book[] actual = restTemplate.getForObject(BASE_URL, Book[].class);
        assertThat(actual.length).as("Should return list")
                .isEqualTo(bookRepository.findAll().size());
    }

    @Test
    void testPostBookWithRest() {
        Book book = new Book();
        book.setTitle("Test title");
        book.setAuthor("Test author");
        Book actual = restTemplate.postForObject(BASE_URL, book, Book.class);
        Book maxIdBook = bookRepository.findAll().stream()
                .max(Comparator.comparing(Book::getId))
                .get();
        assertThat(actual.getId()).as("Should return list")
                .isEqualTo(maxIdBook.getId());
    }

    @Test
    void testPutBookWithRest() {
        Book updatedBook = findInRepositoryByFirstName("Andrzej");
        String newName = "Jędruś";
        updatedBook.setAuthor(updatedBook.getAuthor().replace("Andrzej", newName));
        restTemplate.put(BASE_URL, updatedBook);

        assertThat(updatedBook).as("Shoud return 'Jędruś' as first name")
                .isEqualTo(findInRepositoryByFirstName(newName));


    }

    private Book findInRepositoryByFirstName(String searchName) {
        return bookRepository.findAll()
                .stream()
                .filter(x -> x.getAuthor().startsWith(searchName))
                .findFirst()
                .get();
    }

}
