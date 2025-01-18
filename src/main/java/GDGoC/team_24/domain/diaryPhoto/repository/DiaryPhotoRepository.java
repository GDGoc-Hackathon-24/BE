package GDGoC.team_24.domain.diaryPhoto.repository;

import GDGoC.team_24.domain.diary.domain.Diary;
import GDGoC.team_24.domain.diaryPhoto.domain.DiaryPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryPhotoRepository extends JpaRepository<DiaryPhoto, Long> {

    List<DiaryPhoto> findByDiary(Diary diary);
}
