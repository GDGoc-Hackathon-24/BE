package GDGoC.team_24.domain.diary.controller;

import GDGoC.team_24.domain.diary.dto.DiaryRequestDto;
import GDGoC.team_24.domain.diary.dto.DiaryResponseDto;
import GDGoC.team_24.domain.diary.service.DiaryService;
import GDGoC.team_24.global.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diary")
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping
    @Operation(summary = "일기 작성 API", description = "일기 생성하는 API입니다.")
    public ApiResponse<DiaryResponseDto> createDiary(@RequestPart(name = "diary") DiaryRequestDto userRequestDto,
                                                     @RequestPart(name = "ImageFile", required = false) List<MultipartFile> imgs
                                      ) {
        return ApiResponse.onSuccess(diaryService.createDiary(userRequestDto, imgs));

    }

    @PostMapping("/{diaryId}")
    @Operation(summary = "일기조회 API", description = "일기 조회하는 API입니다.")
    public ApiResponse<DiaryResponseDto> getDiary(@PathVariable Long diaryId) {
        
        return ApiResponse.onSuccess( diaryService.getDiary(diaryId));

    }

    @PatchMapping("{diaryId}")
    @Operation(summary = "일기 수정 API", description = "일기 수정하는 API입니다.")
    public ApiResponse<DiaryResponseDto> updateDiary(@PathVariable Long diaryId,
                                                     @RequestPart(name = "diary") DiaryRequestDto userRequestDto,
                                                     @RequestPart(name = "ImageFile", required = false) List<MultipartFile> imgs) {
        
        return ApiResponse.onSuccess( diaryService.updateDiary(diaryId, userRequestDto, imgs));

    }
    
    @DeleteMapping("{diaryId}")
    @Operation(summary = "일기삭제 API", description = "일기 삭제하는 API입니다.")
    public ApiResponse deleteDiary(@PathVariable Long diaryId) {

        return ApiResponse.onSuccess("일기가 성공적으로 삭제되었습니다.");
    }

}
