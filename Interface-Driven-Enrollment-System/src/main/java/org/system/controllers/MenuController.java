package org.system.controllers;

import org.system.entities.*;
import org.system.services.*;
import org.system.controllers.subcontrollers.*;

import java.util.List;
import java.util.Scanner;

public class MenuController {
    private final Scanner sc = new Scanner(System.in);
    private final StudentSubController studentSub;
    private final PaymentSubController paymentSub;
    private final InstructorSubController instructorSub;
    private final CourseSubController courseSub;
    private final DepartmentDeanController dean;
    private final TuitionServiceImpl tuitionService;
    private final SectionSubController sectionSub;

    public MenuController(RegistrarController reg, DepartmentDeanController dean, TuitionServiceImpl tuition,
                          List<Department> university) {
        this.studentSub = new StudentSubController(reg);
        this.paymentSub = new PaymentSubController();
        this.instructorSub = new InstructorSubController();
        this.courseSub = new CourseSubController();
        this.sectionSub = new SectionSubController(university);
        this.dean = dean;
        this.tuitionService = tuition;
    }

    public void start(List<Department> university) {
        while (true) {
            displayMainMenu();
            String choice = sc.nextLine().trim();

            if (choice.equals("7")) {
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
                    instructorSub.handleAssignment(university);
                    break;
                case "5":
                    courseSub.handleCourseManagement();
                    break;
                case "6":
                    sectionSub.handleSectionManagement(); break;
                default:
                    System.out.println("[ERROR] Invalid selection. Please choose 1-6.");
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("\n--- COLLEGE MANAGEMENT SYSTEM ---");
        System.out.println("[1] Student Management");
        System.out.println("[2] Payment & Balance");
        System.out.println("[3] View Institutional Hierarchy");
        System.out.println("[4] Assign Instructor to Section");
        System.out.println("[5] Course Catalog Management");
        System.out.println("[6] Section Management");
        System.out.println("[7] Exit");
        System.out.print("Selection: ");
    }

    private void handleHierarchy(List<Department> university) {
        while (true) {
            System.out.print("\nEnter Dept (CITE, CEAS, CBEAM, CON) or 'back': ");
            String input = sc.nextLine().toUpperCase().trim();

            if (input.equals("BACK")) return;

            boolean found = false;
            for (Department d : university) {
                if (d.getName().equals(input)) {

                    dean.displayDepartmentStructure(d);
                    found = true;
                    return;
                }
            }
            if (!found) System.out.println("[ERROR] Department not found.");
        }
    }
}