package GDGoC.team_24.domain.user.domain;

import GDGoC.team_24.domain.diary.domain.Diary;
import GDGoC.team_24.domain.family.domain.Family;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private GENDER gender;

    private String birthaDate;

    private String phoneNumber;

    private String emoji;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id")
    private Family family;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Diary> diaries = new ArrayList<>();

    @Builder
    public User(Long id, String name, GENDER gender, String birthaDate, String phoneNumber,String emoji, Family family) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthaDate = birthaDate;
        this.phoneNumber = phoneNumber;
        this.emoji = emoji;
        this.family = family;
    }
    public void setFamily(Family family){
        this.family = family;
    }

}
