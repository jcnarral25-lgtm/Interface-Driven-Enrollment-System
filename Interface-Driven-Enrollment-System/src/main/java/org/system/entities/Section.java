package org.system.entities;

import java.util.ArrayList;
import java.util.List;

public class Section {
    private String sectionCode;
    private int maxCapacity;
    private List<Student> enrolledStudents;
    private Instructor assignedInstructor;

    private List<Course> courses = new ArrayList<>();

    public List<Course> getCourses() {
        return courses;
    }

    public Section(String sectionCode, int maxCapacity) {
        this.sectionCode = sectionCode;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = new ArrayList<>();
    }


    public void setAssignedInstructor(Instructor instructor) {
        this.assignedInstructor = instructor;
    }

    public Instructor getAssignedInstructor() {
        return assignedInstructor;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}