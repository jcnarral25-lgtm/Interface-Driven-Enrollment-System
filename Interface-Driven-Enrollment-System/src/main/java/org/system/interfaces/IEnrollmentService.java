package org.system.interfaces;

import org.system.entities.*;
import org.system.exceptions.SectionFullException;

public interface IEnrollmentService {
    boolean enrollStudentInSection(Student student, Section section) throws SectionFullException;

    void viewDepartmentHierarchy(Department dept);
}