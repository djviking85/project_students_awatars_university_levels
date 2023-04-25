package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service

public class StudentService  {
//    private long countId = 0;
//    private Map<Long, Student> studients = new HashMap<>();

    // создаем круд для сервиса
    // 1) креейт
    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
//        long id = countId;
//        countId++;
//        Student newStudient = new Student(id, name, age);
//        studients.put(id, newStudient);
//        return newStudient;
        return studentRepository.save(student);
    }
    //    crud read
    public Collection<Student> getAllStudent() {
        return studentRepository.findAll();
    }
    //crud update
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
//        Student studentUpdate = studients.get(id);
//        studentUpdate.setName(name);
//        studentUpdate.setAge(age);
//        return studentUpdate;
    }
    //crud delete
    public void deleteStudent(long id) {
         studentRepository.deleteById(id);
    }
//    public Collection<Student> getFilteredByAge(int age) {
//        return studentRepository.values().stream()
//                .filter(s -> s.getAge() == age).collect(Collectors.toList());
//    }

}

