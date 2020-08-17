package com.gidimo.librarymicroservice.controllers;

import com.gidimo.librarymicroservice.db.entity.BorrowedBookEntity;
import com.gidimo.librarymicroservice.services.BookLendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/lend")
public class BookLendingController {

    BookLendingService bookLendingService;

    @Autowired
    public BookLendingController(BookLendingService bookLendingService) {
        this.bookLendingService = bookLendingService;
    }

    @PostMapping("/member/{memberId}/book/{bookId}")
    public ResponseEntity<?> handleBookLendingToMembers(@PathVariable("memberId") final long memberId,
                                                        @PathVariable("bookId") final long bookId) {
        BorrowedBookEntity borrowedBook = bookLendingService.lendBookToLibraryMember(bookId, memberId);
        return ResponseEntity.status(HttpStatus.OK).body(borrowedBook);
    }
}
