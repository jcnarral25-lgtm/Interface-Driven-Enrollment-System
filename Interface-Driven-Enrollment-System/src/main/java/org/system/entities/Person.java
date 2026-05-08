package org.system.entities;

public abstract class Person {
    private String personID;
    private String personName;

    public Person(String id, String name) {
        this.personID = id;
        this.personName = name;
    }

    public String getPersonID() { return personID; }
    public String getPersonName() { return personName; }

    // ADD THIS SETTER TO FIX THE ERROR
    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public abstract void mainTask();
}