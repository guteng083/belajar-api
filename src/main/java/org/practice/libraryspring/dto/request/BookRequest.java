package org.practice.libraryspring.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequest {
    private String title;
    private Long authorId;
    private String publisher;
    private LocalDate publishedDate;
    private int totalCopies;
}
