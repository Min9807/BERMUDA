package com.example.bermuda.repository;

import com.example.bermuda.domain.Diary;
import com.example.bermuda.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    public Optional<User> findByUserId(String userId);
    public Optional<User> findByUserNameAndEmail(String userName, String email);
    Optional<User> findByUserIdAndEmailAndUserName(String userId, String email, String username);
    public boolean existsByRefreshToken(String refreshToken);
    public boolean existsByUserId(String userId);
    public boolean existsByUserNameAndEmail(String userName, String email);
    public boolean existsByUserIdAndEmail(String userId, String email);
    public boolean existsByNickName(String nickName);

    public Optional<Void> deleteByUserId(String userId);
}
