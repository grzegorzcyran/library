package com.grcy.library.controller.api.rest;

import com.grcy.library.model.Book;
import com.grcy.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/library/")
public class BookRestController {
    private final BookService bookService;

    @Autowired
    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "books/id/{id}")
    public Book getBookById(@PathVariable String id){
        return bookService.getBookById(Long.parseLong(id));
    }

    @RequestMapping(value = "books/")
    public List<Book> getAllBooks(){
        return bookService.getBooks();
    }

    @PostMapping(value = "books/")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @RequestMapping(value = "books/updateAuthor")
    public Book updateAuthor(@RequestParam(value = "id") String id, @RequestParam(value = "author") String author) {
        return bookService.updateAuthor(Long.valueOf(id), author);
    }

    @DeleteMapping("books/{id}")
    public void deleteBook(@PathVariable String id) {
        bookService.deleteBook(Long.parseLong(id));
    }

    @GetMapping(value = "books/author/{name}")
    public List<Book> getAllBooksByAuthor(@PathVariable(value = "name") String name) {
        return bookService.findBookByAuthor(name);
    }
}
