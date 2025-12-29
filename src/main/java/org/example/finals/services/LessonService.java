package org.example.finals.services;

import org.example.finals.dto.lesson.LessonRequestDto;
import org.example.finals.dto.lesson.LessonResponseDto;

import java.util.List;

public interface LessonService {

    LessonResponseDto create(LessonRequestDto dto);

    List<LessonResponseDto> getBySubject(Long subjectId);

    List<LessonResponseDto> getAll();

    void delete(Long id);
}
