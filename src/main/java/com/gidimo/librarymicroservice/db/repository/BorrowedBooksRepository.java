package com.gidimo.librarymicroservice.db.repository;

import com.gidimo.librarymicroservice.db.entity.BorrowedBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowedBooksRepository extends JpaRepository<BorrowedBookEntity, Long> {
}
