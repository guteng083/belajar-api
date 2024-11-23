package org.practice.libraryspring.service;

import org.practice.libraryspring.dto.request.MemberRequest;
import org.practice.libraryspring.dto.response.MemberResponse;

import java.util.List;

public interface MemberService {
    MemberResponse createMember(MemberRequest memberRequest);
    MemberResponse updateMember(MemberRequest memberRequest);
    List<MemberResponse> getAllMembers();
    void deleteMember(String id);
}
