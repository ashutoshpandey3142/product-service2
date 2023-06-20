package com.springassignment.bookservice.service;

import com.springassignment.bookservice.dto.BookDto;
import com.springassignment.bookservice.dto.BookRequestDto;
import com.springassignment.bookservice.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookDto> getAllBooks();
    void addBook(BookRequestDto bookRequestDto);
    List<BookDto> getBooksFromOrder(List<Long> booksOrder);

    String deleteBook(Long id);

    Optional<Book> getBook(Long id);
    void updateBook(Book book);
}
