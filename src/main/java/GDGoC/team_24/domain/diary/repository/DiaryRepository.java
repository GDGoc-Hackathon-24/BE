package GDGoC.team_24.domain.diary.repository;


import GDGoC.team_24.domain.diary.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long > {
}
