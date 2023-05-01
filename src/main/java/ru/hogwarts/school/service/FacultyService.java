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
//    public Faculty add(String name, String color) {
//        Faculty newFaculty = new Faculty();
//        newFaculty = facultyRepository.save(newFaculty);
//        return newFaculty;
//    }
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
    public Collection<Faculty> findAllByNameContainsIgnoreCase(String name) {
        return facultyRepository.findAllByNameContainsIgnoreCase(name);
    }
    public Collection<Faculty> findAllByColorContainsIgnoreCase(String color) {
        return facultyRepository.findAllByColorContainsIgnoreCase(color);
    }


}
