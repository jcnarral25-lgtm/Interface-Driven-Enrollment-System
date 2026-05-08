package org.system.services;

import org.system.entities.Student;
import org.system.interfaces.IStudentService;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements IStudentService {
    private List<Student> studentList = new ArrayList<>();

    @Override
    public void addStudent(Student student) {
        for (Student s : studentList) {
            // Updated: getPersonID() -> getId()
            if (s.getId().equals(student.getId())) {
                System.out.println("[ERROR] ID '" + student.getId() + "' already taken.");
                return;
            }
        }
        studentList.add(student);
    }

    @Override
    public void updateStudent(String id, String newName) {
        for (Student s : studentList) {
            // Updated: getPersonID() -> getId()
            if (s.getId().equals(id)) {
                // Updated: setPersonName() -> setName()
                s.setName(newName);
                return;
            }
        }
    }

    @Override
    public void removeStudent(String id) {
        // Updated: getPersonID() -> getId()
        studentList.removeIf(s -> s.getId().equals(id));
    }

    @Override
    public List<Student> getAllStudents() {
        return studentList;
    }
}