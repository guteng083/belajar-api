package org.practice.libraryspring.service;

import org.practice.libraryspring.dto.request.BorrowRequest;
import org.practice.libraryspring.dto.request.ReturnRequest;
import org.practice.libraryspring.dto.response.BorrowResponse;
import org.practice.libraryspring.dto.response.ReturnResponse;
import org.practice.libraryspring.entity.BorrowTransaction;

import java.util.List;

public interface BorrowTransactionService {
    BorrowResponse borrowBook(BorrowRequest borrowRequest);
    BorrowTransaction getBorrowTransactionById(String id);
    ReturnResponse returnBook(ReturnRequest returnRequest);
    List<BorrowResponse> getBorrowTransactions();
}
