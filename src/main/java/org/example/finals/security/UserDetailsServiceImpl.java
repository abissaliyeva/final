//package org.example.finals.security;
//
//import lombok.RequiredArgsConstructor;
//import org.example.finals.entities.User;
//import org.example.finals.enums.Role;
//import org.example.finals.repositories.UserRepository;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.*;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//
//@Service
//@RequiredArgsConstructor
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                user.isEnabled(),
//                true,
//                true,
//                true,
//                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
//        );
//    }
//}
