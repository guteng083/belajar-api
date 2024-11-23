package org.practice.libraryspring.repository;

import org.practice.libraryspring.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
