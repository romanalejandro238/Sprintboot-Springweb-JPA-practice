package com.example.demo.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.transaction.Transactional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public String hello() {
        return "Hello World";
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(@RequestBody Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentsByEmail(student.getEmail());

        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email taken");
        } else{
            System.out.println("adding stundent");
            System.out.println(student.getEmail());
            studentRepository.save(student);
        }
        System.out.println("add finished");
    }

    public void deleteStudent(@RequestBody Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentsById(student.getId());
        if(studentOptional.isPresent()){
            System.out.println("Removing student");
            studentRepository.delete(student);
            //Long studentId =student.getId();
            //return new ResponseEntity<String>("Student with ID " + studentId + " deleted successfully", HttpStatus.OK);
        } else{
            throw new IllegalStateException("Student does not exist");
        }
    }

    public void deleteStudent(Long studentId) {
        boolean exist = studentRepository.existsById(studentId);
        if (!exist) {
            throw new IllegalStateException("Student "+ studentId + " does not exist");
        }
        else{
            System.out.println("Removing student by id");
            studentRepository.deleteById(studentId);
        }
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(()-> new IllegalStateException("Student "+ studentId + " does not exist"));

        if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository.findStudentsByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("Email taken");
            } 
            else {
            student.setEmail(email);
            }
        }
    }


}
