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

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        Long id = 0L;
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



//        Map<Long, Faculty> facultyCollection = facultyService.getAll();
//        Faculty actualFaculty = facultyService.add("swat", "green");
//        assertEquals(facultyCollection, actualFaculty);
//        when(facultyService.save.save(faculty)).thenReturn(faculty);
//        when(facultyRepository.findAll()).thenReturn(List.of(faculty));


//        void testAdd() {
//            String question = "question";
//            String answer = "answer";
//            Question expectedQuestion = new Question(question, answer);
//            Question actualQuestion = javaQuestionService.add("question", "answer");
//            assertEquals(expectedQuestion, actualQuestion);

//        Collection<Faculty> facultyCollection = facultyService.getAll();
//
//        assertEquals(faculty, facultyService.createFaculty(faculty));
//        assertEquals(1, facultyCollection.size());
//        assertTrue(facultyCollection.contains(faculty));
//        Mockito.verify(facultyRepository).save(Mockito.any());
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