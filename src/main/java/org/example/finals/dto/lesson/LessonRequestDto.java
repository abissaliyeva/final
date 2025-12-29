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
public class LessonRequestDto {
    private String topic;
    private int subjectId;
    private int teacherId;
    private LocalDateTime dateTime;
    private String classroom;
}
