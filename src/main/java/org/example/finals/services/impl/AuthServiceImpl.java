//package org.example.finals.services.impl;
//
//import lombok.RequiredArgsConstructor;
//import org.example.finals.dto.auth.AuthResponseDto;
//import org.example.finals.dto.auth.ChangePasswordDto;
//import org.example.finals.dto.auth.LoginRequestDto;
//import org.example.finals.entities.User;
//import org.example.finals.repositories.UserRepository;
//import org.example.finals.security.JwtService;
//import org.example.finals.services.AuthService;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class AuthServiceImpl implements AuthService {
//
//    private final AuthenticationManager authenticationManager;
//    private final UserRepository userRepository;
//    private final JwtService jwtService;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public AuthResponseDto login(LoginRequestDto dto) {
//
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        dto.getUsername(),
//                        dto.getPassword()
//                )
//        );
//
//        User user = userRepository.findByUsername(dto.getUsername())
//                .orElseThrow();
//
//        String token = jwtService.generateToken(user);
//
//        return new AuthResponseDto(
//                token,
//                user.getUsername(),
//                user.getRole().name()
//        );
//    }
//
//    @Override
//    public void changePassword(ChangePasswordDto dto) {
//
//        User user = userRepository.findByUsername(
//                org.springframework.security.core.context.SecurityContextHolder
//                        .getContext()
//                        .getAuthentication()
//                        .getName()
//        ).orElseThrow();
//
//        if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
//            throw new RuntimeException("Old password is incorrect");
//        }
//
//        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
//        userRepository.save(user);
//    }
//}
