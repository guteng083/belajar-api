package org.practice.libraryspring.repository;

import org.practice.libraryspring.entity.BorrowTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowTransactionRepository extends JpaRepository<BorrowTransaction, Long> {
}
