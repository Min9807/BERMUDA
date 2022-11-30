
package com.example.bermuda.repository;

import com.example.bermuda.domain.Music;

import com.example.bermuda.dto.GetAnalysisDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MusicRepository extends JpaRepository<Music, Long> {

    public Page<Music> findAll(Pageable pageable);
    public Page<Music> findByMood(String mood, Pageable pageable);
    public Optional<Music> findByMusicId(Long musicId);
    @Query(value = "SELECT * FROM music order by RAND() limit 1",nativeQuery = true)
    public Music findByMood(String mood);
}
