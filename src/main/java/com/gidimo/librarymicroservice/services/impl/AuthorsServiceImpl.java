package com.gidimo.librarymicroservice.services.impl;

import com.gidimo.librarymicroservice.db.entity.AuthorEntity;
import com.gidimo.librarymicroservice.db.model.AuthorCreationRequest;
import com.gidimo.librarymicroservice.db.repository.AuthorRepository;
import com.gidimo.librarymicroservice.services.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorsServiceImpl implements AuthorsService {

    AuthorRepository authorRepository;

    @Autowired
    public AuthorsServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity createAuthor(AuthorCreationRequest authorCreationRequest) {
        AuthorEntity entity = AuthorEntity
                .builder()
                .firstName(authorCreationRequest.getFirstName())
                .lastName(authorCreationRequest.getLastName())
                .build();
        return authorRepository.save(entity);
    }

    @Override
    public Optional<AuthorEntity> getAuthorById(Long authorId) {
        return authorRepository.findById(authorId);
    }

    @Override
    public List<AuthorEntity> getAllAuthors() {
        return authorRepository.findAll();
    }
}
