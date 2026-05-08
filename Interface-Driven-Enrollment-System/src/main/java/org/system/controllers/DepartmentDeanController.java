package org.system.controllers;
import org.system.entities.*;

public class DepartmentDeanController {
    public void displayDepartmentStructure(Department dept) {
        System.out.println("\n--- DEAN'S HIERARCHY REPORT: " + dept.getName() + " ---");
        for (Section section : dept.getSections()) {
            System.out.println("Section: " + section.getSectionCode());

            String prof = (section.getAssignedInstructor() != null)
                    ? section.getAssignedInstructor().getPersonName()
                    : "TBA";

            System.out.println("  Instructor: " + prof);
            System.out.println("  Enrolled: " + section.getEnrolledStudents().size() + "/" + section.getMaxCapacity());

            for (Student s : section.getEnrolledStudents()) {
                System.out.println("    >> [" + s.getPersonID() + "] " + s.getPersonName());
            }
        }
    }
}