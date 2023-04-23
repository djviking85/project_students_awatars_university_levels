package ru.hogwarts.school.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


@ContextConfiguration(classes = {FacultyService.class})
@ExtendWith(SpringExtension.class)

class FacultyServiceTest {
    @Autowired
    private FacultyService facultyService;
    @BeforeEach
    public void clear() {
        facultyService = new FacultyService();
    }
    @Test
    void testAdd() {

        long id = 0L;
        String name = "hell";
        String color = "red";
        Faculty expectedFaculty = new Faculty(id, name, color);

        Faculty actualFaculty = facultyService.add("hell", "red");
        assertEquals(expectedFaculty, actualFaculty);
//        assertEquals(expectedFaculty, this.facultyService.createFaculty(faculty));
//        assertEquals(1, facultyCollection.size());
        assertTrue(facultyService.getAll().containsValue("red"));
    }

    @Test

    void test_add_success() {
        Faculty faculty1 = new Faculty( 0L,"swat", "green");
        Faculty faculty2 = new Faculty( 1L,"swat", "green");
        Collection<Faculty> facultyCollection = Set.of(faculty1, faculty2);
        Collection<Faculty> expectedFaculty = facultyCollection;
//        facultyService.add(faculty1);
//        facultyService.add(faculty2);
        Collection<Faculty> actualFaculty = facultyService.getAll().values();

        assertEquals(expectedFaculty, actualFaculty);

    }
    @Test
    void createFaculty_CorrectParams_ReturnsCorrectFacultyAndActuallyAdds() {

        Faculty faculty = new Faculty(1L,"Gryffindor", "Red");

        Collection<Faculty> facultyCollection = facultyService.getAll().values();

        assertEquals(faculty, this.facultyService.add("Gryffindor", "Red"));
        assertEquals(1, facultyCollection.size());
        assertTrue(facultyCollection.contains(faculty));
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
    void getFilteredByColor() {
    }
}