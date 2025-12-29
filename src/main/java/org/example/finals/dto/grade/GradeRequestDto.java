package org.example.finals.dto.grade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GradeRequestDto {
    private int studentId;
    private int subjectId;
    private int lessonId;
    private String value;
}
