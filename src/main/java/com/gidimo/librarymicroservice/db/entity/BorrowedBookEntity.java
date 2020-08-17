package com.gidimo.librarymicroservice.db.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "borrowed_book")
public class BorrowedBookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "borrow_date", nullable = false)
    private LocalDateTime borrowDate;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "date_returned")
    private LocalDateTime dateReturned;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private MemberEntity member;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private BookEntity book;

}
