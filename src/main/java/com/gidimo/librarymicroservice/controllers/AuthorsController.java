package com.gidimo.librarymicroservice.controllers;

import com.gidimo.librarymicroservice.db.entity.AuthorEntity;
import com.gidimo.librarymicroservice.db.model.AuthorCreationRequest;
import com.gidimo.librarymicroservice.services.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class AuthorsController {

    private AuthorsService authorsService;

    @Autowired
    public AuthorsController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @PostMapping("/authors")
    public ResponseEntity<?> handleAddingBooksToLibrary(@RequestBody AuthorCreationRequest request) {
        AuthorEntity entity = authorsService.createAuthor(request);
        return ResponseEntity.status(HttpStatus.OK).body(entity);
    }

    @PutMapping("/authors/{authorId}")
    public ResponseEntity<?> handleGettingAuthorById(@PathVariable("authorId") final Long authorId) {
        Optional<AuthorEntity> entity = authorsService.getAuthorById(authorId);
        if (!entity.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Author found with ID [ " + authorId + " ]");

        return ResponseEntity.status(HttpStatus.OK).body(entity.get());
    }

    @GetMapping("/authors/all")
    public ResponseEntity<?> handleFetchingFetchingBooksInLibrary() {
        return ResponseEntity.status(HttpStatus.OK).body(authorsService.getAllAuthors());
    }
}
