package com.example.bermuda.repository;


import com.example.bermuda.domain.Diary;
import com.example.bermuda.domain.User;
import com.example.bermuda.domain.UserDiary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDiaryRepository extends JpaRepository<UserDiary, Long> {

    public Optional<List<UserDiary>> findAllByUser(User user);

    public Optional<UserDiary> findByDiary(Diary diary);

    public boolean existsByDiary(Diary diary);
    public boolean existsByUserAndDiary(User user, Diary diary);

    public Optional<Void> deleteByUserAndDiary(User user, Diary diary);
}
