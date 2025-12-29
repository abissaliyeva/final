package org.example.finals.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.finals.dto.lesson.LessonRequestDto;
import org.example.finals.dto.lesson.LessonResponseDto;
import org.example.finals.entities.Lesson;
import org.example.finals.entities.Subject;
import org.example.finals.entities.User;
import org.example.finals.repositories.LessonRepository;
import org.example.finals.repositories.SubjectRepository;
import org.example.finals.repositories.UserRepository;
import org.example.finals.services.LessonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    @Override
    public LessonResponseDto create(LessonRequestDto dto) {

        Subject subject = subjectRepository.findById((long) dto.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        User teacher = userRepository.findById((long) dto.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Lesson lesson = Lesson.builder()
                .topic(dto.getTopic())
                .subject(subject)
                .teacher(teacher)
                .dateTime(dto.getDateTime())
                .classroom(dto.getClassroom())
                .build();

        Lesson savedLesson = lessonRepository.save(lesson);

        return mapToResponse(savedLesson);
    }

    @Override
    public List<LessonResponseDto> getBySubject(Long subjectId) {

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        return lessonRepository.findBySubject(subject)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<LessonResponseDto> getAll() {
        return lessonRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (!lessonRepository.existsById(id)) {
            throw new RuntimeException("Lesson not found");
        }
        lessonRepository.deleteById(id);
    }

    /* =======================
       Mapping method
       ======================= */
    private LessonResponseDto mapToResponse(Lesson lesson) {
        return LessonResponseDto.builder()
                .id(lesson.getId().intValue())
                .topic(lesson.getTopic())
                .subjectName(lesson.getSubject().getName())
                .teacherName(
                        lesson.getTeacher().getUsername()
                )
                .dateTime(lesson.getDateTime())
                .classroom(lesson.getClassroom())
                .build();
    }
}
