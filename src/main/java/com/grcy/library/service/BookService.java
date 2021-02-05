package com.grcy.library.service;

import com.grcy.library.dao.BookRepository;
import com.grcy.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getBookById(Long id){
        return bookRepository.findById(id);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book addBook(Book book) {

        book.setId(bookRepository.getNextSeriesId());
        bookRepository.save(book);
        return book;
    }

    public Book updateAuthor(Long id, String author) {
        Book book = bookRepository.findById(id);
        book.setAuthor(author);
        bookRepository.save(book);
        return book;
    }

    public void deleteBook(Long id) {
        Optional<Book> book = Optional.ofNullable(bookRepository.findById(id));
        if(book.isPresent()) {
            bookRepository.delete(book.get());
        } else {
            throw new EntityNotFoundException();
        }
    }

    public List<Book> findBookByAuthor(String name) {
        return bookRepository.findBooksByAuthor(name);
    }
}
