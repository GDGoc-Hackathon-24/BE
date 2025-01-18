package GDGoC.team_24.domain.user.dto;

import GDGoC.team_24.domain.user.domain.HOUSE;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class UserInfoRequestDto {
    private HOUSE house;
    private Long sun;
    private Long daughter;
    private String hubby;
    private String medicine;
}
