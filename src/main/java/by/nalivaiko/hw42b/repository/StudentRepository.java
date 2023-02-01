package by.nalivaiko.hw42b.repository;

import by.nalivaiko.hw42b.model.Student;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class StudentRepository {
    @SneakyThrows
    private Statement getConnection() {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://", "", "");
        Statement statement = connection.createStatement();
        statement.executeUpdate("create table if not exists students_and_courses(id integer unique primary key auto_increment,studentname varchar(100) not null, nameofcourse varchar(30) not null)");
        return statement;
    }

    @SneakyThrows
    public List<Student> getAllStudents() {
        try (Statement statement = getConnection()) {
            List<Student> students = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("select * from students_and_courses");
            while (resultSet.next()) {
                students.add(new Student(resultSet.getLong("id"), resultSet.getString("studentname"), resultSet.getString("nameOfCourse")));
            }
            Collections.reverse(students);
            return students;
        }
    }

    @SneakyThrows
    public void deleteStudent(Long id) {
        try (Statement statement = getConnection()) {
            statement.executeUpdate("delete from students_and_courses where id='" + id + "'");
        }
    }

    @SneakyThrows
    public void addStudent(Student student) {
        try (Statement statement = getConnection()) {
            statement.executeUpdate("insert into students_and_courses(studentname,nameofcourse) values ('" + student.getStudentName() + "','" + student.getNameOfCourse() + "')");
        }
    }
}
