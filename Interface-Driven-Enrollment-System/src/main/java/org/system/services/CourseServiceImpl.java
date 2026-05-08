package org.system.services;

import org.system.entities.Course;
import org.system.interfaces.ICourseService;
import java.util.ArrayList;
import java.util.List;

public class CourseServiceImpl implements ICourseService {
    private List<Course> courseList = new ArrayList<>();

    @Override
    public void addCourse(Course course) { courseList.add(course); }

    @Override
    public void updateCourse(String id, String newName) {
        for (Course c : courseList) {
            if (c.getCourseID().equals(id)) {
                c.setCourseName(newName);
                return;
            }
        }
    }

    @Override
    public void removeCourse(String id) {
        courseList.removeIf(c -> c.getCourseID().equals(id));
    }

    @Override
    public List<Course> getAllCourses() { return courseList; }
}