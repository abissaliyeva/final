package org.example.finals.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.finals.dto.grade.GradeRequestDto;
import org.example.finals.dto.grade.GradeResponseDto;
import org.example.finals.entities.Grade;
import org.example.finals.entities.Lesson;
import org.example.finals.entities.Subject;
import org.example.finals.entities.User;
import org.example.finals.repositories.GradeRepository;
import org.example.finals.repositories.LessonRepository;
import org.example.finals.repositories.SubjectRepository;
import org.example.finals.repositories.UserRepository;
import org.example.finals.services.GradeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;
    private final LessonRepository lessonRepository;

    @Override
    public GradeResponseDto create(GradeRequestDto dto) {

        User student = userRepository.findById((long) dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Subject subject = subjectRepository.findById((long) dto.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        Lesson lesson = lessonRepository.findById((long) dto.getLessonId())
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        Grade grade = Grade.builder()
                .student(student)
                .subject(subject)
                .lesson(lesson)
                .value(dto.getValue())
                .build();

        Grade savedGrade = gradeRepository.save(grade);

        return mapToResponse(savedGrade);
    }

    public GradeResponseDto assign(GradeRequestDto dto) {

        User student = userRepository.findById((long) dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Subject subject = subjectRepository.findById((long) dto.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        Lesson lesson = lessonRepository.findById((long) dto.getLessonId())
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        Grade grade = Grade.builder()
                .student(student)
                .subject(subject)
                .lesson(lesson)
                .value(dto.getValue())
                .build();

        Grade savedGrade = gradeRepository.save(grade);

        return mapToResponse(savedGrade);
    }

    @Override
    public List<GradeResponseDto> getByStudent(Long studentId) {

        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return gradeRepository.findByStudent(student)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (!gradeRepository.existsById(id)) {
            throw new RuntimeException("Grade not found");
        }
        gradeRepository.deleteById(id);
    }

    /* =======================
       Mapping method
       ======================= */
    private GradeResponseDto mapToResponse(Grade grade) {
        return GradeResponseDto.builder()
                .id(grade.getId().intValue())
                .studentName(
                        grade.getStudent().getUsername()
                )
                .subjectName(grade.getSubject().getName())
                .lessonTopic(grade.getLesson().getTopic())
                .value(grade.getValue())
                .build();
    }
}
