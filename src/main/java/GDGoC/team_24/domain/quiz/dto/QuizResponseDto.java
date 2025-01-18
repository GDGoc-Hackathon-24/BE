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

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class quizList {
        private Long id;
        private String question; // 퀴즈
        private List<QuizOptionDto> options; // 선택지 목록
        private Long answer; // 정답 번호
        private Long quizAnswer; // 유저의 답변 번호
        private boolean isCorrect; // 맞/틀 여부
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
