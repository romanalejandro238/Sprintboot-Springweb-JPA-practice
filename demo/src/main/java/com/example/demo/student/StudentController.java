package com.example.demo.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/student")
public class StudentController {

    private final StudentService service;


    @Autowired
    public StudentController(StudentService studentService) {
        this.service =  studentService;
    }

    @GetMapping(path = "/")
    public String hello() {

        return service.hello();
    }
    
    @GetMapping(path = "getAll")
    public List<Student> getStudents() {

        return service.getStudents();
    }

    @PostMapping(path = "addStudent")
    public void addStudents(@RequestBody Student student) {
         service.addStudent(student);
    }

    @DeleteMapping(path = "deleteStudent")
    public void deleteStudent(@RequestBody Student student) {
        service.deleteStudent(student);
    }

    @DeleteMapping(path = "deleteStudent/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        service.deleteStudent(studentId);
    }

    @PutMapping(path = "updateStudent/{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestParam(required = false) String name, 
                              @RequestParam(required = false) String email){
                                
        service.updateStudent(studentId,name,email);
    }


}
