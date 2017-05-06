package com.javatpoint.service;

import com.javatpoint.entity.Student;
import com.javatpoint.repository.StudentRepository;
import com.javatpoint.slaveRepository.SlaveStudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by anand on 20/4/17.
 */
@Service("studentService")
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SlaveStudentRepository slaveStudentRepository ;

    public static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

    public String createStudent(int age, String name){
        Student student = new Student(name, age);
        LOGGER.info("going to create a student row in master");
        studentRepository.save(student);
	return "created student ";
    }

    public String getStudents(String name){
        List<Student> listOfStudents = slaveStudentRepository.findByName(name);
        if(listOfStudents==null || listOfStudents.size()==0){
            LOGGER.info("no student found with the name : " + name);
            return null;
        }
        StringBuffer sb = new StringBuffer();
        listOfStudents.stream().forEach(f -> {
            LOGGER.info("student name : " + f.getName());
            LOGGER.info("student age : " + f.getAge());
            LOGGER.info("student id : " + f.getId());
            sb.append("student id : " + f.getId() + " , student age : " + f.getAge() +
                     ", student name : " + f.getName() );
        });
        return sb.toString();
    }

}
