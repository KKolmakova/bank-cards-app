package com.example.bankcards.security;

import com.example.bankcards.entity.User;
import com.example.bankcards.exception.UserException;
import com.example.bankcards.exception.code.UserErrorCode;
import com.example.bankcards.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND, ""));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .roles(user.getRole().getName())
                .disabled(!user.getEnabled())
                .build();
    }

    public User loadUserEntityByUsername(String username) {
        return userRepository.findByName(username)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND, ""));
    }
}