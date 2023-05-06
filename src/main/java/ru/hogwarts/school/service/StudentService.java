package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;

@Service

public class StudentService  {
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    // создаем круд для сервиса
    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        logger.info("We was added a Student!");
        return studentRepository.save(student);
    }
    //    crud read
    public Collection<Student> getAllStudent() {
        logger.info("We was used getAll method!");
        return studentRepository.findAll();
    }
    //crud update
    public Student updateStudent(Student student) {
        logger.info("We was udpated Student!");
        return studentRepository.save(student);
    }
    //crud delete
    public void deleteStudent(long id) {
        logger.info("We was deleted Student!");
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
        logger.info("We are used getCount method!");
    return studentRepository.count();
}

    public double getAverageAge() {
        return studentRepository.getAverageAge();

    }
    public List<Student> getLastFiveStudent () {
        return studentRepository.getLastFiveStudentsId();

    }

}

