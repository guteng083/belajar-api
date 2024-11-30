package org.practice.libraryspring.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String username;
    private String password;
}
