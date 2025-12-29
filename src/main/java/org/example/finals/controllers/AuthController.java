//package org.example.finals.controllers;
//
//import lombok.RequiredArgsConstructor;
//import org.example.finals.dto.auth.AuthResponseDto;
//import org.example.finals.dto.auth.ChangePasswordDto;
//import org.example.finals.dto.auth.LoginRequestDto;
//import org.example.finals.dto.user.UserRequestDto;
//import org.example.finals.dto.user.UserResponseDto;
//import org.example.finals.services.UserService;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//@RequiredArgsConstructor
//public class AuthController {
//
//    private final UserService userService;
//
//    @PostMapping("/register")
//    public UserResponseDto register(@RequestBody UserRequestDto dto) {
//        return userService.register(dto);
//    }
//
//    @PutMapping("/change-password")
//    public void changePassword(@RequestBody ChangePasswordDto dto) {
//        authService.changePassword(dto);
//    }
//}
