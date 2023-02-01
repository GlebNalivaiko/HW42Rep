package by.nalivaiko.hw42b.controller;

import by.nalivaiko.hw42b.model.Student;
import by.nalivaiko.hw42b.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("students")
@Slf4j
@RequiredArgsConstructor
@SessionAttributes("student")
public class PageOfStudentsController {
    private final StudentService service;

    @GetMapping
    public String getStudentPage(Model model) {
        model.addAttribute("stud", service.getStudents());
        return "studentsPage";
    }

    @GetMapping("delete")
    public String deleteStudent(Model model, @RequestParam(value = "id") Long id) {
        log.error("id{}", id);
        service.deleteStudent(id);
        model.addAttribute("stud", service.getStudents());
        return "studentsPage";
    }

    @PostMapping
    public String addStudent(Model model, @Valid Student student, Errors errors) {
        log.error("name{}", student.getStudentName().length());
        if (errors.hasErrors()) {
            log.error("Errors: {}", errors.getAllErrors());
            model.addAttribute("stud", service.getStudents());
        } else {
            service.addStudent(new Student(student.getStudentName(), student.getNameOfCourse()));
            model.addAttribute("stud", service.getStudents());
        }
        return "studentsPage";
    }

    @ModelAttribute(name = "student")
    public Student getSessionStudent() {
        return new Student();
    }
}
