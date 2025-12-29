package org.example.finals.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.finals.dto.subject.SubjectRequestDto;
import org.example.finals.dto.subject.SubjectResponseDto;
import org.example.finals.entities.Subject;
import org.example.finals.entities.User;
import org.example.finals.repositories.SubjectRepository;
import org.example.finals.repositories.UserRepository;
import org.example.finals.services.SubjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    @Override
    public SubjectResponseDto create(SubjectRequestDto dto) {

        User teacher = userRepository.findById((long) dto.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Subject subject = Subject.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .teacher(teacher)
                .build();

        Subject savedSubject = subjectRepository.save(subject);

        return mapToResponse(savedSubject);
    }

    @Override
    public SubjectResponseDto getById(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        return mapToResponse(subject);
    }

    @Override
    public List<SubjectResponseDto> getAll() {
        return subjectRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (!subjectRepository.existsById(id)) {
            throw new RuntimeException("Subject not found");
        }
        subjectRepository.deleteById(id);
    }

    /* =======================
       Mapping method
       ======================= */
    private SubjectResponseDto mapToResponse(Subject subject) {
        return SubjectResponseDto.builder()
                .id(subject.getId().intValue())
                .name(subject.getName())
                .description(subject.getDescription())
                .teacherName(
                        subject.getTeacher().getUsername()
                )
                .build();
    }
}
