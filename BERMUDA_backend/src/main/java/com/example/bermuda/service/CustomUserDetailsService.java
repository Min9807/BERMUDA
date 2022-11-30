package com.example.bermuda.service;

import com.example.bermuda.exception.exceptions.CUserNotFoundException;
import com.example.bermuda.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUserId(username).orElseThrow(CUserNotFoundException::new);
    }
}
