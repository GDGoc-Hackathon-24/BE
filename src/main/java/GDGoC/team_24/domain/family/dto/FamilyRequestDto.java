package GDGoC.team_24.domain.family.dto;

import lombok.Getter;

public class FamilyRequestDto {
    @Getter
    public static class FamilySignUpDto {
        String name;
        String phoneNumber;
        String gender;
        String relationship;
        String emoji;
    }
    @Getter
    public static class FamilyLoginDto {
        String name;
        String phoneNumber;
    }
}
