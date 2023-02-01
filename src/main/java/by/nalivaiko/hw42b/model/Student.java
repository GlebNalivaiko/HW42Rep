package by.nalivaiko.hw42b.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Long id;
    @Pattern(regexp = "[A-Za-z]{1,100}", message = "Name can contains only letters!")
    @Length(min = 1, max = 100, message = "Name can not be empty!")
    private String studentName;
    @NotEmpty(message = "Choose the kind of course!")
    private String nameOfCourse;

    public Student(String studentName, String nameOfCourse) {
        this.studentName = studentName;
        this.nameOfCourse = nameOfCourse;
    }
}
