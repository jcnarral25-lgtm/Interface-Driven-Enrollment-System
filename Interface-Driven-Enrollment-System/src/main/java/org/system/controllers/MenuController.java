package org.system.controllers;

import org.system.entities.Department;
import org.system.controllers.subcontrollers.*;
import org.system.services.TuitionServiceImpl;

import java.util.List;
import java.util.Scanner;

public class MenuController {
    private final Scanner sc = new Scanner(System.in);
    private final StudentSubController studentSub;
    private final PaymentSubController paymentSub;
    private final InstructorSubController instructorSub;
    private final CourseSubController courseSub;
    private final SectionSubController sectionSub;
    private final CollegeSubController collegeSub;
    private final TuitionServiceImpl tuitionService;

    public MenuController(RegistrarController reg, DepartmentDeanController dean, TuitionServiceImpl tuition,
                          List<Department> university) {

        this.studentSub = new StudentSubController(reg);
        this.paymentSub = new PaymentSubController();
        this.instructorSub = new InstructorSubController(university);
        this.courseSub = new CourseSubController(university);
        this.sectionSub = new SectionSubController(university);
        this.collegeSub = new CollegeSubController();
        this.tuitionService = tuition;
    }

    public void start(List<Department> university) {
        while (true) {
            displayMainMenu();
            String choice = sc.nextLine().trim();

            if (choice.equals("8")) {
                System.out.println("Exiting System. Goodbye!");
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

                    instructorSub.handleInstructorManagement(university);
                    break;
                case "5":
                    courseSub.handleCourseManagement(university); // FIXED: Passing university
                    break;
                case "6":
                    sectionSub.handleSectionManagement(university);
                    break;
                case "7":
                    collegeSub.handleDepartmentCRUD(university);
                    break;
                default:
                    System.out.println("[ERROR] Invalid selection. Please choose 1-8.");
            }
        }
    }

    private void displayMainMenu() {
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