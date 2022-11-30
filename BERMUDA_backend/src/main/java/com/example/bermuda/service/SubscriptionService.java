package com.example.bermuda.service;

import com.example.bermuda.domain.User;
import com.example.bermuda.exception.exceptions.CUserNotFoundException;
import com.example.bermuda.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class SubscriptionService {

    private final UserRepository userRepository;

    @Transactional
    public void checkSubscription(){

        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUserId(userId).orElseThrow(CUserNotFoundException::new);
        user.setSubscription(!user.getSubscription());

    }
}
