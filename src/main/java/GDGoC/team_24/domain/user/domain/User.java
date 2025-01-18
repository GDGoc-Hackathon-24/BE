package GDGoC.team_24.domain.user.domain;

import GDGoC.team_24.domain.family.domain.Family;
import GDGoC.team_24.domain.quiz.domain.Quiz;
import GDGoC.team_24.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private GENDER gender;

    private String birthaDate;

    private String phoneNumber;

//    @Enumerated(EnumType.STRING)
//    private EMOJI emoji;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id")
    private Family family;

}
