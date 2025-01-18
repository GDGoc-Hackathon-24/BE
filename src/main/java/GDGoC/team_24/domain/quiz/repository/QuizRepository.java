package GDGoC.team_24.domain.quiz.repository;

import GDGoC.team_24.domain.quiz.domain.Quiz;
import GDGoC.team_24.domain.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Page<Quiz> findByUserAndIsCompleted(User user, Boolean isCompleted, Pageable pageable);
    List<Quiz> findByUser (User user);
    @Query("SELECT q FROM Quiz q " +
            "WHERE q.user = :user AND q.isCompleted = true")
    Page<Quiz> findSolvedQuizzes(@Param("user") User user, Pageable pageable);
    @Query("SELECT q FROM Quiz q " +
            "WHERE q.user = :user " +
            "AND q.isCompleted = false " +
            "AND (q.nextRetryTime IS NULL OR q.nextRetryTime <= CURRENT_TIMESTAMP)")
    Page<Quiz> findUnsolvedQuizzes(@Param("user") User user, Pageable pageable);
    @Query("SELECT CASE WHEN COUNT(q) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Quiz q WHERE q.user = :user AND q.isCompleted = :isCompleted " +
            "AND (q.nextRetryTime IS NULL OR q.nextRetryTime <= CURRENT_TIMESTAMP)")
    boolean existsByUserAndIsCompletedAndNextRetryTimeBefore(
            @Param("user") User user,
            @Param("isCompleted") Boolean isCompleted);



}
