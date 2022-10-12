package com.api.coursesmanager.repositories;

import com.api.coursesmanager.models.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<StudentModel, UUID> {

    List<StudentModel> findAllByCourse_Id(UUID id);
    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
}
