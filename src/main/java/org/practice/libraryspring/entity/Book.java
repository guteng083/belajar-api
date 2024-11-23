package org.practice.libraryspring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    private String publisher;

    @Column(name = "published_date")
    private LocalDate publishedDate;

    @Column(nullable = false, name = "total_copies")
    private int totalCopies;

    @Column(name = "available_copies")
    private int availableCopies;

    @PrePersist
    protected void onCreate() {
        availableCopies = this.totalCopies;
    }
}
