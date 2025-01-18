package GDGoC.team_24.domain.quiz.repository;

import GDGoC.team_24.domain.quiz.domain.Quiz;
import GDGoC.team_24.domain.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Page<Quiz> findByUserAndIsCompleted(User user, Boolean isCompleted, Pageable pageable);
    List<Quiz> findByUser (User user);
    boolean existsByUserAndIsCompleted(User user, Boolean isCompleted);
}
