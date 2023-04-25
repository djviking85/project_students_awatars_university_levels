package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.*;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;


@RestController
@RequestMapping("/student")

public class StudentController {
    public StudentController(StudentService studentService) {
        this.studientService = studentService;
    }
    private final StudentService studientService;

    @PostMapping

    public Student addStudent(@RequestBody Student student) {
        return studientService.addStudent(student);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Collection<Student>> getAllStudent() {

        return ResponseEntity.ok(studientService.getAllStudent());
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student foundStudent = studientService.updateStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);

    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable long id) {
        studientService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }
}
