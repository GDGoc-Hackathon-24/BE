package GDGoC.team_24.domain.quiz.controller;

import GDGoC.team_24.domain.quiz.converter.QuizConverter;
import GDGoC.team_24.domain.quiz.domain.Quiz;
import GDGoC.team_24.domain.quiz.dto.QuizRequestDto;
import GDGoC.team_24.domain.quiz.dto.QuizResponseDto;
import GDGoC.team_24.domain.quiz.service.QuizService;
import GDGoC.team_24.global.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quiz")
@Tag(name = "Quiz API", description = "퀴즈 관련 API")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Operation(summary = "퀴즈 생성 API", description = "퀴즈를 생성합니다. option엔 객관식 답안을 적어주세요. ")
    @PostMapping("/{familyId}")
    public ApiResponse<QuizResponseDto.QuizResponseDTO> createQuiz (@PathVariable Long familyId, @RequestBody QuizRequestDto.createQuiz request){
        return ApiResponse.onSuccess(QuizConverter.toQuizResponseDTO(quizService.makeQuiz(request, familyId)));
    }

    @Operation(summary = "퀴즈 정답 여부 API", description = "퀴즈 정답 여부를 반환합니다. 퀴즈id와 사용자가 적은 답을 주세요. ")
    @PostMapping("/answer")
    public ApiResponse<Boolean> answerQuiz(@RequestBody QuizRequestDto.answerQuiz request) {
        return ApiResponse.onSuccess(quizService.answerQuiz(request));
    }


    @Operation(summary = "퀴즈 조회 API", description = "지금까지 푼 퀴즈를 페이지네이션으로 조회합니다.")
    @GetMapping("/{userId}/answerlist")
    public ApiResponse<Page<QuizResponseDto.quizList>> listQuiz(
            @PathVariable Long userId,
            Pageable pageable
    ) {
        return ApiResponse.onSuccess(quizService.readAllQuizzes(userId, true, pageable));
    }


    @Operation(summary = "안푼 퀴즈 여부 API", description = "안푼 퀴즈 여부를 반환합니다.")
    @GetMapping("/{userId}/noanswer")
    public ApiResponse<Boolean> noAnswerQuiz (@PathVariable Long userId){
        return ApiResponse.onSuccess(quizService.noAnswerQuiz(userId));
    }

    @Operation(summary = "안 푼 퀴즈 조회 API", description = "다시 풀 수 있는 퀴즈만 반환합니다.")
    @GetMapping("/{userId}/noanswerlist")
    public ApiResponse<Page<QuizResponseDto.quizList>> listNoQuiz(
            @PathVariable Long userId,
            Pageable pageable
    ) {
        return ApiResponse.onSuccess(quizService.readAllQuizzes(userId, false, pageable));
    }




}
