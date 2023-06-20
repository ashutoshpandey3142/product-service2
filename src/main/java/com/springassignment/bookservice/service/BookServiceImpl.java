package com.springassignment.bookservice.service;

import com.springassignment.bookservice.dto.BookDto;
import com.springassignment.bookservice.dto.BookRequestDto;
import com.springassignment.bookservice.model.Book;
import com.springassignment.bookservice.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;
    @Override
    public List<BookDto> getAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        log.info(bookList.get(0).getName());
    return bookList.stream().map(this::mapToBookDto).collect(Collectors.toList());
    }

    public Book getBookById(Long id){
       Optional <Book> book= bookRepository.findById(id);
       return book.orElse(null);
    }

    @Override
    public void addBook(BookRequestDto bookRequestDto) {
    Book book =
        Book.builder()
            .name(bookRequestDto.getName())
            .author(bookRequestDto.getAuthor())
            .coverSource(bookRequestDto.getCoverSource())
            .description(bookRequestDto.getDescription())
                .price(bookRequestDto.getPrice())
            .build();
        bookRepository.save(book);
    }

    @Override
    public List<BookDto> getBooksFromOrder(List<Long> booksOrder) {
        return booksOrder.stream()
                .map(id->mapToBookDto(getBookById(id)))
                .collect(Collectors.toList());

    }

    @Override
    public String deleteBook(Long id) {
        bookRepository.deleteById(id);
        return "Deleted";
    }

    @Override
    public Optional<Book> getBook(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookRepository.save(book);

    }

    private BookDto  mapToBookDto(Book book) {
        log.info(book.toString());
        return BookDto.builder().id(book.getId())
                .name(book.getName())
                .price(book.getPrice())
                .author(book.getAuthor())
                .description(book.getDescription())
                .coverSource(book.getCoverSource())
                .build();
    }
}
