package org.practice.libraryspring.repository;

import org.practice.libraryspring.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, String> {
}
