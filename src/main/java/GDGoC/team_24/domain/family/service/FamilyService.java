package GDGoC.team_24.domain.family.service;

import GDGoC.team_24.domain.family.Converter.FamilyConverter;
import GDGoC.team_24.domain.family.domain.Family;
import GDGoC.team_24.domain.family.dto.FamilyRequestDto;
import GDGoC.team_24.domain.family.dto.FamilyResponseDto;
import GDGoC.team_24.domain.family.repository.FamilyRepository;
import GDGoC.team_24.domain.user.domain.User;
import GDGoC.team_24.domain.user.repository.UserRepository;
import GDGoC.team_24.global.code.status.ErrorStatus;
import GDGoC.team_24.global.exception.Handler.ErrorHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FamilyService {
    private final UserRepository userRepository;
    private final FamilyRepository familyRepository;
    public FamilyResponseDto.FamilyInfoDto familySignUp(FamilyRequestDto.FamilySignUpDto request) {
        User user = userRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() -> new ErrorHandler(ErrorStatus.SENIOR_NOT_FOUND));
        if(user.getFamily() != null) {
            throw new ErrorHandler(ErrorStatus.FAMILY_ALREADY);
        }
        Family family = FamilyConverter.toFamily(request,user);
        familyRepository.save(family);
        user.setFamily(family);
        userRepository.save(user);
        return FamilyConverter.toFamilyInfoDto(family, user);
    }

    public FamilyResponseDto.FamilyInfoDto familyLogin(FamilyRequestDto.FamilyLoginDto request) {
        User user = userRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() -> new ErrorHandler(ErrorStatus.SENIOR_NOT_FOUND));
        Family family = familyRepository.findByName(request.getName());
        if(family == null) {
            throw new ErrorHandler(ErrorStatus.FAMILY_NOT_FOUND);
        }
        return FamilyConverter.toFamilyInfoDto(family, user);
    }

    public FamilyResponseDto.FamilyInfoDto getFamilyInfo(Long familyId) {
        Family family = familyRepository.findById(familyId)
                .orElseThrow(() -> new ErrorHandler(ErrorStatus.FAMILY_NOT_FOUND));
        User user = userRepository.findByFamily(family);
        return FamilyConverter.toFamilyInfoDto(family, user);
    }
}
