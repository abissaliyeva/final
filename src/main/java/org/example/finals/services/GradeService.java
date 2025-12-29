package org.example.finals.services;

import org.example.finals.dto.grade.GradeRequestDto;
import org.example.finals.dto.grade.GradeResponseDto;

import java.util.List;

public interface GradeService {

    GradeResponseDto create(GradeRequestDto dto);

    GradeResponseDto assign(GradeRequestDto dto);

    List<GradeResponseDto> getByStudent(Long studentId);

    void delete(Long id);
}
