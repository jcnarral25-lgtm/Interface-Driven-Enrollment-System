package org.system.services;

import org.system.entities.*;
import org.system.interfaces.IEnrollmentService;
import org.system.exceptions.SectionFullException;

public class EnrollmentServiceImpl implements IEnrollmentService {

    @Override
    public boolean enrollStudentInSection(Student student, Section section) throws SectionFullException {
        // Logic: Check if the section is full
        if (section.getEnrolledStudents().size() >= section.getMaxCapacity()) {
            // Throwing the custom exception instead of just printing
            throw new SectionFullException("[DENIED] Section " + section.getSectionCode() + " is full!");
        }

        // Add student if not full
        section.getEnrolledStudents().add(student);
        return true;
    }

    @Override
    public void viewDepartmentHierarchy(Department dept) {
        System.out.println("\n--- Department Hierarchy: " + dept.getName() + " ---");
        if (dept.getSections().isEmpty()) {
            System.out.println("No sections available.");
            return;
        }
        for (Section s : dept.getSections()) {
            System.out.println("Section: " + s.getSectionCode() +
                    " | Students: " + s.getEnrolledStudents().size() +
                    "/" + s.getMaxCapacity());
        }
    }
}