package org.system.services;

import org.system.entities.Department;
import org.system.entities.Instructor;
import org.system.entities.Section;
import org.system.entities.Course;
import org.system.interfaces.IInstructorService;
import java.util.ArrayList;
import java.util.List;

public class InstructorServiceImpl implements IInstructorService {
    private List<Instructor> instructorList = new ArrayList<>();
    private List<Department> university;

    public InstructorServiceImpl(List<Department> university) {
        this.university = university;
    }


    @Override
    public void assignInstructor(String id, String name, String sectionCode, String courseCode) {
        Instructor instructor = new Instructor(id, name);


        for (Department dept : university) {
            for (Section section : dept.getSections()) {
                if (section.getSectionCode().equalsIgnoreCase(sectionCode)) {

                    section.setAssignedInstructor(instructor);


                    if (!instructorList.contains(instructor)) {
                        instructorList.add(instructor);
                    }
                    return;
                }
            }
        }
        System.out.println("[ERROR] Section " + sectionCode + " not found in hierarchy.");
    }

    @Override
    public void addInstructor(Instructor instructor) {
        instructorList.add(instructor);
    }

    @Override
    public void assignInstructorToSection(Instructor instructor, Section section) {
        section.setAssignedInstructor(instructor);
    }

    @Override
    public Instructor getInstructorDetails(String id) {
        for (Instructor ins : instructorList) {
            if (ins.getId().equals(id)) return ins;
        }
        return null;
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorList;
    }
}