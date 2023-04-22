package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.Map;
@Service

public class StudentService {
    private long countId = 0;
    private Map<Long, Student> studients = new HashMap<>();
}
