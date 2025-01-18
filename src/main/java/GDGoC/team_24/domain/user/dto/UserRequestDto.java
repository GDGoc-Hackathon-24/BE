package GDGoC.team_24.domain.user.dto;

public record UserRequestDto(
        String birthDate,
        String gender,
        String name,
        String emoji,
        String phoneNumber
) {
}
