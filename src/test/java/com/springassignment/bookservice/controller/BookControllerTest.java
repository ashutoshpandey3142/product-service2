package com.springassignment.bookservice.controller;

import com.springassignment.bookservice.dto.BookDto;
import com.springassignment.bookservice.dto.BookRequestDto;
import com.springassignment.bookservice.model.Book;
import com.springassignment.bookservice.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

 class BookControllerTest {
    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBooks() {
        // Mock data
        List<BookDto> expectedBooks = Arrays.asList(new BookDto(), new BookDto());
        when(bookService.getAllBooks()).thenReturn(expectedBooks);

        // Perform the GET request
        List<BookDto> actualBooks = bookController.getAllBooks();

        // Verify the result
        assertEquals(expectedBooks, actualBooks);
        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    void testAddBook() {
        // Mock data
        BookRequestDto bookRequestDto = new BookRequestDto();

        // Perform the POST request
        bookController.addBook(bookRequestDto);

        // Verify that the bookService.addBook method is called once
        verify(bookService, times(1)).addBook(bookRequestDto);
    }

    @Test
    void testGetBook() {
        // Mock data
        Long bookId = 1L;
        Book expectedBook = new Book();
        when(bookService.getBook(bookId)).thenReturn(Optional.of(expectedBook));

        // Perform the GET request
        Optional<Book> actualBook = bookController.getBook(bookId);

        // Verify the result
        assertEquals(Optional.of(expectedBook), actualBook);
        verify(bookService, times(1)).getBook(bookId);
    }

    @Test
    void testGetBooksFromOrder() {
        // Mock data
        List<Long> idList = Arrays.asList(1L, 2L);
        List<BookDto> expectedBooks = Arrays.asList(new BookDto(), new BookDto());
        when(bookService.getBooksFromOrder(idList)).thenReturn(expectedBooks);

        // Perform the POST request
        List<BookDto> actualBooks = bookController.getBooksFromOrder(idList);

        // Verify the result
        assertEquals(expectedBooks, actualBooks);
        verify(bookService, times(1)).getBooksFromOrder(idList);
    }

    @Test
    void testUpdateBook() {
        // Mock data
        BookDto bookDto = new BookDto();

        // Perform the PUT request
        bookController.updateBook(bookDto);

        // Verify that the bookService.updateBook method is called once
        verify(bookService, times(1)).updateBook(any());
    }
}