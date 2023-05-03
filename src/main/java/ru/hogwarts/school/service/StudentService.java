package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;

@Service

public class StudentService  {

    // создаем круд для сервиса
    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }
    //    crud read
    public Collection<Student> getAllStudent() {
        return studentRepository.findAll();
    }
    //crud update
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }
    //crud delete
    public void deleteStudent(long id) {
         studentRepository.deleteById(id);
    }

//    добавляем
    public Collection<Student> findByAgeBetween(int min, int max) {
        return studentRepository.findAllByAgeBetween(min, max);
    }
    public Collection<Student> getAllByName(String name){
        return studentRepository.findAllByNameContainsIgnoreCase(name);

    }
    public Collection<Student> getAllByAge(int age) {
        return studentRepository.findAllByAge(age);
    }
    public  Student findStudent(long id) {
        return studentRepository.findById(id).get();
    }
public Long getCount () {
    return studentRepository.count();
}

    public double getAverageAge() {
        return studentRepository.getAverageAge();

    }
    public List<Student> getLastFiveStudent () {
        return studentRepository.getLastFiveStudentsId();

    }

}

