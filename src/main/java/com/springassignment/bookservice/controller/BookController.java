package com.springassignment.bookservice.controller;

import com.springassignment.bookservice.dto.BookDto;
import com.springassignment.bookservice.dto.BookRequestDto;
import com.springassignment.bookservice.model.Book;
import com.springassignment.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    BookService bookService;
    @GetMapping
    public List<BookDto> getAllBooks(){
        return bookService.getAllBooks();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addBook(@RequestBody BookRequestDto bookRequestDto){
        bookService.addBook(bookRequestDto);
    }
    @GetMapping("/{id}")
    public Optional<Book> getBook(@PathVariable("id") Long id){
       return bookService.getBook(id);
    }
    @PostMapping("/getBooksFromOrder")
    public List<BookDto> getBooksFromOrder(@RequestBody List<Long> idList){
        return bookService.getBooksFromOrder(idList);
    }
    @PutMapping
    public void updateBook(@RequestBody BookDto bookDto){
        Book book= Book.builder()
                .id(bookDto.getId())
                .price(bookDto.getPrice())
                .name(bookDto.getName())
                .description(bookDto.getDescription())
                .coverSource(bookDto.getCoverSource())
                .author(bookDto.getAuthor())
                .build();
        bookService.updateBook(book);
    }

}
