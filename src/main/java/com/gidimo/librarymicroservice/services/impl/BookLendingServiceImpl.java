package com.gidimo.librarymicroservice.services.impl;

import com.gidimo.librarymicroservice.Exception.BusinessLogicException;
import com.gidimo.librarymicroservice.Exception.NotFoundException;
import com.gidimo.librarymicroservice.db.entity.BookEntity;
import com.gidimo.librarymicroservice.db.entity.BorrowedBookEntity;
import com.gidimo.librarymicroservice.db.entity.MemberEntity;
import com.gidimo.librarymicroservice.db.repository.BorrowedBooksRepository;
import com.gidimo.librarymicroservice.services.BookLendingService;
import com.gidimo.librarymicroservice.services.BooksService;
import com.gidimo.librarymicroservice.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookLendingServiceImpl implements BookLendingService {

    BorrowedBooksRepository borrowedBooksRepository;
    BooksService booksService;
    MemberService memberService;

    private final int DURATION_IN_DAYS = 14;

    @Autowired
    public BookLendingServiceImpl(BorrowedBooksRepository borrowedBooksRepository,
                                  MemberService memberService) {
        this.borrowedBooksRepository = borrowedBooksRepository;
        this.memberService = memberService;
    }

    @Override
    public BorrowedBookEntity lendBookToLibraryMember(Long bookId, Long memberId) {
        BookEntity book = getBookToBeBorrowed(bookId);

        if (!copyAvailable(book))
            throw new BusinessLogicException(String.format("No copy of book with title %s is currently available. kindly check back...", book.getTitle()));

        MemberEntity member = getMemberRecord(memberId);
        BorrowedBookEntity borrowedBookLog = BorrowedBookEntity
                .builder()
                .book(book)
                .member(member)
                .borrowDate(LocalDateTime.now())
                .dueDate(LocalDateTime.now().plusDays(DURATION_IN_DAYS))
                .build();

        book.setNumberOfCopies(book.getNumberOfCopies() - 1);
        booksService.saveBook(book);
        return borrowedBooksRepository.save(borrowedBookLog);
    }

    private BookEntity getBookToBeBorrowed(Long bookId) throws NotFoundException {
        return booksService.findBookById(bookId).orElseThrow(() -> new NotFoundException("Book with ID [ " + bookId + " ] was not found"));
    }

    private MemberEntity getMemberRecord(Long memberId) throws NotFoundException {
        return memberService.getMemberById(memberId).orElseThrow(() -> new NotFoundException("Member with ID [ " + memberId + " ] was not found"));
    }

    private boolean copyAvailable(BookEntity bookEntity) {
        return bookEntity.getNumberOfCopies() == 1;
    }
}
