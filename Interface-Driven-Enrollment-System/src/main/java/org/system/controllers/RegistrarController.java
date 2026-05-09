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

    public RegistrarController() {
        this.studentSub = new StudentSubController(this);
        this.instructorSub = new InstructorSubController();
        this.paymentSub = new PaymentSubController();
        this.collegeSub = new CollegeSubController();
        this.sectionSub = new SectionSubController();
        this.courseSub = new CourseSubController();
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
                case "1": studentSub.handleManagement(university, tuitionService); break;
                case "2": paymentSub.handlePayment(tuitionService); break;
                case "3": break;
                case "4": instructorSub.handleAssignment(university); break;
                case "5": break;
                case "6": sectionSub.handleSectionManagement(university); break;
                case "7":

                    collegeSub.handleDepartmentCRUD(university);
                    break;
                default:
                    System.out.println("[ERROR] Invalid selection.");
            }
        }
    }
    }
