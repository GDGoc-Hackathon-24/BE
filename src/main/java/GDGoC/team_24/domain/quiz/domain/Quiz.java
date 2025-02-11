package GDGoC.team_24.domain.quiz.domain;

import GDGoC.team_24.domain.user.domain.User;
import GDGoC.team_24.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Quiz extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question; //퀴즈

    private Long answer; //퀴즈 정답

    private boolean isCompleted; //퀴즈 품 여부

    private boolean isCorrect; //퀴즈 맞/틀 여부

    private Long quizAnswer; //퀴즈 답변

    private LocalDateTime nextRetryTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
