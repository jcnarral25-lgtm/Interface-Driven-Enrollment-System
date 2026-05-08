package org.system.controllers;

import org.system.entities.Instructor;
import org.system.entities.Section;

public class HumanResourceController {
    public void assignInstructorToSection(Instructor instructor, Section section) {
        if (instructor == null || section == null) {
            System.out.println("HR: Process failed. Missing resources.");
            return;
        }
        section.setAssignedInstructor(instructor);
        System.out.println("HR: Assigning " + instructor.getPersonName() + " to " + section.getSectionCode());
    }
}