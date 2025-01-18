package GDGoC.team_24.domain.user.service;

import GDGoC.team_24.domain.user.domain.GENDER;
import GDGoC.team_24.domain.user.domain.HOUSE;
import GDGoC.team_24.domain.user.domain.User;
import GDGoC.team_24.domain.user.domain.Userinfo;
import GDGoC.team_24.domain.user.dto.UserInfoRequestDto;
import GDGoC.team_24.domain.user.dto.UserLoginReqDto;
import GDGoC.team_24.domain.user.dto.UserRequestDto;
import GDGoC.team_24.domain.user.dto.UserResponseDto;
import GDGoC.team_24.domain.user.repository.UserInfoRepository;
import GDGoC.team_24.domain.user.repository.UserRepository;
import GDGoC.team_24.global.code.status.ErrorStatus;
import GDGoC.team_24.global.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;

    public UserResponseDto userSignUp(UserRequestDto userRequestDto) {

        if(isDuplicate(userRequestDto.phoneNumber())){
            throw new GeneralException(ErrorStatus.FAMILY_ALREADY);
        }

        GENDER gender = GENDER.valueOf(userRequestDto.gender().toUpperCase());

        User user = User.builder()
                .name(userRequestDto.name())
                .gender(gender)
                .birthaDate(userRequestDto.birthDate())
                .phoneNumber(String.valueOf(userRequestDto.phoneNumber()))
                .emoji(userRequestDto.emoji())
                .build();

        userRepository.save(user);

        return new UserResponseDto(user);
    }

    private boolean isDuplicate(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }

    public UserResponseDto userLogin(UserLoginReqDto userLoginReqDto) {

        User user = userRepository.findByPhoneNumber(userLoginReqDto.phoneNumber())
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));


        return new UserResponseDto(user);


    }

    public UserResponseDto userGet(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        return new UserResponseDto(user);
    }

    public Long userInfoSave(UserInfoRequestDto request, Long userId) {
        // 유저 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        // Userinfo 생성 및 값 설정
        Userinfo userinfo = new Userinfo();
        userinfo.setUser(user);
        userinfo.setHouse(request.getHouse());
        userinfo.setSun(request.getSun());
        userinfo.setDaughter(request.getDaughter());
        userinfo.setHubby(request.getHubby());
        userinfo.setMedicine(request.getMedicine());

        // 저장
        userInfoRepository.save(userinfo);

        return userinfo.getId();
    }

}
