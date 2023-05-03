package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findAllByNameContainsIgnoreCase(String name);

    Collection<Student> findAllByAgeBetween(int min, int max);

    Collection<Student> findAllByAge(int age);

    Collection<Student> findStudentById(long id);

    @Query("select avg(s.age)" + "from Student s")
    double getAverageAge();

    @Query(value = "select *  from Student  ORDER BY id DESC Limit 5;", nativeQuery = true)
    List<Student> getLastFiveStudentsId();
}
