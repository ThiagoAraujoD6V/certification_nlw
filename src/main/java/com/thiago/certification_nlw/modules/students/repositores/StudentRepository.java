package com.thiago.certification_nlw.modules.students.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thiago.certification_nlw.modules.students.entities.StudentEntity;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {
    public Optional<StudentEntity> findByEmail(String email);

}
