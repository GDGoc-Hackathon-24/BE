package GDGoC.team_24.domain.quiz.domain;

import GDGoC.team_24.domain.user.domain.User;
import GDGoC.team_24.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class QuizOption extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Quiz quiz;

    private Long number;
    private String text;

}
