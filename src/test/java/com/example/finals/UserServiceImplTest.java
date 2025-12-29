package com.example.finals;

import com.example.finals.DTO.user.UserRequestDto;
import com.example.finals.DTO.user.UserResponseDto;
import com.example.finals.DTO.user.UserUpdateDto;
import com.example.finals.entities.House;
import com.example.finals.entities.User;
import com.example.finals.enums.Role;
import com.example.finals.repositories.HouseRepository;
import com.example.finals.repositories.UserRepository;
import com.example.finals.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HouseRepository houseRepository;

    @Test
    void registerTest() {
        House house = houseRepository.findAll().get(0);

        UserRequestDto dto = UserRequestDto.builder()
                .username("student_test")
                .email("student@test.com")
                .password("12345")
                .houseId(house.getId())
                .build();

        UserResponseDto response = userService.register(dto);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getId());
        Assertions.assertEquals("student_test", response.getUsername());
        Assertions.assertEquals(Role.STUDENT, response.getRole());
    }

    @Test
    void createByAdminTest() {
        UserRequestDto dto = UserRequestDto.builder()
                .username("teacher_test")
                .email("teacher@test.com")
                .password("12345")
                .build();

        UserResponseDto response = userService.createByAdmin(dto);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(Role.TEACHER, response.getRole());
    }

    @Test
    void blockUserTest() {
        User user = userRepository.findAll().get(0);

        userService.blockUser(user.getId());

        User blockedUser =
                userRepository.findById(user.getId()).orElseThrow();

        Assertions.assertFalse(blockedUser.isEnabled());
    }

    @Test
    void deleteUserTest() {
        User user = userRepository.findAll().get(0);

        userService.deleteUser(user.getId());

        Assertions.assertFalse(
                userRepository.findById(user.getId()).isPresent()
        );
    }

    @Test
    void getAllUsersTest() {
        List<UserResponseDto> users = userService.getAllUsers();

        Assertions.assertNotNull(users);
        Assertions.assertNotEquals(0, users.size());

        for (UserResponseDto dto : users) {
            Assertions.assertNotNull(dto);
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getUsername());
        }
    }

    @Test
    void getProfileTest() {
        User user = userRepository.findAll().get(0);

        UserResponseDto profile =
                userService.getProfile(user.getUsername());

        Assertions.assertNotNull(profile);
        Assertions.assertEquals(user.getUsername(), profile.getUsername());
    }

    @Test
    void updateProfileTest() {
        User user = userRepository.findAll().get(0);

        UserUpdateDto dto = UserUpdateDto.builder()
                .username("updated_username")
                .email("updated@email.com")
                .build();

        UserResponseDto updated =
                userService.updateProfile(user.getUsername(), dto);

        Assertions.assertEquals("updated_username", updated.getUsername());
        Assertions.assertEquals("updated@email.com", updated.getEmail());
    }

    @Test
    void changePasswordTest() {
        User user = userRepository.findAll().get(0);

        user.setPassword("oldPass");
        userRepository.save(user);

        boolean result =
                userService.changePassword(user.getId(), "oldPass", "newPass");

        Assertions.assertTrue(result);

        User updated =
                userRepository.findById(user.getId()).orElseThrow();

        Assertions.assertEquals("newPass", updated.getPassword());
    }
}

