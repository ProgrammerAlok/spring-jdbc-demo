package com.example.SpringJDBCEx.repository;

import com.example.SpringJDBCEx.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {
    private JdbcTemplate jdbc;

    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void save(Student s) {
        String sql = "insert into student (rollNo, name, marks) values (?,?,?)";
        int row = jdbc.update(sql, s.getRollNo(), s.getName(), s.getMarks());
        System.out.println(row + " affected");
    }

    public List<Student> findAll() {
        String sql = "select * from student";
        List<Student> students = jdbc.query(sql, (rs, d) -> {
            Student s = new Student();
            s.setRollNo(rs.getInt("rollno"));
            s.setName(rs.getString("name"));
            s.setMarks(rs.getInt("marks"));
            return s;
        });

        return students;
    }
}