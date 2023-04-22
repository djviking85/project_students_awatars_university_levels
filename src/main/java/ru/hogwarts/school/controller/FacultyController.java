package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.FacultyCreationRequest;
import ru.hogwarts.school.service.FacultyService;

import java.util.Map;

@RestController
@RequestMapping("/faculty")

public class FacultyController {
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    private final FacultyService facultyService;
    @PostMapping

    public  Faculty add(@RequestBody FacultyCreationRequest request) {
        return facultyService.add(request.getName(), request.getColor());
    }

    @GetMapping("/getAll")
    public Map<Long, Faculty> getAll() {

        return facultyService.getAll();
    }
    @PutMapping
    public Faculty update(long id, String name, String color)
}
