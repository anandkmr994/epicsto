package com.javatpoint;

import com.javatpoint.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by anand on 20/4/17.
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService ;

    @RequestMapping("/create")
    public void createStudentRow(){
        studentService.createStudent(23, "anand kumar");
    }

    @RequestMapping("/get")
    public ModelAndView getStudent(){
        String result = studentService.getStudents("anand kumar");
        return new ModelAndView("hellopage","msg",result);
    }
}
