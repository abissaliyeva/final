package org.example.finals.repositories;

import org.example.finals.entities.Lesson;
import org.example.finals.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findBySubject(Subject subject);
}
