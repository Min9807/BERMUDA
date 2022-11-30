package com.example.bermuda.repository;

import com.example.bermuda.domain.User;
import com.example.bermuda.domain.UserDiary;
import com.example.bermuda.domain.UserVoiceActor;
import com.example.bermuda.domain.VoiceActor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserVoiceActorRepository extends JpaRepository<UserVoiceActor, Long> {
    public Optional<List<UserVoiceActor>> findAllByUser(User user);
}
