package org.practice.libraryspring.dto.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowRequest {
    private Long bookId;
    private Long userId;
}
