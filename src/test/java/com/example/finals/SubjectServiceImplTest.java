package com.example.finals;

import com.example.finals.DTO.subject.SubjectRequestDto;
import com.example.finals.DTO.subject.SubjectResponseDto;
import com.example.finals.repositories.SubjectRepository;
import com.example.finals.entities.User;
import com.example.finals.repositories.UserRepository;
import com.example.finals.services.impl.SubjectServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SubjectServiceImplTest {

    @Autowired
    private SubjectServiceImpl subjectService;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void createSubjectTest() {
        User teacher = userRepository.findAll().get(0);

        SubjectRequestDto dto = SubjectRequestDto.builder()
                .name("Mathematics")
                .description("Math course")
                .teacherId(Math.toIntExact(teacher.getId()))
                .build();

        SubjectResponseDto response = subjectService.create(dto);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getId());
        Assertions.assertEquals("Mathematics", response.getName());
        Assertions.assertEquals("Math course", response.getDescription());
        Assertions.assertEquals(teacher.getUsername(), response.getTeacherName());
    }

    @Test
    void getByIdTest() {
        SubjectResponseDto created = subjectService.getAll().get(0);

        SubjectResponseDto response = subjectService.getById((long) created.getId());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(created.getId(), response.getId());
        Assertions.assertEquals(created.getName(), response.getName());
    }

    @Test
    void getAllTest() {
        List<SubjectResponseDto> subjects = subjectService.getAll();

        Assertions.assertNotNull(subjects);
        Assertions.assertNotEquals(0, subjects.size());

        for (SubjectResponseDto dto : subjects) {
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getName());
            Assertions.assertNotNull(dto.getTeacherName());
        }
    }

    @Test
    void deleteSubjectTest() {
        SubjectResponseDto created = subjectService.getAll().get(0);
        Long subjectId = (long) created.getId();

        subjectService.delete(subjectId);

        boolean exists = subjectRepository.existsById(subjectId);
        Assertions.assertFalse(exists);
    }
}

