package com.example.demo.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentsByEmail(String email);

    @Query("SELECT s FROM Student s WHERE s.id = ?1")
    Optional<Student> findStudentsById(Long id);
}
