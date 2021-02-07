package com.grcy.library;

import com.grcy.library.dao.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LibraryApplicationTests {

    @Autowired
    BookRepository bookRepository;

    @Test
    void testAllRowsAreReadFromH2() {
        final int noRows = 8;
        int elements = bookRepository.findAll().size();

        assertThat(elements).as("Shoud have 8 elements in DB").isEqualTo(noRows);
    }

}
