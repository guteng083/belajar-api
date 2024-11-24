package org.practice.libraryspring.service.impl;

import lombok.RequiredArgsConstructor;
import org.practice.libraryspring.dto.request.BorrowRequest;
import org.practice.libraryspring.dto.request.ReturnRequest;
import org.practice.libraryspring.dto.response.BorrowResponse;
import org.practice.libraryspring.dto.response.ReturnResponse;
import org.practice.libraryspring.entity.Book;
import org.practice.libraryspring.entity.BorrowTransaction;
import org.practice.libraryspring.entity.Member;
import org.practice.libraryspring.repository.BorrowTransactionRepository;
import org.practice.libraryspring.service.BookService;
import org.practice.libraryspring.service.BorrowTransactionService;
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
public class BorrowTransactionServiceImpl implements BorrowTransactionService {
    private final BorrowTransactionRepository borrowTransactionRepository;
    private final BookService bookService;
    private final MemberService memberService;

    @Override
    public BorrowResponse borrowBook(BorrowRequest borrowRequest) {
        Book book = bookService.getBookById(borrowRequest.getBookId());
        Member member = memberService.getMemberById(borrowRequest.getUserId());
        if(book.getAvailableCopies() == 0){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Book not available");
        }

        BorrowTransaction borrowTransaction = BorrowTransaction.builder()
                .book(book)
                .member(member)
                .build();
        borrowTransactionRepository.save(borrowTransaction);

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        return BorrowResponse.BorrowToBorrowResponse(borrowTransaction);
    }

    @Override
    public BorrowTransaction getBorrowTransactionById(String id) {
        Optional<BorrowTransaction> borrowTransaction = borrowTransactionRepository.findById(id);
        if(borrowTransaction.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No transaction found with id " + id);
        }
        return borrowTransaction.get();
    }

    @Override
    public ReturnResponse returnBook(ReturnRequest returnRequest) {
        BorrowTransaction borrowTransaction = getBorrowTransactionById(returnRequest.getId());
        BorrowTransaction returned = new BorrowTransaction();
        returned.setId(borrowTransaction.getId());
        returned.setBook(borrowTransaction.getBook());
        returned.setMember(borrowTransaction.getMember());
        returned.setBorrowDate(borrowTransaction.getBorrowDate());
        returned.setDueDate(borrowTransaction.getDueDate());
        borrowTransactionRepository.save(returned);
        return ReturnResponse.returnResponse(returned);
    }

    @Override
    public List<BorrowResponse> getBorrowTransactions() {
        List<BorrowTransaction> borrowTransactions = borrowTransactionRepository.findAll();
        List<BorrowResponse> borrowResponses = new ArrayList<>();
        borrowTransactions.forEach(borrowTransaction -> {
            borrowResponses.add(BorrowResponse.BorrowToBorrowResponse(borrowTransaction));
        });
        return borrowResponses;
    }
}
