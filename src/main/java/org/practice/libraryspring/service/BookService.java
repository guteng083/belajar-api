package org.practice.libraryspring.service;

import org.practice.libraryspring.dto.request.BookRequest;
import org.practice.libraryspring.dto.response.BookResponse;
import org.practice.libraryspring.entity.Book;

import java.util.List;

public interface BookService {
    BookResponse addBook(BookRequest bookRequest);
    BookResponse updateBook(BookRequest bookRequest);
    Book getBookById(String id);
    List<BookResponse> getAllBooks();
    List<BookResponse> getAllBooksByAuthorId(String authorId);
    void deleteBookById(String id);
}
