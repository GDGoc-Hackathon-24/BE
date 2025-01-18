package GDGoC.team_24.domain.quiz.service;

import GDGoC.team_24.domain.family.domain.Family;
import GDGoC.team_24.domain.family.repository.FamilyRepository;
import GDGoC.team_24.domain.quiz.domain.Quiz;
import GDGoC.team_24.domain.quiz.domain.QuizOption;
import GDGoC.team_24.domain.quiz.dto.CustomPage;
import GDGoC.team_24.domain.quiz.dto.QuizRequestDto;
import GDGoC.team_24.domain.quiz.dto.QuizResponseDto;
import GDGoC.team_24.domain.quiz.repository.QuizOptionRepository;
import GDGoC.team_24.domain.quiz.repository.QuizRepository;
import GDGoC.team_24.domain.user.domain.User;
import GDGoC.team_24.domain.user.repository.UserRepository;
import GDGoC.team_24.global.code.status.ErrorStatus;
import GDGoC.team_24.global.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizOptionRepository quizOptionRepository;
    private final FamilyRepository familyRepository;
    private final UserRepository userRepository;

    public Quiz makeQuiz(QuizRequestDto.createQuiz request, Long familyId) {
        Family family = familyRepository.findById(familyId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        User user = family.getUser(); // 중복 조회 제거

        Quiz newQuiz = new Quiz();
        newQuiz.setQuestion(request.getQuestion());
        newQuiz.setAnswer(request.getAnswer());
        newQuiz.setCompleted(false);
        newQuiz.setUser(user); // User 설정

        Quiz savedQuiz = quizRepository.save(newQuiz);

        // 선택지 저장
        List<QuizOption> options = new ArrayList<>();
        for (int i = 0; i < request.getOptions().size(); i++) {
            QuizOption option = new QuizOption();
            option.setQuiz(savedQuiz);
            option.setNumber((long) (i + 1)); // 선택지 번호는 1부터 시작
            option.setText(request.getOptions().get(i));
            options.add(option);
        }
        quizOptionRepository.saveAll(options);

        return savedQuiz;
    }


    public Boolean answerQuiz(QuizRequestDto.answerQuiz request) {
        Quiz quiz = quizRepository.findById(request.getQuizId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.QUIZ_NOT_FOUND));

        // 객관식 정답 비교
        boolean isCorrect = quiz.getAnswer().equals(request.getAnswer());
        quiz.setCorrect(isCorrect);
        quiz.setCompleted(true); // 퀴즈 완료 처리
        quiz.setQuizAnswer(request.getAnswer()); // 사용자 입력 저장

        if (!isCorrect) {
            // 틀린 경우: 하루 뒤에 다시 풀 수 있도록 설정
            quiz.setNextRetryTime(LocalDateTime.now().plusDays(1));
        } else {
            // 맞은 경우: 7일 뒤에 다시 풀 수 있도록 설정
            quiz.setNextRetryTime(LocalDateTime.now().plusDays(7));
        }

        quizRepository.save(quiz);
        return isCorrect;
    }
    

    public CustomPage<QuizResponseDto.quizList> readAllQuizzes(Long userId, Boolean solve, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        Page<Quiz> quizzes = solve
                ? quizRepository.findSolvedQuizzes(user, pageable) // 풀었던 퀴즈 조회
                : quizRepository.findUnsolvedQuizzes(user, pageable); // 안 푼 퀴즈 조회

        if (quizzes.isEmpty()) {
            throw new GeneralException(ErrorStatus.QUIZ_NOT_FOUND);
        }

        // `Page` 객체를 `CustomPage`로 변환
        List<QuizResponseDto.quizList> quizList = quizzes.getContent().stream()
                .map(quiz -> {
                    // 각 퀴즈의 선택지만 조회
                    List<QuizOption> options = quizOptionRepository.findByQuiz(quiz);

                    // 선택지 DTO로 변환
                    List<QuizResponseDto.QuizOptionDto> optionDtos = options.stream()
                            .map(option -> QuizResponseDto.QuizOptionDto.builder()
                                    .number(option.getNumber())
                                    .text(option.getText())
                                    .build())
                            .toList();

                    // `solvedAt` 값을 퀴즈 상태에 따라 설정
                    LocalDateTime solvedAt = quiz.isCompleted() ? quiz.getUpdatedAt() : null;

                    // 퀴즈 DTO로 변환
                    return QuizResponseDto.quizList.builder()
                            .id(quiz.getId())
                            .question(quiz.getQuestion())
                            .options(optionDtos)
                            .answer(quiz.getAnswer())
                            .quizAnswer(quiz.getQuizAnswer())
                            .isCorrect(quiz.isCorrect())
                            .solvedAt(solvedAt) // 상태에 따라 `solvedAt` 값 설정
                            .build();
                })
                .toList();

        return CustomPage.<QuizResponseDto.quizList>builder()
                .content(quizList)
                .pageNumber(quizzes.getNumber())
                .pageSize(quizzes.getSize())
                .totalElements(quizzes.getTotalElements())
                .totalPages(quizzes.getTotalPages())
                .isLast(quizzes.isLast())
                .isFirst(quizzes.isFirst())
                .build();
    }



    public Boolean noAnswerQuiz(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        // 다음 풀 수 있는 퀴즈 존재 여부 확인
        return quizRepository.existsByUserAndIsCompletedAndNextRetryTimeBefore(user, false);
    }


}
