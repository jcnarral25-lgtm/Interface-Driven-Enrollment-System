package org.system.controllers.subcontrollers;

import org.system.entities.Course;
import org.system.entities.Department;
import org.system.entities.Section;
import org.system.interfaces.ICourseService;
import org.system.services.CourseServiceImpl;
import org.system.services.SectionServiceImpl;

import java.util.List;
import java.util.Scanner;

public class CourseSubController {
    private final Scanner sc = new Scanner(System.in);
    private final ICourseService courseService;
    private final SectionServiceImpl sectionService;

    public CourseSubController(List<Department> university) {
        this.courseService = new CourseServiceImpl(university);
        this.sectionService = new SectionServiceImpl(university);
    }

    public void handleCourseManagement(List<Department> university) {
        while (true) {
            System.out.println("\n--- COURSE CATALOG MANAGEMENT ---");
            System.out.println("[A] Add New Course");
            System.out.println("[B] Update Course Name");
            System.out.println("[C] Remove Course");
            System.out.println("[D] View All Courses");
            System.out.println("[E] Back to Main Menu");
            System.out.print("Selection: ");
            String choice = sc.nextLine().toUpperCase().trim();

            if (choice.equals("E")) break;

            switch (choice) {
                case "A": performAddCourse(); break;
                case "B": performUpdateCourse(); break;
                case "C": performRemoveCourse(); break;
                case "D": viewCourses(); break;
                default: System.out.println("[ERROR] Invalid selection.");
            }
        }
    }

    private void performAddCourse() {
        System.out.print("Enter Course ID (e.g., CS101): ");
        String id = sc.nextLine().trim();
        System.out.print("Enter Course Name: ");
        String name = sc.nextLine().trim();

        int units;
        while (true) {
            System.out.print("Enter Units: ");
            try {
                units = Integer.parseInt(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("[ERROR] Enter a valid number.");
            }
        }

        System.out.print("Assign to Section Code (e.g., IT1A): ");
        String sectionCode = sc.nextLine().toUpperCase().trim();

        Section section = sectionService.getSectionByCode(sectionCode);

        if (section != null) {
            // FIXED: Added the 4th argument ("") to match your Course constructor
            Course newCourse = new Course(id, name, units, "");

            courseService.addCourse(newCourse);
            section.getCourses().add(newCourse);

            System.out.println("[SUCCESS] Course '" + name + "' added to catalog and Section " + sectionCode + ".");
        } else {
            System.out.println("[ERROR] Section not found. Create the section in Section Management first.");
        }
    }

    private void performUpdateCourse() {
        System.out.print("Enter Course ID to update: ");
        String id = sc.nextLine().trim();
        System.out.print("Enter New Course Name: ");
        String newName = sc.nextLine().trim();

        courseService.updateCourse(id, newName);
        System.out.println("[SUCCESS] Update process completed.");
    }

    private void performRemoveCourse() {
        System.out.print("Enter Course ID to remove: ");
        String id = sc.nextLine().trim();
        courseService.removeCourse(id);
        System.out.println("[SUCCESS] Course removed.");
    }

    private void viewCourses() {
        System.out.println("\n--- CURRENT COURSE LIST ---");
        List<Course> courses = courseService.getAllCourses();
        if (courses == null || courses.isEmpty()) {
            System.out.println("No courses in catalog.");
        } else {
            for (Course c : courses) {
                System.out.println("ID: " + c.getCourseCode() + " | Name: " + c.getCourseName() + " | Units: " + c.getUnits());
            }
        }
    }
}