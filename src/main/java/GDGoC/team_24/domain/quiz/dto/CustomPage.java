package GDGoC.team_24.domain.quiz.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomPage<T> {
    private List<T> content; // 페이징된 실제 데이터
    private int pageNumber;  // 현재 페이지 번호
    private int pageSize;    // 페이지 크기
    private long totalElements; // 전체 요소 수
    private int totalPages;  // 전체 페이지 수
    private boolean isLast;  // 마지막 페이지 여부
    private boolean isFirst; // 첫 번째 페이지 여부
}
