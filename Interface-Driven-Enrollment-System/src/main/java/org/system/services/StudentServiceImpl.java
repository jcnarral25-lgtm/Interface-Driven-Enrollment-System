package org.system.services;

import org.system.entities.Student;
import org.system.interfaces.IStudentService;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements IStudentService {
    private final List<Student> students = new ArrayList<>();

    @Override
    public void addStudent(Student student) {
        students.add(student);
    }

    @Override
    public void updateStudent(String id, String newName) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                s.setName(newName);
            }
        }
    }

    @Override
    public void removeStudent(String id) {
        students.removeIf(s -> s.getId().equals(id));
    }

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }
}