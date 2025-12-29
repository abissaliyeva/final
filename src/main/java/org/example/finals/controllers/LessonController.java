package org.example.finals.controllers;

import lombok.RequiredArgsConstructor;
import org.example.finals.dto.lesson.LessonRequestDto;
import org.example.finals.dto.lesson.LessonResponseDto;
import org.example.finals.services.LessonService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Collections;

@RestController
@RequestMapping("/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public LessonResponseDto create(@RequestBody LessonRequestDto dto) {
        return lessonService.create(dto);
    }

    @GetMapping("/subject/{id}")
    public List<LessonResponseDto> getBySubject(@PathVariable Long id) {
        return lessonService.getBySubject(id);
    }
}
