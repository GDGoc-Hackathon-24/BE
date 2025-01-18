package GDGoC.team_24.domain.diary.service;

import GDGoC.team_24.domain.diary.domain.Diary;
import GDGoC.team_24.domain.diary.dto.DiaryResponseDto;
import GDGoC.team_24.domain.diaryPhoto.domain.DiaryPhoto;
import GDGoC.team_24.domain.diary.domain.TODAYEMTION;
import GDGoC.team_24.domain.diary.dto.DiaryRequestDto;
import GDGoC.team_24.domain.diary.repository.DiaryRepository;
import GDGoC.team_24.domain.diaryPhoto.repository.DiaryPhotoRepository;
import GDGoC.team_24.domain.user.domain.User;
import GDGoC.team_24.domain.user.repository.UserRepository;
import GDGoC.team_24.global.code.status.ErrorStatus;
import GDGoC.team_24.global.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import GDGoC.team_24.global.aws.AwsS3Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final DiaryPhotoRepository diaryPhotoRepository;
    private final UserRepository userRepository;
    private final AwsS3Service awsS3Service;

    public DiaryResponseDto createDiary(DiaryRequestDto userRequestDto, List<MultipartFile> imgs) {

        User user = userRepository.findById(userRequestDto.userId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        TODAYEMTION todayemtion = TODAYEMTION.valueOf(userRequestDto.todayEmotion().toUpperCase());

        // Diary 객체 생성
        Diary diary = Diary.builder()
                .content(userRequestDto.content())
                .date(userRequestDto.date())
                .todayEmtion(todayemtion)
                .user(user)
                .build();

        diaryRepository.save(diary);

        // Diary에 포함될 사진들 저장
        List<DiaryPhoto> diaryPhotos = new ArrayList<>();
        for (MultipartFile img : imgs) {
            String imgUrl = awsS3Service.uploadFile(img);

            DiaryPhoto photo = DiaryPhoto.builder()
                    .diary(diary)
                    .photoUrl(imgUrl)
                    .build();

            diaryPhotoRepository.save(photo);
            diaryPhotos.add(photo);
        }

        // Diary와 관련된 사진들을 포함하는 DTO 반환
        return new DiaryResponseDto(diary, diaryPhotos);
    }


    public DiaryResponseDto getDiary(Long diaryId) {

        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._BAD_REQUEST));

        List<DiaryPhoto> diaryPhotos = diaryPhotoRepository.findByDiary(diary);

        return new DiaryResponseDto(diary, diaryPhotos);
    }

    public DiaryResponseDto updateDiary(Long diaryId, DiaryRequestDto userRequestDto, List<MultipartFile> imgs) {

        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._BAD_REQUEST));


        Diary updateDiary = Diary.builder()
                .date(userRequestDto.date())
                .content(userRequestDto.content())
                .todayEmtion(diary.getTodayEmtion())
                .todayEmtion(diary.getTodayEmtion())
                .user(diary.getUser())
                .build();

        diaryRepository.save(updateDiary);


        List<DiaryPhoto> existingPhotos = diaryPhotoRepository.findByDiary(diary);
        for (DiaryPhoto photo : existingPhotos) {
            awsS3Service.deleteFile(photo.getPhotoUrl());
            diaryPhotoRepository.delete(photo);
        }

        List<DiaryPhoto> diaryPhotos = new ArrayList<>();

        for (MultipartFile img : imgs) {
            String imgUrl = awsS3Service.uploadFile(img);
            DiaryPhoto newPhoto = DiaryPhoto.builder()
                    .diary(updateDiary)
                    .photoUrl(imgUrl)
                    .build();
            diaryPhotoRepository.save(newPhoto);
            diaryPhotos.add(newPhoto);
        }

        return new DiaryResponseDto(updateDiary, diaryPhotos);

    }

    public void deleteDiary(Long diaryId) {

        diaryRepository.deleteById(diaryId);
    }
    }

