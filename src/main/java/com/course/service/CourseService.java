package com.course.service;

import com.course.dto.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    public void addCourse(Course course);
    public List<Course> getAllCourses();
    public Optional<Course> getCourseById(int id);
    public boolean updateCourse(int id, Course newCourse);
    public boolean deleteCourse(int id);

}
