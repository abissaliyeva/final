package org.example.finals.dto.grade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GradeResponseDto {
    private int id;
    private String studentName;
    private String subjectName;
    private String lessonTopic;
    private String value;
}
