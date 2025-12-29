package com.example.finals;

import com.example.finals.DTO.lesson.LessonRequestDto;
import com.example.finals.DTO.lesson.LessonResponseDto;
import com.example.finals.entities.Subject;
import com.example.finals.entities.User;
import com.example.finals.repositories.LessonRepository;
import com.example.finals.repositories.SubjectRepository;
import com.example.finals.repositories.UserRepository;
import com.example.finals.services.impl.LessonServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class LessonServiceImplTest {

    @Autowired
    private LessonServiceImpl lessonService;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void createLessonTest() {
        Subject subject = subjectRepository.findAll().get(0);
        User teacher = userRepository.findAll().get(0);

        LessonRequestDto dto = LessonRequestDto.builder()
                .topic("Introduction to Java")
                .subjectId(subject.getId().intValue())
                .teacherId(teacher.getId().intValue())
                .dateTime(LocalDateTime.now())
                .classroom("Room 101")
                .build();

        LessonResponseDto response = lessonService.create(dto);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getId());
        Assertions.assertEquals("Introduction to Java", response.getTopic());
        Assertions.assertEquals(subject.getName(), response.getSubjectName());
        Assertions.assertEquals(teacher.getUsername(), response.getTeacherName());
        Assertions.assertEquals("Room 101", response.getClassroom());
    }

    @Test
    void getAllLessonsTest() {
        List<LessonResponseDto> lessons = lessonService.getAll();

        Assertions.assertNotNull(lessons);
        Assertions.assertNotEquals(0, lessons.size());

        for (LessonResponseDto dto : lessons) {
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getTopic());
            Assertions.assertNotNull(dto.getSubjectName());
            Assertions.assertNotNull(dto.getTeacherName());
        }
    }

    @Test
    void getBySubjectTest() {
        Subject subject = subjectRepository.findAll().get(0);

        List<LessonResponseDto> lessons = lessonService.getBySubject(subject.getId());

        Assertions.assertNotNull(lessons);
        for (LessonResponseDto dto : lessons) {
            Assertions.assertEquals(subject.getName(), dto.getSubjectName());
        }
    }

    @Test
    void deleteLessonTest() {
        LessonResponseDto lesson = lessonService.getAll().get(0);
        Long lessonId = (long) lesson.getId();

        lessonService.delete(lessonId);

        boolean exists = lessonRepository.existsById(lessonId);
        Assertions.assertFalse(exists);
    }
}

