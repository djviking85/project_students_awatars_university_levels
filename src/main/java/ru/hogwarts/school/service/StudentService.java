package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service

public class StudentService {
    private long countId = 0;
    private Map<Long, Student> studients = new HashMap<>();

    // создаем круд для сервиса
    // 1) креейт
    public Student add(String name, int age) {
        long id = countId;
        countId++;
        Student newStudient = new Student(id, name, age);
        studients.put(id, newStudient);
        return newStudient;
    }
    //    crud read
    public Map<Long, Student> getAll() {
        return studients;
    }
    //crud update
    public Student update(long id, String name, int age) {
        Student studentUpdate = studients.get(id);
        studentUpdate.setName(name);
        studentUpdate.setAge(age);
        return studentUpdate;
    }
    //crud delete
    public Student delete(long id) {
        return studients.remove(id);
    }
    public Collection<Student> getFilteredByAge(int age) {
        return studients.values().stream()
                .filter(s -> s.getAge() == age).collect(Collectors.toList());
    }

}

