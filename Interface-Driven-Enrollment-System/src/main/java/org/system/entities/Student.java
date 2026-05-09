package org.system.entities;

public class Student extends Person {
    private String program;

    public Student(String id, String name, String program) {
        super(id, name);
        this.program = program;
    }

    public String getId() {
        return getPersonID();
    }

    public String getName() {
        return getPersonName();
    }

    public void setName(String name) {
        setPersonName(name);
    }

    public String getProgram() {
        return program;
    }

    @Override
    public void mainTask() {
        System.out.println("Studying for degree requirements.");
    }
}