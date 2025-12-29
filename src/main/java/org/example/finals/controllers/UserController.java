package org.example.finals.controllers;

import lombok.RequiredArgsConstructor;
import org.example.finals.dto.user.UserUpdateDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.example.finals.dto.user.UserRequestDto;
import org.example.finals.dto.user.UserResponseDto;
import org.example.finals.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /* ---------- PUBLIC REGISTRATION ---------- */
    @PostMapping("/register")
    public UserResponseDto register(@RequestBody UserRequestDto request) {
        return userService.register(request);
    }

    /* ---------- ADMIN ---------- */
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public UserResponseDto createByAdmin(@RequestBody UserRequestDto request) {
        return userService.createByAdmin(request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    /* ---------- AUTHENTICATED USER ---------- */
    @PutMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public UserResponseDto updateOwnProfile(
            Authentication authentication,
            @RequestParam String fullName,
            UserUpdateDto request
    ) {
        return userService.updateProfile(fullName,request);
    }

//    public UserResponseDto updateProfile(String username, UserUpdateDto dto) {
//        User user = userRepository.findByUsername(username).orElseThrow();
//        user.setUsername(dto.getUsername());
//        user.setEmail(dto.getEmail());
//        return userMapper.toResponse(userRepository.save(user));
//    }

    @PutMapping("/change-password")
    @PreAuthorize("isAuthenticated()")
    public String changePassword(@PathVariable Long id,
                                 @RequestParam String oldPass,
                                 @RequestParam String newPass) {
        boolean success = userService.changePassword(id, oldPass, newPass);

        return success
                ? "Password changed successfully"
                : "Old password is incorrect";
    }
}
