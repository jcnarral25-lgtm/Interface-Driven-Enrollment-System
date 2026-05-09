package org.system.interfaces;

import org.system.entities.Instructor;
import org.system.entities.Section;
import java.util.List;

public interface IInstructorService {

    void assignInstructor(String id, String name, String sectionCode, String courseCode);

    void addInstructor(Instructor instructor);
    void assignInstructorToSection(Instructor instructor, Section section);
    Instructor getInstructorDetails(String id);
    List<Instructor> getAllInstructors();
}