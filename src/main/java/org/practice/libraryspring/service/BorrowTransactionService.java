package org.practice.libraryspring.service;

import org.practice.libraryspring.dto.request.BorrowRequest;
import org.practice.libraryspring.dto.response.BorrowResponse;

import java.util.List;

public interface BorrowTransactionService {
    BorrowResponse borrowBook(BorrowRequest borrowRequest);
    BorrowResponse returnBook(BorrowRequest borrowRequest);
    List<BorrowResponse> getBorrowTransactions();
}
