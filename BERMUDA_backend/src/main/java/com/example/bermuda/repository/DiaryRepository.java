package com.example.bermuda.repository;

import com.example.bermuda.domain.Diary;
import com.example.bermuda.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    public Optional<Diary> findByDiaryId(Long diaryId);
    public Optional<List<Diary>> findByUser(User user, Pageable pageable);

    public Optional<Boolean> deleteByDiaryId(Long diaryId);
}
