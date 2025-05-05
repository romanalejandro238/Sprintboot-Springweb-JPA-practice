package com.example.demo.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.transaction.Transactional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final String successRequest = "The request were successful";
    private final String createsuccessfully = "Student created successfully";
    private final String deleteSuccessfully = "The student were deleted successfully";
    private final String updateSuccessfully = "The student were updated successfully";

    private final String internalServerErrorRequest = "The request were not successful";
    private final String conflictCreate = "Student email was already in use";
    // private final String conflictDelete = "Student doesn't exist";
    private final String noChangesMade = "Student were not updated";

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public String hello() {
        return "Hello World";
    }

    public ResponseEntity<StudentApiResponse> getStudents() {
        try {
            List<Student> students = studentRepository.findAll();
            StudentApiResponse apiResponse = new StudentApiResponse(HttpStatus.OK.value(), successRequest, students);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            StudentApiResponse apiResponse = new StudentApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    internalServerErrorRequest, null);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
    }

    public ResponseEntity<StudentApiResponse> addStudent(@RequestBody Student student) {
        try {
            Optional<Student> studentOptional = studentRepository.findStudentsByEmail(student.getEmail());

            if (studentOptional.isPresent()) {
                StudentApiResponse apiResponse = new StudentApiResponse(HttpStatus.CONFLICT.value(), conflictCreate,
                        student);
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            } else {
                studentRepository.save(student);
                StudentApiResponse apiResponse = new StudentApiResponse(HttpStatus.OK.value(), createsuccessfully,
                        student);
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            }
        } catch (Exception e) {
            StudentApiResponse apiResponse = new StudentApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    internalServerErrorRequest, null);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }

    }

    public ResponseEntity<StudentApiResponse> deleteStudent(@RequestBody Student student) {
        try {
            Optional<Student> studentOptional = studentRepository.findStudentsById(student.getId());
            if (studentOptional.isPresent()) {

                studentRepository.delete(student);
                StudentApiResponse apiResponse = new StudentApiResponse(HttpStatus.OK.value(), deleteSuccessfully,
                        student);
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            } else {
                StudentApiResponse apiResponse = new StudentApiResponse(HttpStatus.OK.value(),
                        internalServerErrorRequest, student);
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            }

        } catch (Exception e) {
            StudentApiResponse apiResponse = new StudentApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    internalServerErrorRequest, null);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }

    }

    public ResponseEntity<StudentApiResponse> deleteStudent(Long studentId) {

        try {
            boolean exist = studentRepository.existsById(studentId);
            if (!exist) {
                StudentApiResponse apiResponse = new StudentApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        internalServerErrorRequest, null);
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            } else {
                studentRepository.deleteById(studentId);
                StudentApiResponse apiResponse = new StudentApiResponse(HttpStatus.OK.value(), deleteSuccessfully,
                        studentId);
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            }

        } catch (Exception e) {
            StudentApiResponse apiResponse = new StudentApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    internalServerErrorRequest, null);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }

    }

    @Transactional
    public ResponseEntity<StudentApiResponse> updateStudent(Long studentId, String name, String email) {

        try {

            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new IllegalStateException("Student " + studentId + " does not exist"));

            Boolean nameChanged = false;

            if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
                student.setName(name);
                nameChanged = true;
            }

            if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
                Optional<Student> studentOptional = studentRepository.findStudentsByEmail(email);
                if (studentOptional.isPresent()) {
                    StudentApiResponse apiResponse = new StudentApiResponse(HttpStatus.CONFLICT.value(), conflictCreate,
                            student);
                    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
                } else {
                    student.setEmail(email);
                    StudentApiResponse apiResponse = new StudentApiResponse(HttpStatus.OK.value(), updateSuccessfully,
                            student);
                    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
                }
            }

            if (nameChanged) {
                StudentApiResponse apiResponse = new StudentApiResponse(HttpStatus.OK.value(), updateSuccessfully,
                        student);
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            } else {
                StudentApiResponse apiResponse = new StudentApiResponse(HttpStatus.OK.value(), noChangesMade,
                        student);
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            }

        } catch (Exception e) {
            StudentApiResponse apiResponse = new StudentApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    internalServerErrorRequest, null);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }

    }

}
