package org.example.finals.controllers;

import lombok.RequiredArgsConstructor;
import org.example.finals.dto.subject.SubjectRequestDto;
import org.example.finals.dto.subject.SubjectResponseDto;
import org.example.finals.services.SubjectService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public SubjectResponseDto create(@RequestBody SubjectRequestDto dto) {
        return subjectService.create(dto);
    }

    @GetMapping
    public List<SubjectResponseDto> getAll() {
        return subjectService.getAll();
    }
}
