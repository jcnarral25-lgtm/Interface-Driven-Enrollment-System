package org.system.controllers;

import org.system.controllers.subcontrollers.*;
import org.system.entities.Department;
import org.system.services.TuitionServiceImpl;
import java.util.List;
import java.util.Scanner;

public class RegistrarController {
    private final Scanner sc = new Scanner(System.in);

    private final StudentSubController studentSub;
    private final InstructorSubController instructorSub;
    private final PaymentSubController paymentSub;
    private final CollegeSubController collegeSub;
    private final SectionSubController sectionSub;
    private final CourseSubController courseSub;

    // FIXED: Added List<Department> university to the constructor parameters
    public RegistrarController(List<Department> university) {
        this.studentSub = new StudentSubController(this);

        // FIXED: Passing university to SubControllers that now require it
        this.instructorSub = new InstructorSubController(university);
        this.sectionSub = new SectionSubController(university);
        this.courseSub = new CourseSubController(university);

        // These stay as-is if they don't have a university-based constructor yet
        this.paymentSub = new PaymentSubController();
        this.collegeSub = new CollegeSubController();
    }

    public void showMenu(List<Department> university, TuitionServiceImpl tuitionService) {
        while (true) {
            System.out.println("\n--- COLLEGE MANAGEMENT SYSTEM ---");
            System.out.println("[1] Student Management");
            System.out.println("[2] Payment & Balance");
            System.out.println("[3] View Institutional Hierarchy");
            System.out.println("[4] Instructor Management");
            System.out.println("[5] Course Catalog Management");
            System.out.println("[6] Section Management");
            System.out.println("[7] Department Management");
            System.out.println("[8] Exit");
            System.out.print("Selection: ");

            String choice = sc.nextLine().trim();

            if (choice.equals("8")) {
                System.out.println("Logging out...");
                break;
            }

            switch (choice) {
                case "1":
                    studentSub.handleManagement(university, tuitionService);
                    break;
                case "2":
                    paymentSub.handlePayment(tuitionService);
                    break;
                case "3":
                    handleHierarchy(university);
                    break;
                case "4":
                    // Ensure method name matches InstructorSubController
                    instructorSub.handleInstructorManagement(university);
                    break;
                case "5":
                    courseSub.handleCourseManagement(university);
                    break;
                case "6":
                    sectionSub.handleSectionManagement(university);
                    break;
                case "7":
                    collegeSub.handleDepartmentCRUD(university);
                    break;
                default:
                    System.out.println("[ERROR] Invalid selection.");
            }
        }
    }

    private void handleHierarchy(List<Department> university) {
        System.out.println("\n--- INSTITUTIONAL HIERARCHY ---");
        if (university.isEmpty()) {
            System.out.println("No departments found.");
            return;
        }
        for (Department dept : university) {
            System.out.println("Department: " + dept.getName());
        }
    }
}