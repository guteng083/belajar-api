package org.practice.libraryspring.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.practice.libraryspring.dto.request.AuthorRequest;
import org.practice.libraryspring.dto.response.AuthorResponse;
import org.practice.libraryspring.entity.Author;
import org.practice.libraryspring.repository.AuthorRepository;
import org.practice.libraryspring.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
                .build();
        authorRepository.save(author);
        return toAuthorResponse(author);
    }

    @Override
    public AuthorResponse updateAuthor(String id, AuthorRequest authorRequest) {
        Author author = getAuthorById(id);
        return toAuthorResponse(authorRepository.save(author));
    }

    @Override
    public Author getAuthorById(String id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public List<AuthorResponse> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        List<AuthorResponse> authorResponses = new ArrayList<>();
        authors.forEach(author -> authorResponses.add(toAuthorResponse(author)));
        return authorResponses;
    }

    @Override
    public void deleteAuthor(String id) {
        authorRepository.deleteById(id);
    }

    public AuthorResponse toAuthorResponse(Author author)  {
        AuthorResponse authorResponse = new AuthorResponse();
        authorResponse.setId(author.getId());
        authorResponse.setName(author.getFirstName() + " " + author.getLastName());
        return authorResponse;
    }
}
