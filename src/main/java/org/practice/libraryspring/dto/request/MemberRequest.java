package org.practice.libraryspring.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRequest {
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
}
