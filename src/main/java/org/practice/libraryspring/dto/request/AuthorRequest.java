package org.practice.libraryspring.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorRequest {
    private String firstName;
    private String lastName;
}
