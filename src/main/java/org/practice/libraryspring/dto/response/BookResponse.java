package org.practice.libraryspring.dto.response;

import lombok.*;
import org.practice.libraryspring.entity.Book;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private LocalDate publishedDate;
    private int totalCopies;
    private int availableCopies;

    public static BookResponse BooktoBookResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName())
                .publisher(book.getPublisher())
                .publishedDate(book.getPublishedDate())
                .totalCopies(book.getTotalCopies())
                .availableCopies(book.getAvailableCopies())
                .build();
    }
}
