package org.example.finals.services;


import org.example.finals.dto.user.*;

import java.util.List;

public interface UserService {

    UserResponseDto register(UserRequestDto dto);

    UserResponseDto createByAdmin(UserRequestDto dto);

    UserResponseDto getProfile(String username);

    UserResponseDto updateProfile(String username, UserUpdateDto dto);

    void blockUser(Long userId);

    void deleteUser(Long userId);

    List<UserResponseDto> getAllUsers();
    boolean changePassword(Long userId, String oldPass, String newPass);
}
