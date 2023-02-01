package by.nalivaiko.hw42b.service;

import by.nalivaiko.hw42b.model.Student;
import by.nalivaiko.hw42b.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class StudentService {
    private final StudentRepository repository;

    public void addStudent(Student student) {
        repository.addStudent(student);
    }

    public List<Student> getStudents() {
        return repository.getAllStudents();
    }

    public void deleteStudent(Long id) {
        repository.deleteStudent(id);
    }
}
