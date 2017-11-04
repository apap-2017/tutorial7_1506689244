package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.StudentModel;
import com.example.service.StudentService;

@Controller
public class StudentController
{
    @Autowired
    StudentService studentDAO;


    @RequestMapping("/")
    public String index (Model model)
    {
    	model.addAttribute("title", "Tutorial 06 APAP");
        return "index";
    }


    @RequestMapping("/student/add")
    public String add (Model model)
    {
    	model.addAttribute("title", "Tambah Mahasiswa");
        return "form-add";
    }


    @RequestMapping("/student/add/submit")
    public String addSubmit (Model model,
            @RequestParam(value = "npm", required = false) String npm,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gpa", required = false) double gpa)
    {
        StudentModel student = new StudentModel (npm, name, gpa, null);
        studentDAO.addStudent (student);

        model.addAttribute("title", "Tambah Mahasiswa");
        return "success-add";
    }


    @RequestMapping("/student/view")
    public String view (Model model,
            @RequestParam(value = "npm", required = false) String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
        	model.addAttribute("title", "Lihat Mahasiswa");
            model.addAttribute ("student", student);
            return "view";
        } else {
        	model.addAttribute("title", "Not Found");
            return "not-found";
        }
    }


    @RequestMapping("/student/view/{npm}")
    public String viewPath (Model model,
            @PathVariable(value = "npm") String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
        	model.addAttribute("title", "Lihat Mahasiswa");
            model.addAttribute ("student", student);
            return "view";
        } else {
        	model.addAttribute("title", "Not Found");
            return "not-found";
        }
    }
    
    @RequestMapping("/student/viewall")
    public String view (Model model)
    {
        List<StudentModel> students = studentDAO.selectAllStudents ();
        model.addAttribute ("students", students);

        model.addAttribute("title", "Daftar Mahasiswa");
        return "viewall";
    }

    @RequestMapping("/student/delete/{npm}")
    public String delete (Model model, @PathVariable(value = "npm") String npm)
    {
    	StudentModel student = studentDAO.selectStudent(npm);
		
		if(student == null) {
			model.addAttribute("title", "Not Found");
			return "not-found";
		} else {
			model.addAttribute("title", "Hapus Data");
			studentDAO.deleteStudent (npm);
			return "delete";
		}
    }

    @RequestMapping("/student/update/{npm}")
    public String update (Model model, @PathVariable(value = "npm") String npm)
    {
    	StudentModel student = studentDAO.selectStudent(npm);
		
		if(student == null) {
			model.addAttribute("title", "Not Found");
			return "not-found";
		} else {
			model.addAttribute("title", "Ubah Mahasiswa");
			model.addAttribute("student", student);
			return "form-update";
		}
    }
    
//    @RequestMapping(value = "/student/update/submit", method = RequestMethod.POST)
//    public String updateSubmit (
//    @RequestParam(value="npm", required=false) String npm,
//    @RequestParam(value="name", required=false) String name,
//    @RequestParam(value="gpa", required=false) double gpa){
//    	
//    	StudentModel student = new StudentModel(npm, name, gpa);
//		
//		studentDAO.updateStudent (student);
//		return "success-update";
//    }
    
    @RequestMapping(value = "/student/update/submit", method = RequestMethod.POST)
    public String updateSubmit (Model model, StudentModel student){
    	
    	model.addAttribute("title", "Ubah Mahasiswa");
		studentDAO.updateStudent (student);
		return "success-update";		
    }
    
//	@Autowired
//	CourseService courseDAO;
//
//    @RequestMapping("/course/view/{id_course}")
//    public String viewCourse (Model model,
//            @PathVariable(value = "id_course") String id_course)
//    {
//        CourseModel course = courseDAO.selectCourse (id_course);
//        
//        if (course != null) {
//            model.addAttribute ("course", course);
//            return "course";
//        } else {
//            return "not-found";
//        }
//    }

}
