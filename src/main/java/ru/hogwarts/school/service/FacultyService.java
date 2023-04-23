package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private long countId = 0;
    private Map<Long, Faculty> faculties = new HashMap<>();

    //    crud
//    create
    public Faculty add(String name, String color) {
        long id = countId;
        countId++;
        Faculty newFaculty = new Faculty(id, name, color);
        faculties.put(id, newFaculty);
        return newFaculty;

    }
//    crud read
    public Map<Long, Faculty> getAll() {
        return faculties;
    }
//crud update
    public Faculty update(long id, String name, String color) {
        Faculty facultyUpdate = faculties.get(id);
        facultyUpdate.setName(name);
        facultyUpdate.setColor(color);
        return facultyUpdate;
    }
//crud delete
    public Faculty delete(long id) {
        return faculties.remove(id);

    }
    public Collection<Faculty> getFilteredByColor(String color) {
        return faculties.values().stream()
                .filter(f -> f.getColor().equals(color)).collect(Collectors.toList());
    }

}
