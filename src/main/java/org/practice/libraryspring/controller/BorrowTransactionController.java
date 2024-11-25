package org.practice.libraryspring.controller;

import lombok.RequiredArgsConstructor;
import org.practice.libraryspring.dto.request.BorrowRequest;
import org.practice.libraryspring.dto.request.ReturnRequest;
import org.practice.libraryspring.dto.response.BorrowResponse;
import org.practice.libraryspring.dto.response.ReturnResponse;
import org.practice.libraryspring.service.BorrowTransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/transaction")
@RequiredArgsConstructor
public class BorrowTransactionController {
    private final BorrowTransactionService borrowTransactionService;

    @PostMapping("/borrow")
    public ResponseEntity<?> borrowBook(@RequestBody BorrowRequest borrowRequest) {
        BorrowResponse borrowResponse = borrowTransactionService.borrowBook(borrowRequest);
        return ResponseEntity.ok(borrowResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        List<BorrowResponse> borrowResponses = borrowTransactionService.getBorrowTransactions();
        return ResponseEntity.ok(borrowResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        BorrowResponse borrowResponse = borrowTransactionService.getBorrowTransactionById(id);
        return ResponseEntity.ok(borrowResponse);
    }

    @PutMapping("/return")
    public ResponseEntity<?> returnBook(@RequestBody ReturnRequest returnRequest) {
        ReturnResponse returnResponse = borrowTransactionService.returnBook(returnRequest);
        return ResponseEntity.ok(returnResponse);
    }
}
