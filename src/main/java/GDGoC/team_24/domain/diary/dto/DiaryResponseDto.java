package GDGoC.team_24.domain.diary.dto;

import GDGoC.team_24.domain.diary.domain.Diary;

import GDGoC.team_24.domain.diaryPhoto.domain.DiaryPhoto;
import GDGoC.team_24.domain.user.domain.User;



import java.util.List;
import java.util.stream.Collectors;

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


}
