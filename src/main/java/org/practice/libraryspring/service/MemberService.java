package org.practice.libraryspring.service;

import org.practice.libraryspring.dto.request.MemberRequest;
import org.practice.libraryspring.dto.response.MemberResponse;
import org.practice.libraryspring.entity.Member;

import java.util.List;

public interface MemberService {
    MemberResponse createMember(MemberRequest memberRequest);
    Member getMemberById(Long id);
    MemberResponse updateMember(Long id, MemberRequest memberRequest);
    List<MemberResponse> getAllMembers();
    void deleteMember(Long id);
}
