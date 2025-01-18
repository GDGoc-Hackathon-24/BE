package GDGoC.team_24.domain.diary.dto;

import GDGoC.team_24.domain.diary.domain.Diary;
import lombok.Builder;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder

public record DiaryRequestDto(
        LocalDateTime date,
        String content,
        Long userId,
        String todayEmotion) {


    public static DiaryRequestDto from(Diary diary) {
        return DiaryRequestDto.builder()
                .date(diary.getDate())
                .content(diary.getContent())
                .userId(diary.getUser().getId())
                .todayEmotion(diary.getTodayEmtion().toString())
                .build();
    }
}
