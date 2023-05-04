package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {StudentService.class})
@ExtendWith(SpringExtension.class)

class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    void getAverageAge_success() {


        //Подготовка входных данных
        double avgAge = 30;

        //Подготовка ожидаемого результата

        when(studentRepository.getAverageAge()).thenReturn(avgAge);

        //Начало теста
        double actualAvgAge = studentService.getAverageAge();
        assertEquals(avgAge, actualAvgAge);
        verify(studentRepository).getAverageAge();
        verifyNoMoreInteractions(studentRepository);
    }

    @Test
    void getLastFiveStudent_success() {
        //Подготовка входных данных
        Student firstStudent = new Student(1L,"HArry", 22);
        Student secondStudent = new Student(2L,"Kane", 229);

        List<Student> students = List.of(firstStudent, secondStudent);

        //Подготовка ожидаемого результата

        when(studentRepository.getLastFiveStudentsId()).thenReturn(students);

        //Начало теста

        List<Student> actualStudents = studentService.getLastFiveStudent();
        assertEquals(students, actualStudents);
        verify(studentRepository).getLastFiveStudentsId();
        verifyNoMoreInteractions(studentRepository);
    }

    @Test
    void add() {
    }

    @Test
    void getAll() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void getFilteredByAge() {
    }
}