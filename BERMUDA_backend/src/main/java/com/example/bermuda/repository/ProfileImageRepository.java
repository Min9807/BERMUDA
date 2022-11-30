package com.example.bermuda.repository;

import com.example.bermuda.domain.ProfileImage;
import com.example.bermuda.domain.User;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {
    //BindResult<Object> findByUserId(String loginId);

    public boolean existsByUser(User user);

    public Optional<ProfileImage> findByUser(User user);
}
