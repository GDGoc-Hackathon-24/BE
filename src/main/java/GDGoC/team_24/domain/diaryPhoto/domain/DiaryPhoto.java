package GDGoC.team_24.domain.diaryPhoto.domain;

import GDGoC.team_24.domain.diary.domain.Diary;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class DiaryPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String photoUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diaryId")
    private Diary diary;

    @Builder
    public DiaryPhoto(String photoUrl, Diary diary) {
        this.photoUrl = photoUrl;
        this.diary = diary;
    }

}
