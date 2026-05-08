package org.system.entities;

public class Student extends Person {
    private String program;

    public Student(String id, String name, String program) {
        super(id, name);
        this.program = program;
    }

    public String getProgram() { return program; }
    public void setProgram(String program) { this.program = program; }

    @Override
    public void mainTask() {
        System.out.println("Student " + getPersonName() + " is attending classes for " + program + ".");
    }
}