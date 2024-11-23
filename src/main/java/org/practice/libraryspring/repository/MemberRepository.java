package org.practice.libraryspring.repository;

import org.practice.libraryspring.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
