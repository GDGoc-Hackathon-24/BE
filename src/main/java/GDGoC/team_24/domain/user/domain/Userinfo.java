package GDGoC.team_24.domain.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Userinfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private HOUSE house;

    private Long sun;
    private Long daughter;
    private String hubby;
    private String medicine;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
