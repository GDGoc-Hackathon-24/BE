package GDGoC.team_24.domain.family.domain;

import GDGoC.team_24.domain.user.domain.User;
import GDGoC.team_24.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Family extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private RELATIONSHIP relationship;

    private String gender;

    @OneToOne(mappedBy = "family")
    private User user;
}
