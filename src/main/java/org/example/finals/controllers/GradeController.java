package org.example.finals.controllers;

import lombok.RequiredArgsConstructor;
import org.example.finals.dto.grade.GradeRequestDto;
import org.example.finals.dto.grade.GradeResponseDto;
import org.example.finals.services.GradeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public GradeResponseDto assign(@RequestBody GradeRequestDto dto) {
        return gradeService.assign(dto);
    }

    @GetMapping("/student/{id}")
    @PreAuthorize("hasAnyRole('STUDENT','ADMIN')")
    public List<GradeResponseDto> getByStudent(@PathVariable Long id) {
        return gradeService.getByStudent(id);
    }
}
