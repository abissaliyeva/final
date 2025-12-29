package org.example.finals.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.finals.dto.user.UserRequestDto;
import org.example.finals.dto.user.UserResponseDto;
import org.example.finals.dto.user.UserUpdateDto;
import org.example.finals.entities.House;
import org.example.finals.entities.User;
import org.example.finals.enums.Role;
import org.example.finals.mappers.UserMapper;
import org.example.finals.repositories.HouseRepository;
import org.example.finals.repositories.UserRepository;
import org.example.finals.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final HouseRepository houseRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(UserRequestDto dto) {
        User user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.STUDENT);
        user.setCreatedAt(LocalDateTime.now());

        if (dto.getHouseId() != null) {
            House house = houseRepository.findById(dto.getHouseId())
                    .orElseThrow(() -> new RuntimeException("House not found"));
            user.setHouse(house);
        }

        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public UserResponseDto createByAdmin(UserRequestDto dto) {
        User user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.TEACHER);
        user.setCreatedAt(LocalDateTime.now());
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public void blockUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setEnabled(false);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public UserResponseDto getProfile(String username) {
        return userMapper.toResponse(
                userRepository.findByUsername(username).orElseThrow()
        );
    }

    @Override
    public UserResponseDto updateProfile(String username, UserUpdateDto dto) {
        User user = userRepository.findByUsername(username).orElseThrow();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        return userMapper.toResponse(userRepository.save(user));
    }
    @Override
    public boolean changePassword(Long userId, String oldPass, String newPass) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null && user.getPassword().equals(oldPass)) {
            user.setPassword(newPass);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
