package org.practice.libraryspring.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterResponse {
    private String username;
    private String role;
}
