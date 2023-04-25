package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {
    @Autowired
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    //    crud
//    create
    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);

    }
//    crud read
    public Collection<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }
//crud update
    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }
//crud delete
    public void deleteFaculty(long id) {
facultyRepository.deleteById(id);
    }

}
