package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.*;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/student")

public class StudentController {
    public StudentController(StudentService studentService) {
        this.studientService = studentService;
    }
    private final StudentService studientService;

    @PostMapping

    public Student add(Student student) {
        return studientService.add(student);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Collection<Student>> getAll() {

        return ResponseEntity.ok(studientService.getAll());
    }

    @PutMapping
    public ResponseEntity<Student> update(@RequestBody Student student) {
        Student foundStudent = studientService.update(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
//        return studientService.update(
//                studientUpdRequest.getId(),
//                studientUpdRequest.getName(),
//                studientUpdRequest.getAge());
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable long id) {
        studientService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }
//    @GetMapping("filter/{age}")
//    public ResponseEntity<Collection<Student>> getAllFilteredByAge(@PathVariable int age){
//        return ResponseEntity.ok(studientService.getFilteredByAge(age));
//    }

}
