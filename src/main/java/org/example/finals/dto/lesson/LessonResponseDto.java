package org.example.finals.dto.lesson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LessonResponseDto {
    private int id;
    private String topic;
    private String subjectName;
    private String teacherName;
    private LocalDateTime dateTime;
    private String classroom;
}
