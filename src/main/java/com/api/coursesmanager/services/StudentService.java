package com.api.coursesmanager.services;

import com.api.coursesmanager.models.StudentModel;
import com.api.coursesmanager.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public StudentModel save(StudentModel studentModel){
        return studentRepository.save(studentModel);
    }

    public List<StudentModel> findAll(){
        return studentRepository.findAll();
    }

    public Optional<StudentModel> findById(UUID id) {
        return studentRepository.findById(id);
    }

    @Transactional
    public void delete(StudentModel studentModel) {
        studentRepository.delete(studentModel);
    }

    public boolean existsByCpf(String cpf) {
        return studentRepository.existsByCpf(cpf);
    }

    public boolean existsByEmail(String email) {
        return studentRepository.existsByEmail(email);
    }

    public boolean existsByPhone(String phone) {
        return studentRepository.existsByPhone(phone);
    }
}
