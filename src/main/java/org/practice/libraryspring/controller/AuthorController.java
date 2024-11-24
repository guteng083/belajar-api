package org.practice.libraryspring.controller;

import lombok.RequiredArgsConstructor;
import org.practice.libraryspring.dto.request.AuthorRequest;
import org.practice.libraryspring.dto.response.AuthorResponse;
import org.practice.libraryspring.entity.Author;
import org.practice.libraryspring.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping("/create")
    public ResponseEntity<?> addAuthor(@RequestBody AuthorRequest authorRequest) {
        AuthorResponse authorResponse = authorService.createAuthor(authorRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(authorResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAllAuthors() {
        List<AuthorResponse> authorResponses = authorService.getAllAuthors();
        return new ResponseEntity<>(authorResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);
        AuthorResponse authorResponse = AuthorResponse.AuthorToAuthorResponse(author);
        return new ResponseEntity<>(authorResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable Long id, @RequestBody AuthorRequest authorRequest) {
        AuthorResponse authorResponse = authorService.updateAuthor(id, authorRequest);
        return new ResponseEntity<>(authorResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
