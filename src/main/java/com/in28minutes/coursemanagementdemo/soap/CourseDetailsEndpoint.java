package com.in28minutes.coursemanagementdemo.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.in28minutes.coursemanagementdemo.soap.bean.Course;
import com.in28minutes.coursemanagementdemo.soap.service.CourseDetailsService;
import com.in28minutes.courses.CourseDetails;
import com.in28minutes.courses.GetAllCourseDetailsRequest;
import com.in28minutes.courses.GetAllCourseDetailsResponse;
import com.in28minutes.courses.GetCourseDetailsRequest;
import com.in28minutes.courses.GetCourseDetailsResponse;


@Endpoint
public class CourseDetailsEndpoint {
	
	@Autowired
	CourseDetailsService service;

//	request - GetCourseDetailsRequest
//	response - GetCourseDetailsResponse
	
//	http://mywebservices.com/courses
//	GetCourseDetailsRequest
	
	@PayloadRoot(namespace="http://in28minutes.com/courses", localPart="GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest(
			@RequestPayload GetCourseDetailsRequest request){
		
		Course course = service.findId(request.getId());
		return mapCourseDetails(course);
	}

	@PayloadRoot(namespace="http://in28minutes.com/courses", localPart="GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processCourseDetailsRequest(
			@RequestPayload GetAllCourseDetailsRequest request){
		List<Course> courses = service.findAll();
		return mapAllCourseDetails(courses);
	}
	
	private GetCourseDetailsResponse mapCourseDetails(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		response.setCourseDetails(mapCourse(course));
		return response;
	}
	private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		for (Course course : courses) {
			CourseDetails mapCourse = mapCourse(course);
			response.getCourseDetails().add(mapCourse);
		}
		return response;
	}

	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();
		
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}


}
