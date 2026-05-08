package org.system.entities;

public class Instructor extends Person {
    public Instructor(String id, String name) { super(id, name); }

    @Override
    public void mainTask() {
        System.out.println("Instructor " + getPersonName() + " is preparing lesson plans.");
    }
}