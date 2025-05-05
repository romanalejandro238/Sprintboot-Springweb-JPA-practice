package com.example.demo.student;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<StudentApiResponse> getStudents() {
        ResponseEntity<StudentApiResponse> response = service.getStudents();
        return response;
    }

    @PostMapping(path = "addStudent")
    public ResponseEntity<StudentApiResponse> addStudents(@RequestBody Student student) {
        ResponseEntity<StudentApiResponse> response = service.addStudent(student);
        return response;
    }

    @DeleteMapping(path = "deleteStudent")
    public ResponseEntity<StudentApiResponse> deleteStudent(@RequestBody Student student) {
        ResponseEntity<StudentApiResponse> response = service.deleteStudent(student);
        return response;
        
    }

    @DeleteMapping(path = "deleteStudent/{studentId}")
    public ResponseEntity<StudentApiResponse> deleteStudent(@PathVariable("studentId") Long studentId) {  
        ResponseEntity<StudentApiResponse> response = service.deleteStudent(studentId);
        return response;
    }

    @PutMapping(path = "updateStudent/{studentId}")
    public ResponseEntity<StudentApiResponse> updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestParam(required = false) String name, 
                              @RequestParam(required = false) String email){
        ResponseEntity<StudentApiResponse> response = service.updateStudent(studentId,name,email);
        return response;
    }


}
