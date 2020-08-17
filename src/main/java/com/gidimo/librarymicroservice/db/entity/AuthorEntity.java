package com.gidimo.librarymicroservice.db.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@Entity
@Table(name = "author")
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToMany(targetEntity = BookEntity.class, mappedBy = "authors", fetch = FetchType.EAGER)
    private Set<BookEntity> authoredBooks;

    @PrePersist
    private void setDefaultValues() {
        if (this.authoredBooks == null) {
            this.authoredBooks = new HashSet<>();
        }
    }

}
