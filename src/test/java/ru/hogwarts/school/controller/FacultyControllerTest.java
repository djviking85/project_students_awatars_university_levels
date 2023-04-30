package ru.hogwarts.school.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.service.FacultyService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(FacultyController.class)

class FacultyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyService facultyService;

    @Test
    void getAllTest() throws Exception {
//        готовим данные

//        подгтоовка результата
        when(facultyService.getAllFaculty()).thenReturn(Collections.emptyList());
//        начало теста
        mockMvc.perform(MockMvcRequestBuilders
                .get("/faculty/getAll")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    @Test
    void addTest() throws Exception {

    }

}