package com.javatpoint.slaveRepository;

import com.javatpoint.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by anand on 20/4/17.
 */
public interface SlaveStudentRepository extends JpaRepository<Student, Integer> {
    public List<Student> findByAge(int age);
    public List<Student> findByAgeAndName(int age, String name);
    public List<Student> findByName(String name);
}
