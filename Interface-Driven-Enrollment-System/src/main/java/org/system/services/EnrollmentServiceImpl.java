package org.system.services;

import org.system.entities.*;
import org.system.interfaces.IEnrollmentService;

public class EnrollmentServiceImpl implements IEnrollmentService {

    @Override
    public boolean enrollStudentInSection(Student student, Section section) {
        if (section.getEnrolledStudents().size() >= section.getMaxCapacity()) {
            System.out.println("\n[ERROR] Section " + section.getSectionCode() + " is full!");
            return false;
        }
        section.getEnrolledStudents().add(student);
        return true;
    }

    // ADD THIS METHOD TO FIX THE RED ERROR IN YOUR SCREENSHOT
    @Override
    public void viewDepartmentHierarchy(Department dept) {
        System.out.println("Viewing Hierarchy for: " + dept.getName());
        for (Section s : dept.getSections()) {
            System.out.println(" - " + s.getSectionCode());
        }
    }
}