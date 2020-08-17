package com.gidimo.librarymicroservice.services;

import com.gidimo.librarymicroservice.db.entity.BookEntity;
import com.gidimo.librarymicroservice.db.model.BookAdditionRequest;

import java.util.List;
import java.util.Optional;


public interface BooksService {
    BookEntity addNewBook(BookAdditionRequest request);

    void deleteBook(final Long bookId);

    List<BookEntity> searchForBookByTitle(final String title);

    Optional<BookEntity> findBookById(final Long bookId);

    BookEntity saveBook(BookEntity bookEntity);

    BookEntity updateBook(long bookId, BookAdditionRequest request);
}
