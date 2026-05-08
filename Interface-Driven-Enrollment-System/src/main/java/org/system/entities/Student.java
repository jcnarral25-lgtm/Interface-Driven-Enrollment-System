package org.system.entities;

public class Student {
    private String id;
    private String name;
    private String program;

    public Student(String id, String name, String program) {
        this.id = id;
        this.name = name;
        this.program = program;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProgram() {
        return program;
    }
}