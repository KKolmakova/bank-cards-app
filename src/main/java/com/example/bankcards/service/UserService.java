package com.example.bankcards.service;

import com.example.bankcards.dto.UserDTO;
import com.example.bankcards.entity.User;
import com.example.bankcards.exception.UserException;
import com.example.bankcards.exception.code.UserErrorCode;
import com.example.bankcards.repository.UserRepository;
import com.example.bankcards.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDTO createUser(User user) {
        if (userRepository.existsByName(user.getName())) {
            throw new UserException(UserErrorCode.USER_NAME_OCCUPIED, user.getName());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return mapperUtil.mapToUserDTO(userRepository.save(user), List.of());
    }

    @Transactional
    public String deleteUser(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserException(UserErrorCode.USER_NOT_FOUND_BY_ID, userId.toString());
        }
        userRepository.deleteById(userId);
        return "User successfully deleted.";
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> mapperUtil.mapToUserDTO(user, List.of()))
                .toList();
    }
}

