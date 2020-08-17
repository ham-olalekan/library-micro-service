package com.gidimo.librarymicroservice.db.repository;

import com.gidimo.librarymicroservice.db.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
}
