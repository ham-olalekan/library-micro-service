package com.gidimo.librarymicroservice.services;

import com.gidimo.librarymicroservice.db.entity.AuthorEntity;
import com.gidimo.librarymicroservice.db.model.AuthorCreationRequest;

import java.util.List;
import java.util.Optional;


public interface AuthorsService {
    AuthorEntity createAuthor(AuthorCreationRequest authorCreationRequest);

    Optional<AuthorEntity> getAuthorById(Long authorId);

    List<AuthorEntity> getAllAuthors();
}
