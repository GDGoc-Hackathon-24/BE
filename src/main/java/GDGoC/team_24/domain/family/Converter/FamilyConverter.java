package GDGoC.team_24.domain.family.Converter;

import GDGoC.team_24.domain.family.domain.Family;
import GDGoC.team_24.domain.family.dto.FamilyRequestDto;
import GDGoC.team_24.domain.family.dto.FamilyResponseDto;
import GDGoC.team_24.domain.user.domain.User;

public class FamilyConverter {
    public static Family toFamily(FamilyRequestDto.FamilySignUpDto request, User user) {
        return Family.builder()
                .gender(request.getGender())
                .name(request.getName())
                .relationship(request.getRelationship())
                .emoji(request.getEmoji())
                .user(user)
                .build();
    }
    public static FamilyResponseDto.FamilyInfoDto toFamilyInfoDto(Family family, User user) {
        return FamilyResponseDto.FamilyInfoDto.builder()
                .seniorId(user.getId())
                .familyId(family.getId())
                .gender(family.getGender())
                .name(family.getName())
                .emoji(family.getEmoji())
                .relationship(family.getRelationship())
                .build();
    }
}
