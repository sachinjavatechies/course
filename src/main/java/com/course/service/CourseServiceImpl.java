package com.course.service;

import com.course.dto.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//public class CourseServiceImpl implements  CourseService{
@Service
public class CourseServiceImpl{

    //RDS DB
    private final List<Course> courses = new ArrayList<>();


    // Create a new course
    public void addCourse(Course course) {
        courses.add(course);
    }

    //@Override
    // Retrieve all courses
    public List<Course> getAllCourses() {
        return courses;
    }

    //@Override
    // Retrieve a course by id
    public Optional<Course> getCourseById(int id) {
        return courses.stream()
                .filter(course -> course.getId() == id)
                .findFirst();
    }

    //@Override
    // Update a course
    public boolean updateCourse(int id, Course newCourse) {
        return getCourseById(id).map(existingCourse -> {
            courses.remove(existingCourse);
            courses.add(newCourse);
            return true;
        }).orElse(false);
    }

    //@Override
    public boolean deleteCourse(int id) {
        return courses
                .removeIf(course -> course.getId() == id);
    }


}