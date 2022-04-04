package com.in28minutes.coursemanagementdemo.soap.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.in28minutes.coursemanagementdemo.soap.bean.Course;

@Component
public class CourseDetailsService {
	private static List<Course> courses = new ArrayList<>();
	
	static{
		courses.add(new Course(1001, "Spring", "10 steps"));
		courses.add(new Course(1002, "Spring MVC", "10 Examples"));
		courses.add(new Course(1003, "Spring Boot", "10K Students"));
		courses.add(new Course(1004, "Maven", "popular maven course"));
	}
	
	public Course findId(int id){
		for (Course course : courses) {
			if(course.getId()==id)
				return course;
		}
		return null;
	}
	
	public List<Course> findAll(){
		return courses;
	}
	
	public int deleteById(int id){
		Iterator<Course> iterator = courses.iterator();
		while(iterator.hasNext()){
			Course course = iterator.next();
			if(course.getId()==id){
				iterator.remove();
				return 1;
			}	
		}
		return 0;
	}
}
