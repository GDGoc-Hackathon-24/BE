package GDGoC.team_24.domain.family.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class FamilyResponseDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class FamilyInfoDto{
        Long familyId;
        String name;
        String gender;
        String relationship;
    }
}
