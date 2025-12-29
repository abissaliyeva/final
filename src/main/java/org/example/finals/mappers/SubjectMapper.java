package org.example.finals.mappers;

import org.example.finals.dto.subject.SubjectRequestDto;
import org.example.finals.dto.subject.SubjectResponseDto;
import org.example.finals.entities.Subject;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    @Mapping(target = "teacher", ignore = true)
    Subject toEntity(SubjectRequestDto dto);

    @Mapping(source = "teacher.username", target = "teacherName")
    SubjectResponseDto toResponse(Subject subject);
}
