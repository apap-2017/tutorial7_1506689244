package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CourseMapper;
import com.example.model.CourseModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CourseService {
	
	@Autowired
    private CourseMapper courseMapper;
	
	public CourseModel selectCourse (String id_course) {
    	log.info ("select course by ID {}", id_course);
        return courseMapper.selectCourse (id_course);
    }
}
