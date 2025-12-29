package com.example.finals;

import com.example.finals.services.impl.GradeServiceImpl;
import com.example.finals.repositories.*;
import com.example.finals.DTO.grade.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class GradeServiceImplTest {

    @Autowired
    private GradeServiceImpl gradeService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private GradeRepository gradeRepository;

    // 🔹 Создание оценки
    @Test
    void createGradeTest() {
        GradeRequestDto dto = GradeRequestDto.builder()
                .studentId(1)
                .subjectId(1)
                .lessonId(1)
                .value(String.valueOf(95))
                .build();

        GradeResponseDto response = gradeService.create(dto);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getId());
        Assertions.assertEquals("95", response.getValue());
        Assertions.assertNotNull(response.getStudentName());
        Assertions.assertNotNull(response.getSubjectName());
        Assertions.assertNotNull(response.getLessonTopic());
    }

    @Test
    void getByStudentTest() {
        Long studentId = 1L;

        List<GradeResponseDto> grades = gradeService.getByStudent(studentId);

        Assertions.assertNotNull(grades);
        Assertions.assertNotEquals(0, grades.size());

        for (GradeResponseDto dto : grades) {
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getStudentName());
            Assertions.assertNotNull(dto.getSubjectName());
            Assertions.assertNotNull(dto.getLessonTopic());
        }
    }

    @Test
    void deleteGradeTest() {
        GradeRequestDto dto = GradeRequestDto.builder()
                .studentId(1)
                .subjectId(1)
                .lessonId(1)
                .value(String.valueOf(80))
                .build();

        GradeResponseDto response = gradeService.create(dto);
        Long gradeId = (long) response.getId();

        gradeService.delete(gradeId);

        Assertions.assertFalse(gradeRepository.existsById(gradeId));
    }
}

