package org.system.services;

import org.system.entities.Course;
import org.system.entities.Department;
import org.system.interfaces.ICourseService;
import java.util.ArrayList;
import java.util.List;

public class CourseServiceImpl implements ICourseService {

    private final List<Course> courseList = new ArrayList<>();
    private List<Department> university;


    public CourseServiceImpl(List<Department> university) {
        this.university = university;
    }

    @Override
    public void addCourse(Course course) {
        courseList.add(course);
    }

    @Override
    public void updateCourse(String id, String newName) {
        for (Course c : courseList) {
            // Use the getter name we fixed in Course.java
            if (c.getCourseCode().equals(id)) {
                c.setCourseName(newName);
                return;
            }
        }
    }

    @Override
    public void removeCourse(String id) {
        courseList.removeIf(c -> c.getCourseCode().equals(id));
    }

    @Override
    public List<Course> getAllCourses() {
        return courseList;
    }
}