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
import ru.hogwarts.school.service.FacultyService;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


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
        //        готовим данные
        String name = "name";
        String color = "color";

        FacultyCreationRequest request = new FacultyCreationRequest();
        request.setName(name);
        request.setColor(color);
        String jsonData = new ObjectMapper().writeValueAsString(request);

        //        подгтоовка результата
        when(facultyService.addFaculty(new Faculty(1L,name,color))).thenReturn(new Faculty());

        //        начало теста
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(jsonData)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void updateFacultyTestEdit() throws Exception {
       final Long id = 1L;
       final String name = "Hash";
       final String color = "Black";
        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);
        faculty = new Faculty(1L,name, color);
        faculty.setId(id);

        when(facultyService.updateFaculty(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(facultyService.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }


    @Test
    void deleteFaculty_CorrectRequest_CorrectResponse() throws Exception {

        final Long facultyId = 1L;

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/" + facultyId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}