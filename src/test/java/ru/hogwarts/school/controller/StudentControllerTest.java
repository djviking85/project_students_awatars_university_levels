package ru.hogwarts.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.FacultyCreationRequest;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.model.StudientCreationReq;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)

class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentService studentService;
    @MockBean
    private AvatarService avatarService;
    @Test
    void getAllTest() throws Exception {
//        готовим данные

//        подгтоовка результата
        when(studentService.getAllStudent()).thenReturn(Collections.emptyList());
//        начало теста
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/getAll")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    @Test
    void addTest() throws Exception {
        //        готовим данные
        String name = "student";
        int age = 22;

        StudientCreationReq request = new StudientCreationReq();
        request.setName(name);
        request.setAge(age);
        String jsonData = new ObjectMapper().writeValueAsString(request);

        //        подгтоовка результата
        when(studentService.addStudent(new Student(1L,name,age))).thenReturn(new Student());

        //        начало теста
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(jsonData)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void updateStudentTestEdit() throws Exception {
        final Long id = 1L;
        final String name = "name";
        final int age = 22;
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        student = new Student(1L,name, age);
        student.setId(id);

        when(studentService.updateStudent(any(Student.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(studentService.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(age));
    }
//
//
    @Test
    void deleteStudent() throws Exception {

        final Long studentId = 1L;

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/" + studentId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}