package GDGoC.team_24.domain.quiz.converter;

import GDGoC.team_24.domain.quiz.domain.Quiz;
import GDGoC.team_24.domain.quiz.dto.QuizResponseDto;

public class QuizConverter {

    public static QuizResponseDto.QuizResponseDTO toQuizResponseDTO(Quiz quiz){
        return QuizResponseDto.QuizResponseDTO.builder()
                .quizId(quiz.getId())
                .createdAt(quiz.getCreatedAt())
                .build();
    }

    public static QuizResponseDto.quizList toquizListDTO(Quiz quiz){
        return QuizResponseDto.quizList.builder()
                .id(quiz.getId())
                .question(quiz.getQuestion())
                .quizAnswer(quiz.getAnswer())
                .answer(quiz.getAnswer())
                .isCorrect(quiz.isCorrect())
                .build();
    }
}
