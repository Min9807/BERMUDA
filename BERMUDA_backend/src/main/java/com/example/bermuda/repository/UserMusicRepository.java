package com.example.bermuda.repository;

import com.example.bermuda.domain.User;
import com.example.bermuda.domain.UserDiary;
import com.example.bermuda.domain.UserMusic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserMusicRepository extends JpaRepository<UserMusic, Long> {
    public Optional<List<UserMusic>> findAllByUser(User user);

}
