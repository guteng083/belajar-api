package org.practice.libraryspring.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.practice.libraryspring.dto.request.BookRequest;
import org.practice.libraryspring.dto.response.BookResponse;
import org.practice.libraryspring.entity.Author;
import org.practice.libraryspring.entity.Book;
import org.practice.libraryspring.repository.AuthorRepository;
import org.practice.libraryspring.repository.BookRepository;
import org.practice.libraryspring.service.AuthorService;
import org.practice.libraryspring.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    @Override
    public BookResponse addBook(BookRequest bookRequest) {
        Book book = Book.builder()
                .title(bookRequest.getTitle())
                .author(authorService.getAuthorById(bookRequest.getAuthorId()))
                .publisher(bookRequest.getPublisher())
                .publishedDate(bookRequest.getPublishedDate())
                .totalCopies(bookRequest.getTotalCopies())
                .build();
        bookRepository.save(book);
        return BookResponse.BooktoBookResponse(book);
    }

    @Override
    public BookResponse updateBook(String id,BookRequest bookRequest) {
        Book book = getBookById(id);
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(authorService.getAuthorById(bookRequest.getAuthorId()));
        book.setPublisher(bookRequest.getPublisher());
        book.setPublishedDate(bookRequest.getPublishedDate());
        book.setTotalCopies(bookRequest.getTotalCopies());
        bookRepository.save(book);
        return BookResponse.BooktoBookResponse(book);
    }

    @Override
    public Book getBookById(String id) {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Book with id %s not found", id));
        }
        return book.get();
    }

    @Override
    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookResponse> bookResponses = new ArrayList<>();
        books.forEach(book -> bookResponses.add(BookResponse.BooktoBookResponse(book)));
        return bookResponses;
    }

    @Override
    public List<BookResponse> getAllBooksByAuthorId(String authorId) {
        Author author = authorService.getAuthorById(authorId);
        List<Book> books = author.getBooks();
        List<BookResponse> bookResponses = new ArrayList<>();
        books.forEach(book -> bookResponses.add(BookResponse.BooktoBookResponse(book)));
        return bookResponses;
    }

    @Override
    public void deleteBookById(String id) {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }
}
