package com.ramazan.producer_service.repository;

import com.ramazan.producer_service.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
}