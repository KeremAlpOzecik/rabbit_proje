package com.ramazan.consumer_service.repository;

import com.ramazan.consumer_service.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, String> {
}
