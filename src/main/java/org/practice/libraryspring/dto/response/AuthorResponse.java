package org.practice.libraryspring.dto.response;

import lombok.*;
import org.practice.libraryspring.entity.Author;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorResponse {
    private Long id;
    private String name;

    public static AuthorResponse AuthorToAuthorResponse(Author author) {
        return AuthorResponse.builder()
                .id(author.getId())
                .name(author.getFirstName() + " " + author.getLastName())
                .build();
    }
}
