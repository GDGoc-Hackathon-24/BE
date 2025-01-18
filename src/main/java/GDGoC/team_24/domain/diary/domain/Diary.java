package GDGoC.team_24.domain.diary.domain;

import GDGoC.team_24.domain.diary.dto.DiaryRequestDto;
import GDGoC.team_24.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
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
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Diary(String content, LocalDateTime date, TODAYEMTION todayEmtion, User user) {
        this.content = content;
        this.date = date;
        this.todayEmtion = todayEmtion;
        this.user = user;
    }

    public void update(DiaryRequestDto userRequestDto) {
        this.content = content;
        this.date = date;
        this.todayEmtion = todayEmtion;
        this.user = user;
    }
}
