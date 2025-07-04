package com.course.controller;

import com.course.dto.Course;
import com.course.service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/course")
public class CourseController {

    //Feature Development:
    //1. Add the validation on controller level.
    //2. Add the Request and Response Entity.[Note: Check approach to use.]
    //3. Create the standard Request and Response code, message and add the error response name and code.
    //4. Cross check how to add the Exception Handler.
    //5. How to provide the OAuth/JWT Token Validation in this.
    //6. Also use the Redis Cache into it.
    //7. Add the Hibernate to store the data and also use the distrubute data sharing approach in the hibernate.
    //8. Keep the AWSCloud Watch Logs.
    //9. Read all the properties from application.properties or separate properties for place holder.

    @Autowired
    private CourseServiceImpl courseServiceImpl;

    @PostMapping(value ="/addcourse", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Course> addCourse(@RequestBody Course course){
        courseServiceImpl.addCourse(course);
         return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    @GetMapping(value = "getallcourses", produces = "application/json")
    public ResponseEntity<List<Course>> getAllCourses(){
          List<Course> courses = courseServiceImpl.getAllCourses();
          return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Course> getCourseById(@PathVariable Integer id){
            Optional<Course> course = courseServiceImpl.getCourseById(id);
            return course.map(value -> new ResponseEntity<>(course.get(), HttpStatus.OK)
                    ).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Course> updateCourse(@PathVariable int id, @RequestBody Course newCourse) {
        boolean updated = courseServiceImpl.updateCourse(id, newCourse);
        if (updated) {
            return new ResponseEntity<>(newCourse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteCourse(@PathVariable int id) {
        boolean deleted = courseServiceImpl.deleteCourse(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "UP";
    }


    @GetMapping("/welcome")
    public String greetings() {
        return "Hello Techie , AWS CICD Example working fine !";
    }
}