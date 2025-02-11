package GDGoC.team_24.domain.user.dto;

import GDGoC.team_24.domain.family.domain.Family;
import GDGoC.team_24.domain.user.domain.User;

public record UserResponseDto(
        Long id,
        String birthDate,
        String gender,
        String name,
        String emoji,
        String phoneNumber,
        Long familyId

) {
    public UserResponseDto(User user) {
        this(
                user.getId(),
                user.getBirthaDate(),
                user.getGender().toString(),
                user.getName(),
                user.getEmoji(),
                user.getPhoneNumber(),
                user.getFamily() != null ? user.getFamily().getId() : null);
    }
}
