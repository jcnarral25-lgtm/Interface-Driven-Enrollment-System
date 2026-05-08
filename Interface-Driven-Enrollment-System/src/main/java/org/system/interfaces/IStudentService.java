package org.system.interfaces;

import org.system.entities.Student;
import java.util.List;

public interface IStudentService {
    void addStudent(Student student);
    void updateStudent(String id, String newName);
    void removeStudent(String id);
    List<Student> getAllStudents();
}