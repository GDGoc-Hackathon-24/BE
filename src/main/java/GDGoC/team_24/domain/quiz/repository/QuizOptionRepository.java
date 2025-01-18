package GDGoC.team_24.domain.quiz.repository;

import GDGoC.team_24.domain.quiz.domain.Quiz;
import GDGoC.team_24.domain.quiz.domain.QuizOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizOptionRepository extends JpaRepository<QuizOption, Long> {
    List<QuizOption> findByQuiz (Quiz quiz);
}
