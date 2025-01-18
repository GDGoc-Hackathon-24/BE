package GDGoC.team_24.domain.quiz.service;

import GDGoC.team_24.domain.family.domain.Family;
import GDGoC.team_24.domain.family.repository.FamilyRepository;
import GDGoC.team_24.domain.quiz.domain.Quiz;
import GDGoC.team_24.domain.quiz.domain.QuizOption;
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
        // 퀴즈 생성
        Quiz newQuiz = new Quiz();
        newQuiz.setQuestion(request.getQuestion());
        newQuiz.setAnswer(request.getAnswer());
        newQuiz.setCompleted(false);

        // Family 및 User 조회
        Family family = familyRepository.findById(familyId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));
        User user = userRepository.findById(family.getUser().getId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));
        newQuiz.setUser(user);

        // 퀴즈 저장
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

        // 정답 비교
        boolean isCorrect = quiz.getAnswer().equals(request.getAnswer());
        quiz.setCorrect(isCorrect);
        quiz.setCompleted(true); // 퀴즈 완료 상태로 변경
        quiz.setQuizAnswer(request.getAnswer()); // 사용자 답변 저장
        quizRepository.save(quiz);

        return isCorrect;
    }


    public Page<QuizResponseDto.quizList> readAllQuizzes(Long userId, Boolean solve, Pageable pageable) {
        // User 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        // User가 푼/안 푼 퀴즈를 페이지네이션으로 조회
        Page<Quiz> quizzes = quizRepository.findByUserAndIsCompleted(user, solve, pageable);

        if (quizzes.isEmpty()) {
            throw new GeneralException(ErrorStatus.QUIZ_NOT_FOUND);
        }

        // 각 퀴즈와 해당 선택지 정보를 DTO로 변환
        return quizzes.map(quiz -> {
            // 선택지 조회
            List<QuizOption> options = quizOptionRepository.findByQuiz(quiz);

            // 선택지를 DTO로 변환
            List<QuizResponseDto.QuizOptionDto> optionDtos = options.stream()
                    .map(option -> QuizResponseDto.QuizOptionDto.builder()
                            .number(option.getNumber())
                            .text(option.getText())
                            .build())
                    .toList();

            // 퀴즈와 선택지 정보를 DTO로 변환
            return QuizResponseDto.quizList.builder()
                    .id(quiz.getId())
                    .question(quiz.getQuestion())
                    .options(optionDtos)
                    .answer(quiz.getAnswer())
                    .quizAnswer(quiz.getQuizAnswer())
                    .isCorrect(quiz.isCorrect())
                    .build();
        });
    }


    public Boolean noAnswerQuiz(Long userId) {
        // User 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        // 안 푼 퀴즈 존재 여부 확인
        return quizRepository.existsByUserAndIsCompleted(user, false);
    }

}
