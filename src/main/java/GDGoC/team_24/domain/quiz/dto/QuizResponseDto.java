package GDGoC.team_24.domain.quiz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.util.List;

import java.time.LocalDateTime;

public class QuizResponseDto {

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QuizResponseDTO{
        private Long quizId;
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    public static class quizList {
        private Long id;
        private String question;
        private List<QuizOptionDto> options;
        private Long answer;
        private Long quizAnswer;
        private boolean isCorrect;
        private LocalDateTime solvedAt; // 푼 날짜 추가
    }

    // 선택지 DTO
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QuizOptionDto {
        private Long number; // 선택지 번호
        private String text; // 선택지 내용
    }
}
