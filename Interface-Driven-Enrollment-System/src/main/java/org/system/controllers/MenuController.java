package org.system.controllers;

import org.system.entities.*;
import org.system.services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuController {
    private final Scanner sc = new Scanner(System.in);
    private final RegistrarController registrar;
    private final DepartmentDeanController dean;
    private final TuitionServiceImpl tuitionService;

    public MenuController(RegistrarController reg, DepartmentDeanController dean, TuitionServiceImpl tuition) {
        this.registrar = reg;
        this.dean = dean;
        this.tuitionService = tuition;
    }

    public void start(List<Department> university) {
        while (true) {
            System.out.println("\n--- INTERFACE-DRIVEN ENROLLMENT SYSTEM ---");
            System.out.println("[1] Enroll Student (Program Restricted)");
            System.out.println("[2] Payment & Balance (ID Required)");
            System.out.println("[3] View Institutional Hierarchy");
            System.out.println("[4] Assign Instructor to Section");
            System.out.println("[5] Exit");
            System.out.print("Selection: ");
            String choice = sc.nextLine();

            if (choice.equals("5")) break;

            switch (choice) {
                case "1":
                    handleEnrollment(university);
                    break;
                case "2":
                    handlePayment();
                    break;
                case "3":
                    handleHierarchy(university);
                    break;
                case "4":
                    handleAssignInstructor(university);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void handleEnrollment(List<Department> university) {
        System.out.println("\n--- REGISTRATION ---");
        System.out.print("Full Name: ");
        String name = sc.nextLine();
        System.out.print("Student ID: ");
        String id = sc.nextLine();
        System.out.print("Program (BSIT/BSCS/BSA/BSN): ");
        String prog = sc.nextLine().toUpperCase().trim();

        System.out.println("\n--- AVAILABLE SECTIONS ---");
        for (Department d : university) {
            for (Section s : d.getSections()) System.out.println("  > " + s.getSectionCode());
        }

        System.out.print("\nType exact Course/Section: ");
        String selected = sc.nextLine().trim();

        Section target = null;
        Department targetDept = null;
        for (Department d : university) {
            for (Section s : d.getSections()) {
                if (s.getSectionCode().equalsIgnoreCase(selected)) {
                    target = s;
                    targetDept = d;
                    break;
                }
            }
        }

        if (target != null) {
            // Validation: Only CITE students for CITE courses
            boolean isCiteStudent = (prog.equals("BSIT") || prog.equals("BSCS"));
            if (targetDept.getName().equals("CITE") && !isCiteStudent) {
                System.out.println("[ACCESS DENIED] CITE courses are for BSIT/BSCS only.");
                return;
            }

            System.out.print("Units: ");
            int units = Integer.parseInt(sc.nextLine());
            registrar.enrollStudent(id, name, prog, target);
            tuitionService.recordUnits(id, units);
            tuitionService.calculateFee(units);
            System.out.println("[SUCCESS] Enrolled in " + target.getSectionCode());
        } else {
            System.out.println("[ERROR] Not found.");
        }
    }

    private void handlePayment() {
        System.out.print("\nEnter Student ID: ");
        String searchId = sc.nextLine();
        if (tuitionService.hasRecord(searchId)) {
            System.out.println("\n--- ACCOUNT STATUS ---");
            System.out.println("Units: " + tuitionService.getUnits(searchId));
            System.out.println("Balance: ₱" + String.format("%.2f", tuitionService.getRemainingBalance()));
            System.out.print("Payment Amount: ");
            double amt = Double.parseDouble(sc.nextLine());
            tuitionService.makePayment(amt);
        } else {
            System.out.println("[ERROR] ID not found.");
        }
    }

    private void handleHierarchy(List<Department> university) {
        System.out.print("Enter Dept (CITE, CEAS, CBEAM): ");
        String input = sc.nextLine().toUpperCase();
        for (Department d : university) if (d.getName().equals(input)) dean.displayDepartmentStructure(d);
    }

    private void handleAssignInstructor(List<Department> university) {
        System.out.println("\n--- INSTRUCTOR ASSIGNMENT ---");
        System.out.print("Instructor Name: ");
        String profName = sc.nextLine();

        // 1. Ask for the Course/Subject Name
        System.out.print("Enter Course Name (e.g., Information Management): ");
        String courseName = sc.nextLine().trim();

        // 2. Find and show available sections for that course
        List<Section> availableSections = new ArrayList<>();
        System.out.println("\nAvailable Sections for " + courseName + ":");

        for (Department d : university) {
            for (Section s : d.getSections()) {
                if (s.getSectionCode().contains(courseName)) {
                    availableSections.add(s);
                    // Extract just the section part (e.g., IT2B) for display
                    String shortCode = s.getSectionCode().split(" - ")[0];
                    System.out.println("  > " + shortCode);
                }
            }
        }

        if (availableSections.isEmpty()) {
            System.out.println("[ERROR] No sections found for that course.");
            return;
        }

        // 3. Input the specific section
        System.out.print("\nEnter the specific Section (e.g., IT2B): ");
        String targetSectionCode = sc.nextLine().trim().toUpperCase();

        Section finalTarget = null;
        for (Section s : availableSections) {
            if (s.getSectionCode().startsWith(targetSectionCode)) {
                finalTarget = s;
                break;
            }
        }

        if (finalTarget != null) {
            finalTarget.setAssignedInstructor(new Instructor("INS-NEW", profName));
            System.out.println("\n[SUCCESS] " + profName + " assigned to " + finalTarget.getSectionCode());
        } else {
            System.out.println("[ERROR] Invalid section selection.");
        }
    }
}