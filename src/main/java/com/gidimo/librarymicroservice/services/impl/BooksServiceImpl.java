package com.gidimo.librarymicroservice.services.impl;

import com.gidimo.librarymicroservice.Exception.NotFoundException;
import com.gidimo.librarymicroservice.db.entity.AuthorEntity;
import com.gidimo.librarymicroservice.db.entity.BookEntity;
import com.gidimo.librarymicroservice.db.model.BookAdditionRequest;
import com.gidimo.librarymicroservice.db.repository.BookRepository;
import com.gidimo.librarymicroservice.services.AuthorsService;
import com.gidimo.librarymicroservice.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class BooksServiceImpl implements BooksService {

    BookRepository bookRepository;
    AuthorsService authorsService;

    @Autowired
    public BooksServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookEntity addNewBook(BookAdditionRequest request) {
        Set<AuthorEntity> authors = getAuthors(request.getAuthorsId());
        BookEntity newBook = BookEntity.builder()
                .datePublished(request.getDatePublished())
                .isbn(request.getIsbn())
                .numberOfCopies(request.getNumberOfCopies())
                .title(request.getTitle())
                .authors(authors)
                .build();
        return bookRepository.save(newBook);
    }

    public List<BookEntity> searchForBookByTitle(final String title) {
        return bookRepository.searchByTitleLike(title);
    }

    public void deleteBook(final Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public Optional<BookEntity> findBookById(Long bookId) {
        return bookRepository.findById(bookId);
    }

    private Set<AuthorEntity> getAuthors(List<Long> authorsId) {
        Set<AuthorEntity> authors = new HashSet<>();
        authorsId.forEach(id -> {
            authorsService.getAuthorById(id).ifPresent(authorEntity -> authors.add(authorEntity));
        });
        return authors;
    }

    @Override
    public BookEntity saveBook(BookEntity bookEntity) {
        return bookRepository.save(bookEntity);
    }

    @Override
    public BookEntity updateBook(long bookId,
                                 BookAdditionRequest request) {
        Optional<BookEntity> optionalBookEntity = findBookById(bookId);
        if (!optionalBookEntity.isPresent()) {
            throw new NotFoundException("Book with ID [" + bookId + "] was not found");
        }

        BookEntity book = optionalBookEntity.get();

        if (Objects.nonNull(request.getTitle()))
            book.setTitle(request.getTitle());

        if (Objects.nonNull(request.getIsbn()))
            book.setIsbn(request.getIsbn());

        if (Objects.nonNull(request.getDatePublished()))
            book.setDatePublished(request.getDatePublished());

        return saveBook(book);
    }
}
