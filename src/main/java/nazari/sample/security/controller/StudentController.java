package nazari.sample.security.controller;


import nazari.sample.security.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1L, "ali"),
            new Student(2L, "hasan"),
            new Student(3L, "hossein")
    );


    @GetMapping(path = "{id}")
    public Student getStudent(@PathVariable(name = "id") Long id) {
        return STUDENTS.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(id + "not found"));
    }
}
