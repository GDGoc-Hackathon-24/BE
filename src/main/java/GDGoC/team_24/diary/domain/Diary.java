package GDGoC.team_24.diary.domain;

import GDGoC.team_24.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private TODAYEMTION todayEmtion;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
