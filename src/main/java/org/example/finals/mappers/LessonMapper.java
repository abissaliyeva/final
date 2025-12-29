package org.example.finals.mappers;

import org.example.finals.dto.lesson.LessonRequestDto;
import org.example.finals.dto.lesson.LessonResponseDto;
import org.example.finals.entities.Lesson;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    Lesson toEntity(LessonRequestDto dto);

    @Mapping(source = "subject.name", target = "subjectName")
    @Mapping(source = "teacher.username", target = "teacherName")
    LessonResponseDto toResponse(Lesson lesson);
}
