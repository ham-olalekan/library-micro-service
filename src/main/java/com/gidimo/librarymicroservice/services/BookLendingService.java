package com.gidimo.librarymicroservice.services;

import com.gidimo.librarymicroservice.db.entity.BorrowedBookEntity;

public interface BookLendingService {
    BorrowedBookEntity lendBookToLibraryMember(final Long bookId, final Long memberId);
}
