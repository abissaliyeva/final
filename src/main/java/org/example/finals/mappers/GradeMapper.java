package org.example.finals.mappers;

import org.example.finals.dto.grade.GradeRequestDto;
import org.example.finals.dto.grade.GradeResponseDto;
import org.example.finals.entities.Grade;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface GradeMapper {

    @Mapping(target = "student", ignore = true)
    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "lesson", ignore = true)
    Grade toEntity(GradeRequestDto dto);

    @Mapping(source = "student.username", target = "studentName")
    @Mapping(source = "subject.name", target = "subjectName")
    @Mapping(source = "lesson.topic", target = "lessonTopic")
    GradeResponseDto toResponse(Grade grade);
}
