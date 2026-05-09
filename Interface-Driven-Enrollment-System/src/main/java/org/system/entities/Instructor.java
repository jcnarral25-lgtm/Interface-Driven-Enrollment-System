package org.system.entities;

public class Instructor extends Person {
    public Instructor(String id, String name) {
        super(id, name);
    }

    public String getName() {
        return getPersonName();
    }

    @Override
    public void mainTask() {
        System.out.println("Teaching assigned sections.");
    }
}