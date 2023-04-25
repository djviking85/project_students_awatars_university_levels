package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    //    private long countId = 0;
//    private Map<Long, Faculty> faculties = new HashMap<>();
    @Autowired
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    //    crud
//    create
    public Faculty addFaculty(Faculty faculty) {
//        long id = countId;
//        countId++;
//        Faculty newFaculty = new Faculty(id, name, color);
//        faculties.put(id, newFaculty);
//        return newFaculty;
        return facultyRepository.save(faculty);

    }
//    crud read
    public Collection<Faculty> getAllFaculty() {
//        return faculties;
        return facultyRepository.findAll();
    }
//crud update
    public Faculty updateFaculty(Faculty faculty) {
//        Faculty facultyUpdate = faculties.get(id);
//        facultyUpdate.setName(name);
//        facultyUpdate.setColor(color);
//        return facultyUpdate;
        return facultyRepository.save(faculty);
    }
//crud delete
    public void deleteFaculty(long id) {
//        return faculties.remove(id);
facultyRepository.deleteById(id);
    }
//    public Collection<Faculty> getFilteredByColor(String color) {
//        return faculties.values().stream()
//                .filter(f -> f.getColor().equals(color)).collect(Collectors.toList());
//    }

}
