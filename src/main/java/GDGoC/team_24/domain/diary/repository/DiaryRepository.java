package GDGoC.team_24.domain.diary.repository;


import GDGoC.team_24.domain.diary.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long > {

    List<Diary> findByUserId(Long userId);


//    @Query("SELECT d FROM Diary d LEFT JOIN FETCH d.photos WHERE d.user.id = :userId")
//    List<Diary> findAllByUserIdWithPhotos(@Param("userId") Long userId);
}
