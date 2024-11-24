package org.practice.libraryspring.dto.response;

import lombok.*;
import org.practice.libraryspring.entity.Member;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberResponse {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;

    public static MemberResponse memberToMemberResponse(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .address(member.getAddress())
                .build();
    }
}
