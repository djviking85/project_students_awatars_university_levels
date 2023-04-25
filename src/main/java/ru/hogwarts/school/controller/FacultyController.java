package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;


@RestController
@RequestMapping("/faculty")

public class FacultyController {
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    private final FacultyService facultyService;
    @PostMapping

    public  Faculty addFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Collection<Faculty>> getAllFaculty() {

        return ResponseEntity.ok(facultyService.getAllFaculty());
    }

    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty findFaculty = facultyService.updateFaculty(faculty);
        if (findFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(findFaculty);

    }

    @DeleteMapping ("{id}")
    public ResponseEntity deleteFaculty(@PathVariable long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();

    }

}
