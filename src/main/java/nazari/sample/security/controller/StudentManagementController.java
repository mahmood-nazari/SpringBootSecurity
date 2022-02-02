package nazari.sample.security.controller;


import nazari.sample.security.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/management")
public class StudentManagementController {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1L, "ali"),
            new Student(2L, "hasan"),
            new Student(3L, "hossein")
    );


    @GetMapping("/get-all")
    public List<Student> getAllStudent() {
        System.out.println("getAllStudent");
        return STUDENTS;
    }

    @PostMapping("/register")
    public Student registerStudent(@RequestBody Student student){
        System.out.println("registerStudent");
        return student;
    }
}
