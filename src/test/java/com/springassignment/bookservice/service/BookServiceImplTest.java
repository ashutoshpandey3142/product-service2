package com.springassignment.bookservice.service;

import com.springassignment.bookservice.dto.BookDto;
import com.springassignment.bookservice.dto.BookRequestDto;
import com.springassignment.bookservice.model.Book;
import com.springassignment.bookservice.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBooks() {
        // Mock data
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book());
        when(bookRepository.findAll()).thenReturn(bookList);

        // Perform the method under test
        List<BookDto> result = bookService.getAllBooks();

        // Verify the result
        assertEquals(bookList.size(), result.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testAddBook() {
        // Mock data
        BookRequestDto bookRequestDto = new BookRequestDto();

        // Perform the method under test
        bookService.addBook(bookRequestDto);

        // Verify that the bookRepository.save method is called once
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void testGetBooksFromOrder() {
        List<Long> booksOrder = new ArrayList<>();
        booksOrder.add(1L);
        List<BookDto> bookDtoList = new ArrayList<>();
        bookDtoList.add(new BookDto());

        Book book = Book.builder()
                .id(1L)
                .name("Sample Book")
                .author("Sample Author")
                .price(BigDecimal.valueOf(19.99))
                .description("Sample Description")
                .coverSource("Sample Cover Source")
                .build();

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        // Perform the method under test
        List<BookDto> result = bookService.getBooksFromOrder(booksOrder);

        // Verify the result
        assertEquals(bookDtoList.size(), result.size());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteBook() {
        // Mock data
        Long id = 1L;

        // Perform the method under test
        String result = bookService.deleteBook(id);

        // Verify the result
        assertEquals("Deleted", result);
        verify(bookRepository, times(1)).deleteById(id);
    }

    @Test
    void testGetBook() {
        // Mock data
        Long id = 1L;
        Optional<Book> expectedBook = Optional.of(new Book());
        when(bookRepository.findById(id)).thenReturn(expectedBook);

        // Perform the method under test
        Optional<Book> result = bookService.getBook(id);

        // Verify the result
        assertEquals(expectedBook, result);
        verify(bookRepository, times(1)).findById(id);
    }

    @Test
    void testUpdateBook() {
        // Mock data
        Book book = new Book();

        // Perform the method under test
        bookService.updateBook(book);

        // Verify that the bookRepository.save method is called once
        verify(bookRepository, times(1)).save(book);
    }
}
