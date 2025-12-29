package org.example.finals.services;

import org.example.finals.dto.subject.SubjectRequestDto;
import org.example.finals.dto.subject.SubjectResponseDto;

import java.util.List;

public interface SubjectService {

    SubjectResponseDto create(SubjectRequestDto dto);

    SubjectResponseDto getById(Long id);

    List<SubjectResponseDto> getAll();

    void delete(Long id);
}
