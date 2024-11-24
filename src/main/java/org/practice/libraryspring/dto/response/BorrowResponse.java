package org.practice.libraryspring.dto.response;

import lombok.*;
import org.practice.libraryspring.entity.Book;
import org.practice.libraryspring.entity.BorrowTransaction;
import org.practice.libraryspring.entity.Member;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowResponse {
    private Long id;
    private BookResponse book;
    private MemberResponse member;
    private LocalDate borrowDate;
    private LocalDate dueDate;

    public static BorrowResponse BorrowToBorrowResponse(BorrowTransaction transaction) {
        return BorrowResponse.builder()
                .id(transaction.getId())
                .book(BookResponse.BooktoBookResponse(transaction.getBook()))
                .member(MemberResponse.memberToMemberResponse(transaction.getMember()))
                .borrowDate(transaction.getBorrowDate())
                .dueDate(transaction.getDueDate())
                .build();

    }
}
