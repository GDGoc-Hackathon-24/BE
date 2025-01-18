package GDGoC.team_24.domain.family.controller;

import GDGoC.team_24.domain.family.dto.FamilyRequestDto;
import GDGoC.team_24.domain.family.dto.FamilyResponseDto;
import GDGoC.team_24.domain.family.service.FamilyService;
import GDGoC.team_24.global.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/family")
@Tag(name="family", description = "가족 관리하는 api입니다.")

public class FamilyController {
    private final FamilyService familyService;


    @PostMapping
    @Operation(summary = "가족 회원가입 API", description = "가족 계정을 생성하는 API입니다.")
    public ApiResponse<FamilyResponseDto.FamilyInfoDto> userSignUp(@RequestBody FamilyRequestDto.FamilySignUpDto request) {

        return ApiResponse.onSuccess(familyService.familySignUp(request));

    }

    @PostMapping("/login")
    @Operation(summary = "가족 로그인 API", description = "가족 로그인 API입니다.")
    public ApiResponse<FamilyResponseDto.FamilyInfoDto> userLogin(@RequestBody FamilyRequestDto.FamilyLoginDto request) {

        return ApiResponse.onSuccess(familyService.familyLogin(request));

    }

    @GetMapping("/{familyId}")
    @Operation(summary = "내 정보 조회 API", description = "정보 조회하는 생성하는 API입니다.")
    public ApiResponse<FamilyResponseDto.FamilyInfoDto> userGet(@PathVariable("familyId") Long familyId) {

        return ApiResponse.onSuccess(familyService.getFamilyInfo(familyId));

    }
}
