package com.example.bermuda.repository;

import com.example.bermuda.domain.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findByIdAndExpirationDateAfterAndExpired(String confirmationTokenId, LocalDateTime now, boolean expired);
}
