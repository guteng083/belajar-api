package org.practice.libraryspring.repository;

import org.practice.libraryspring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
