package GDGoC.team_24.domain.quiz.domain;

import GDGoC.team_24.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question; //퀴즈

    private String answer; //퀴즈 정답

    private boolean isCompleted; //퀴즈 품 여부

    private boolean isCorrect; //퀴즈 맞/틀 여부

    private String quizAnswer; //퀴즈 답변

    @OneToOne(mappedBy = "quiz")
    private User user;
}
