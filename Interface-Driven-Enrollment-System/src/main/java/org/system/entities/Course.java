package org.system.entities;

public class Course {
    private String courseID;
    private String courseName;
    private int units;
    private String program;

    public Course(String id, String name, int units, String program) {
        this.courseID = id;
        this.courseName = name;
        this.units = units;
        this.program = program;
    }

    public String getCourseID() { return courseID; }
    public void setCourseID(String id) { this.courseID = id; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String name) { this.courseName = name; }

    public int getUnits() { return units; }
    public void setUnits(int units) { this.units = units; }

    public String getProgram() { return program; }
    public void setProgram(String program) { this.program = program; }
}