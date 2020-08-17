package com.gidimo.librarymicroservice.controllers;

import com.gidimo.librarymicroservice.db.entity.BookEntity;
import com.gidimo.librarymicroservice.db.model.BookAdditionRequest;
import com.gidimo.librarymicroservice.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BooksController {

    private BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @PostMapping("/books")
    public ResponseEntity<?> handleAddingBooksToLibrary(@RequestBody BookAdditionRequest request) {
        BookEntity bookEntity = booksService.addNewBook(request);
        return ResponseEntity.status(HttpStatus.OK).body(bookEntity);
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<?> handleDeletingBookFromLibrary(@PathVariable("bookId") final Long bookId) {
        booksService.deleteBook(bookId);
        return ResponseEntity.status(HttpStatus.OK).body("Book with ID [ " + bookId + " ] successfully deleted");
    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<?> handleUpdatingBookInLibrary(@PathVariable("bookId") final Long bookId,
                                                         @RequestBody BookAdditionRequest request) {
        BookEntity bookEntity = booksService.updateBook(bookId, request);
        return ResponseEntity.status(HttpStatus.OK).body(bookEntity);

    }

    @GetMapping("/books")
    public ResponseEntity<?> handleSearchingBookByTitle(@RequestParam("title") final String title) {
        List<BookEntity> booksWithMatchingTitle = booksService.searchForBookByTitle(title);
        return ResponseEntity.status(HttpStatus.OK).body(booksWithMatchingTitle);
    }

}
