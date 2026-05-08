package org.system.entities;
import java.util.ArrayList;
import java.util.List;

public class Section {
    private String sectionCode;
    private int maxCapacity;
    private List<Student> enrolledStudents = new ArrayList<>();
    private Instructor assignedInstructor;

    public Section(String code, int capacity) {
        this.sectionCode = code;
        this.maxCapacity = capacity;
    }

    public String getSectionCode() { return sectionCode; }
    public int getMaxCapacity() { return maxCapacity; }
    public List<Student> getEnrolledStudents() { return enrolledStudents; }
    public void setAssignedInstructor(Instructor instructor) { this.assignedInstructor = instructor; }
    public Instructor getAssignedInstructor() { return assignedInstructor; }
}