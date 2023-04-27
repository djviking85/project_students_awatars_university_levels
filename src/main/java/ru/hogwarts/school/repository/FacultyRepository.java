package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.context.request.FacesWebRequest;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Collection<Faculty> findAllByNameContainsIgnoreCase(String name);
    Collection<Faculty> findAllByColorContainsIgnoreCase(String color);

}
