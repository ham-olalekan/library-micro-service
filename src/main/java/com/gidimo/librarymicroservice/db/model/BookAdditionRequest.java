package com.gidimo.librarymicroservice.db.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
public class BookAdditionRequest {
    private String isbn;
    private String title;
    private LocalDateTime datePublished;
    private Integer numberOfCopies;
    List<Long> authorsId;
}
