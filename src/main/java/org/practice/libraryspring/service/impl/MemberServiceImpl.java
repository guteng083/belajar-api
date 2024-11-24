package org.practice.libraryspring.service.impl;

import lombok.RequiredArgsConstructor;
import org.practice.libraryspring.dto.request.MemberRequest;
import org.practice.libraryspring.dto.response.MemberResponse;
import org.practice.libraryspring.entity.Member;
import org.practice.libraryspring.repository.MemberRepository;
import org.practice.libraryspring.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public MemberResponse createMember(MemberRequest memberRequest) {
        Member member = Member.builder()
                .name(memberRequest.getName())
                .email(memberRequest.getEmail())
                .phoneNumber(memberRequest.getPhoneNumber())
                .address(memberRequest.getAddress())
                .build();
        memberRepository.save(member);
        return MemberResponse.memberToMemberResponse(member);
    }

    @Override
    public Member getMemberById(String id) {
        Optional<Member> member = memberRepository.findById(id);
        if(member.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found");
        }

        return member.get();
    }

    @Override
    public MemberResponse updateMember(String id, MemberRequest memberRequest) {
        Member member = getMemberById(id);
        member.setName(memberRequest.getName());
        member.setEmail(memberRequest.getEmail());
        member.setPhoneNumber(memberRequest.getPhoneNumber());
        member.setAddress(memberRequest.getAddress());
        memberRepository.save(member);
        return MemberResponse.memberToMemberResponse(member);
    }

    @Override
    public List<MemberResponse> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        List<MemberResponse> memberResponses = new ArrayList<>();
        members.forEach(member -> memberResponses.add(MemberResponse.memberToMemberResponse(member)));
        return memberResponses;
    }

    @Override
    public void deleteMember(String id) {
        Member member = getMemberById(id);
        memberRepository.delete(member);
    }
}
