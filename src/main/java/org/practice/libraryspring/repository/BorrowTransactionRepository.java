package org.practice.libraryspring.repository;

import org.practice.libraryspring.entity.BorrowTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowTransactionRepository extends JpaRepository<BorrowTransaction, String> {
}
