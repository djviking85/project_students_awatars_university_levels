package ru.hogwarts.school.controller;

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

    public Student add(@RequestBody StudientCreationReq request) {
        return studientService.add(request.getName(), request.getAge());
    }

    @GetMapping("/getAll")
    public Map<Long, Student> getAll() {

        return studientService.getAll();
    }

    @PutMapping
    public Student update(@RequestBody StudientUpdRequest studientUpdRequest) {
        return studientService.update(
                studientUpdRequest.getId(),
                studientUpdRequest.getName(),
                studientUpdRequest.getAge());
    }

    @DeleteMapping
    public Student delete(long id) {
        return studientService.delete(id);
    }
    @GetMapping("filter/{age}")
    public ResponseEntity<Collection<Student>> getAllFilteredByAge(@PathVariable int age){
        return ResponseEntity.ok(studientService.getFilteredByAge(age));
    }

}
