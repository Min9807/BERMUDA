package com.example.bermuda.utils;

import com.example.bermuda.domain.User;
import com.example.bermuda.dto.MusicDto;
import com.example.bermuda.dto.ProfileImageDto;
import com.example.bermuda.exception.exceptions.CExtensionException;
import com.example.bermuda.exception.exceptions.CFileNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileHandler {

    public ProfileImageDto.RepProfileImageDto uploadImage(User user, MultipartFile profileImageFile) throws IOException {
        if(profileImageFile.isEmpty()) {
            log.info("비었나?");
            throw new CFileNotFoundException();
        }

        //경로 이름 설정
        String path = BasicInfo.path + user.getUserId() + "/profile";
        File file = new File(path);

        log.info(path);

        if(!file.exists())
            file.mkdirs();

        //콘텐츠 타입을 가져옴.
        String contentType = profileImageFile.getContentType();
        String originalFileExtension;

        //콘텐츠 타입이 이미지일 경우에만 서버에 저장함.
        if(contentType.contains("image")){
            originalFileExtension = "." + contentType.split("/")[1];
        }else
            throw new CExtensionException();


        String new_file_name = "profile" + originalFileExtension;

        file = new File(path + File.separator + new_file_name);
        profileImageFile.transferTo(file);

        log.info(file.getPath());

        return ProfileImageDto.RepProfileImageDto.builder()
                .originalFileName(profileImageFile.getOriginalFilename())
                .storedFilePath(path + "/" + new_file_name)
                .fileSize(profileImageFile.getSize())
                .userId(user.getUserId())
                .build();
    }

    public byte[] getProfileImageByte(ProfileImageDto.RepProfileImageDto repProfileImageDto) throws IOException{
        File file = new File(repProfileImageDto.getStoredFilePath());
        if(!file.exists()){
            log.info("file doesn't exist");
            throw new CFileNotFoundException();
        }
        return Files.readAllBytes(file.toPath());
    }

    public MusicDto.RepMusicDto uploadMusic(User user, MultipartFile uploadMusic, String songName, String mood) throws IOException {
        if(uploadMusic.isEmpty()) {
            log.info("비었나?");
            throw new CFileNotFoundException();
        }

        //경로 이름 설정
        String path = BasicInfo.path + user.getUserId() + "/music";
        File file = new File(path);

        log.info(path);

        if(!file.exists())
            file.mkdirs();

        //콘텐츠 타입을 가져옴.
        String contentType = uploadMusic.getContentType();

        //콘텐츠 타입이 이미지일 경우에만 서버에 저장함.
        if(contentType.contains("audio")){
            file = new File(path + File.separator + uploadMusic.getOriginalFilename());
            uploadMusic.transferTo(file);
        }else
            throw new CExtensionException();

        log.info(file.getPath());
        return MusicDto.RepMusicDto.builder()
                .originalFileName(songName)
                .mood(mood)
                .storedFilePath(path + "/" + uploadMusic.getOriginalFilename())
                .fileSize(uploadMusic.getSize())
                .user(user)
                .build();
    }
}
