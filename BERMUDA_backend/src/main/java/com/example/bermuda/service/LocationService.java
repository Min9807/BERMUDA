package com.example.bermuda.service;

import com.example.bermuda.converter.DiaryDtoConverter;
import com.example.bermuda.converter.ProfileImageDtoConverter;
import com.example.bermuda.domain.Diary;
import com.example.bermuda.domain.User;
import com.example.bermuda.domain.UserDiary;
import com.example.bermuda.dto.DiaryDto;
import com.example.bermuda.dto.LocationDto;
import com.example.bermuda.exception.exceptions.CUserDiaryNotFoundException;
import com.example.bermuda.exception.exceptions.CUserNotFoundException;
import com.example.bermuda.repository.ProfileImageRepository;
import com.example.bermuda.repository.UserDiaryRepository;
import com.example.bermuda.repository.UserRepository;
import com.example.bermuda.utils.CardinalDirection;
import com.example.bermuda.utils.FileHandler;
import com.example.bermuda.utils.GeometryUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class LocationService {

    private final EntityManager entityManager;
    private final UserDiaryRepository userDiaryRepository;
    private final UserRepository userRepository;
    private final ProfileImageRepository profileImageRepository;
    private final FileHandler fileHandler;

    @Transactional
    public List<DiaryDto.DiaryPreviewDto> getLocationDtoByLatitudeAndLongitude(Double latitude, Double longitude, String loginId) throws IOException {

        double distance = 3;

        // 북동쪽 좌표 구하기
        LocationDto.LocationCoordinateDto northEast = GeometryUtils.calculateByDirection(latitude, longitude, distance,
                CardinalDirection.NORTHEAST
                        .getBearing());
        LocationDto.LocationCoordinateDto southWest = GeometryUtils.calculateByDirection(latitude, longitude, distance,
                CardinalDirection.SOUTHWEST
                        .getBearing());

        double x1 = northEast.getLongitude();
        double y1 = northEast.getLatitude();
        double x2 = southWest.getLongitude();
        double y2 = southWest.getLatitude();


        String pointFormat = String.format("'LINESTRING(%f %f, %f %f)')", x1, y1, x2, y2);
        // native query 활용
        Query query = entityManager.createNativeQuery("" +
                "SELECT d.diary_id, d.content, d.like_num, d.open, d.write_date, d.user_no, d.music_id, d.location_id\n" +
                "FROM diary AS d \n" +
                "WHERE d.location_id " +
                "IN (" +
                "SELECT l.location_id \n" +
                "FROM location AS l \n" +
                "WHERE MBRContains(ST_LINESTRINGFROMTEXT(" + pointFormat + ", l.point)" +
                ")", Diary.class)
                .setMaxResults(10);

        List<Diary> diaryList = query.getResultList();

        List<DiaryDto.DiaryPreviewDto> diaryPreviewDtoList = new ArrayList<>();

        for (Diary diary : diaryList) {
            Boolean liked = false;

            if(userDiaryRepository.existsByDiary(diary)){
                UserDiary userDiary = userDiaryRepository.findByDiary(diary).orElseThrow(CUserNotFoundException::new);
                User user = userRepository.findByUserId(loginId).orElseThrow(CUserNotFoundException::new);
                if(userDiary.getUser().equals(user))
                    liked = true;
            }
            diaryPreviewDtoList.add(DiaryDtoConverter.toDiaryPreviewDto(diary, diary.getUser().getUserId(), liked));
            log.info(diary.getContent());
        }

        return diaryPreviewDtoList;
    }
}
