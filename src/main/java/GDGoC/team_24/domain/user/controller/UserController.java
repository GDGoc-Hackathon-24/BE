package GDGoC.team_24.domain.user.controller;

import GDGoC.team_24.global.ApiResponse;
import GDGoC.team_24.domain.user.dto.UserLoginReqDto;
import GDGoC.team_24.domain.user.dto.UserRequestDto;
import GDGoC.team_24.domain.user.dto.UserResponseDto;
import GDGoC.team_24.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Tag(name="user", description = "유저 관리하는 api입니다.")

public class UserController {

    private final UserService userService;


    @PostMapping
    @Operation(summary = "유저 회원가입 API", description = "유저 계정을 생성하는 API입니다.")
    public ApiResponse<UserResponseDto> userSignUp(@RequestBody UserRequestDto userRequestDto) {

        return ApiResponse.onSuccess(userService.userSignUp(userRequestDto));

    }

    @GetMapping
    @Operation(summary = "유저 로그인 API", description = "로그인 생성하는 API입니다.")
    public ApiResponse<UserResponseDto> userLogin(@RequestBody UserLoginReqDto userLoginReqDto) {

        return ApiResponse.onSuccess(userService.userLogin(userLoginReqDto));

    }

    @GetMapping("/{userId}")
    @Operation(summary = "내 정보 조회 API", description = "정보 조회하는 생성하는 API입니다.")
    public ApiResponse<UserResponseDto> userGet(@PathVariable("userId") Long userId) {

        return ApiResponse.onSuccess(userService.userGet(userId));

    }






}
