package com.epicsto.repository;

import com.epicsto.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by anand on 20/4/17.
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {
    public List<Student> findByAge(int age);
    public List<Student> findByAgeAndName(int age, String name);
    public List<Student> findByName(String name);
}
