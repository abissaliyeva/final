package org.example.finals.repositories;

import org.example.finals.entities.Grade;
import org.example.finals.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    List<Grade> findByStudent(User student);
}
