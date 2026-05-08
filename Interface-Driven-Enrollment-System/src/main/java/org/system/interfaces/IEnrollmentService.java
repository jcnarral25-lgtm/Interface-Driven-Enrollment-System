package org.system.interfaces;
import org.system.entities.Department;
import org.system.entities.Student;
import org.system.entities.Section;

public interface IEnrollmentService {
    boolean enrollStudentInSection(Student student, Section section);
    void viewDepartmentHierarchy(Department department);
}