package com.gidimo.librarymicroservice.db.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AuthorCreationRequest {
    private String firstName;
    private String lastName;
}
