package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.CourseModel;
import com.example.model.StudentModel;
import com.example.service.CourseService;

@Controller
public class CourseController {
	@Autowired
	CourseService courseDAO;

    @RequestMapping("/course/view/{id_course}")
    public String viewCourse (Model model,
            @PathVariable(value = "id_course") String id_course)
    {
        CourseModel course = courseDAO.selectCourse (id_course);
        
        if (course != null) {
            model.addAttribute ("course", course);
            return "course";
        } else {
            return "not-found";
        }
    }
    
    @RequestMapping("/course/viewall")
    public String viewCourse (Model model)
    {
        List<CourseModel> courses = courseDAO.selectAllCourses();
        model.addAttribute ("courses", courses);

        model.addAttribute("title", "Daftar Mata Kuliah");
        return "course-viewall";
    }
}
