package GDGoC.team_24.domain.diary.dto;

import GDGoC.team_24.domain.diary.domain.Diary;

import GDGoC.team_24.domain.diaryPhoto.domain.DiaryPhoto;
import GDGoC.team_24.domain.user.domain.User;
import lombok.Builder;


import java.util.List;
import java.util.stream.Collectors;

@Builder
public record DiaryResponseDto(
        Long id,
        Long userId,
        String content,
        String date,
        String todayEmotion,
        List<String> photoUrls
) {

    public DiaryResponseDto(Diary diary, List<DiaryPhoto> diaryPhotos) {
        this(
                diary.getId(),
                diary.getUser().getId(),
                diary.getContent(),
                diary.getDate().toString(),
                diary.getTodayEmtion().toString(),
                diaryPhotos.stream().map(DiaryPhoto::getPhotoUrl).collect(Collectors.toList())
        );
    }



    public static DiaryResponseDto from(Diary diary) {
        return DiaryResponseDto.builder()
                .id(diary.getId())
                .date(diary.getDate().toString())
                .content(diary.getContent())
                .userId(diary.getUser().getId())
                .todayEmotion(diary.getTodayEmtion().toString())
                .build();
    }
}
