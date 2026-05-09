package org.system.controllers.subcontrollers;

import org.system.entities.Department;
import org.system.entities.Section;
import org.system.entities.Course;
import org.system.services.InstructorServiceImpl;
import org.system.services.SectionServiceImpl;
import java.util.List;
import java.util.Scanner;

public class InstructorSubController {
    private final Scanner sc = new Scanner(System.in);
    private final InstructorServiceImpl instructorService;
    private final SectionServiceImpl sectionService;

    // Constructor now takes 'university' to initialize services correctly
    public InstructorSubController(List<Department> university) {
        this.instructorService = new InstructorServiceImpl(university);
        this.sectionService = new SectionServiceImpl(university);
    }

    public void handleInstructorManagement(List<Department> university) {
        while (true) {
            System.out.println("\n--- INSTRUCTOR MANAGEMENT ---");
            System.out.println("[A] Add New Instructor");
            System.out.println("[B] Edit Instructor Info");
            System.out.println("[C] Remove Instructor");
            System.out.println("[D] Back to Main Menu");
            System.out.print("Selection: ");

            String sub = sc.nextLine().toUpperCase().trim();

            if (sub.equals("D")) break;

            switch (sub) {
                case "A":
                    performAdd(university);
                    break;
                case "B":
                    // performEdit(university);
                    break;
                case "C":
                    // performRemove(university);
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
        }
    }

    private void performAdd(List<Department> university) {
        try {
            System.out.print("Enter Instructor ID: ");
            String id = sc.nextLine().trim();
            System.out.print("Enter Full Name: ");
            String name = sc.nextLine().trim();

            // 1. Ask for Section Code first
            System.out.print("Assign to Section Code: ");
            String sectionCode = sc.nextLine().toUpperCase().trim();

            // 2. Use the initialized sectionService to find the section
            Section section = sectionService.getSectionByCode(sectionCode);

            if (section != null) {
                // 3. Show courses linked to this section
                List<Course> availableCourses = section.getCourses();

                if (availableCourses.isEmpty()) {
                    System.out.println("[ERROR] No courses assigned to " + sectionCode + " yet.");
                    return;
                }

                System.out.println("\nAvailable Courses in " + sectionCode + ":");
                for (int i = 0; i < availableCourses.size(); i++) {
                    System.out.println("[" + (i + 1) + "] " + availableCourses.get(i).getCourseName());
                }

                System.out.print("Select Course Number: ");
                int choice = Integer.parseInt(sc.nextLine()) - 1;

                if (choice >= 0 && choice < availableCourses.size()) {
                    Course selected = availableCourses.get(choice);

                    // 4. Final assignment call
                    instructorService.assignInstructor(id, name, sectionCode, selected.getCourseCode());
                    System.out.println("[SUCCESS] Instructor " + name + " assigned to " + selected.getCourseName());
                } else {
                    System.out.println("[ERROR] Invalid choice.");
                }
            } else {
                System.out.println("[ERROR] Section not found.");
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Assignment failed: " + e.getMessage());
        }
    }
}