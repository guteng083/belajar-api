package org.practice.libraryspring.service.impl;


import lombok.RequiredArgsConstructor;
import org.practice.libraryspring.dto.request.AuthorRequest;
import org.practice.libraryspring.dto.response.AuthorResponse;
import org.practice.libraryspring.entity.Author;
import org.practice.libraryspring.repository.AuthorRepository;
import org.practice.libraryspring.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public AuthorResponse createAuthor(AuthorRequest authorRequest) {
        Author author = Author.builder()
                .firstName(authorRequest.getFirstName())
                .lastName(authorRequest.getLastName())
                .books(null)
                .build();
        authorRepository.save(author);
        return AuthorResponse.AuthorToAuthorResponse(author);
    }

    @Override
    public AuthorResponse updateAuthor(Long id, AuthorRequest authorRequest) {
        Author author = getAuthorById(id);
        author.setFirstName(authorRequest.getFirstName());
        author.setLastName(authorRequest.getLastName());
        authorRepository.save(author);
        return AuthorResponse.AuthorToAuthorResponse(author);
    }

    @Override
    public Author getAuthorById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if(author.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found");
        }

        return author.get();
    }

    @Override
    public List<AuthorResponse> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        List<AuthorResponse> authorResponses = new ArrayList<>();
        authors.forEach(author -> authorResponses.add(AuthorResponse.AuthorToAuthorResponse(author)));
        return authorResponses;
    }

    @Override
    public void deleteAuthor(String id) {
        authorRepository.deleteById(id);
    }
}
