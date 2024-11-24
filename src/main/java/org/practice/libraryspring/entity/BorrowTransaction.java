package org.practice.libraryspring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Table(name = "borrow_transaction")
public class BorrowTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member  member;

    @Column(nullable = false, name = "borrow_date")
    private LocalDate borrowDate;

    @Column(nullable = false, name = "due_date")
    private LocalDate dueDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    private Double fine;

    @PrePersist
    public void prePersist() {
        borrowDate = LocalDate.now();
        dueDate = LocalDate.now().plusDays(7);
    }

    @PreUpdate
    public void preUpdate() {
        returnDate = LocalDate.now();
        if(returnDate.isAfter(dueDate)) {
            fine = (double) 1000 * (returnDate.getDayOfYear() - dueDate.getDayOfYear());
        }
    }
}
