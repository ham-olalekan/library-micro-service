package com.gidimo.librarymicroservice.db.repository;

import com.gidimo.librarymicroservice.db.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    @Query("SELECT b FROM BookEntity b WHERE b.title LIKE %:title%")
    List<BookEntity> searchByTitleLike(final String title);
}
