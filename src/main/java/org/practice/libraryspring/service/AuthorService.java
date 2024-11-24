package org.practice.libraryspring.service;

import org.practice.libraryspring.dto.request.AuthorRequest;
import org.practice.libraryspring.dto.response.AuthorResponse;
import org.practice.libraryspring.entity.Author;

import java.util.List;

public interface AuthorService {
    AuthorResponse createAuthor(AuthorRequest authorRequest);
    AuthorResponse updateAuthor(Long id, AuthorRequest authorRequest);
    Author getAuthorById(Long id);
    List<AuthorResponse> getAllAuthors();
    void deleteAuthor(Long id);
}
