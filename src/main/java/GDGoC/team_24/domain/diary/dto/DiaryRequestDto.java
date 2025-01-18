package GDGoC.team_24.domain.diary.dto;

import java.time.LocalDateTime;
import java.util.List;

public record DiaryRequestDto(
        LocalDateTime date,
        String content,
        Long userId,
        String todayEmotion) {
}
