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
            if (s.getPersonID().equals(student.getPersonID())) {
                System.out.println("[ERROR] ID '" + student.getPersonID() + "' already taken.");
                return;
            }
        }
        studentList.add(student);
    }

    @Override
    public void updateStudent(String id, String newName) {
        for (Student s : studentList) {
            if (s.getPersonID().equals(id)) {
                s.setPersonName(newName);
                return;
            }
        }
    }

    @Override
    public void removeStudent(String id) {
        studentList.removeIf(s -> s.getPersonID().equals(id));
    }

    @Override
    public List<Student> getAllStudents() { return studentList; }
}