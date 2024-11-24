package org.practice.libraryspring.dto.response;

import lombok.*;
import org.practice.libraryspring.entity.Book;
import org.practice.libraryspring.entity.BorrowTransaction;
import org.practice.libraryspring.entity.Member;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnResponse {
    private Long id;
    private BookResponse book;
    private MemberResponse member;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private double fine;

    public static ReturnResponse returnResponse(BorrowTransaction borrowTransaction) {
        return ReturnResponse.builder()
                .id(borrowTransaction.getId())
                .book(BookResponse.BooktoBookResponse(borrowTransaction.getBook()))
                .member(MemberResponse.memberToMemberResponse(borrowTransaction.getMember()))
                .borrowDate(borrowTransaction.getBorrowDate())
                .dueDate(borrowTransaction.getDueDate())
                .returnDate(borrowTransaction.getReturnDate())
                .fine(borrowTransaction.getFine())
                .build();
    }
}
