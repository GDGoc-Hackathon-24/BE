package GDGoC.team_24.domain.family.domain;

import GDGoC.team_24.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Builder
@AllArgsConstructor
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String relationship;

    private String gender;

    @OneToOne(mappedBy = "family")
    private User user;
}
