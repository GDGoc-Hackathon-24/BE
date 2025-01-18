package GDGoC.team_24.domain.quiz.dto;

import GDGoC.team_24.domain.quiz.domain.Quiz;
import lombok.*;
import java.util.List;

public class QuizRequestDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @Setter
    @AllArgsConstructor
    public static class createQuiz{
        private String question; // 퀴즈 질문
        private Long answer;     // 정답(선택지 번호)
        private List<String> options; // 선택지 목록
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @Setter
    @AllArgsConstructor
    public static class answerQuiz{
        private Long quizId;
        private Long answer;
    }
}
