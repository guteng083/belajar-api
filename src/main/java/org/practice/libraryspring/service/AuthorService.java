package org.practice.libraryspring.service;

import org.practice.libraryspring.dto.request.AuthorRequest;
import org.practice.libraryspring.dto.response.AuthorResponse;
import org.practice.libraryspring.dto.response.BookResponse;
import org.practice.libraryspring.entity.Author;

import java.util.List;

public interface AuthorService {
    AuthorResponse createAuthor(AuthorRequest authorRequest);
    AuthorResponse updateAuthor(String id, AuthorRequest authorRequest);
    Author getAuthorById(String id);
    List<AuthorResponse> getAllAuthors();
    void deleteAuthor(String id);
}
