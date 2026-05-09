package org.system.controllers.subcontrollers;

import org.system.entities.Department;
import java.util.List;
import java.util.Scanner;

public class CollegeSubController {
    private final Scanner sc = new Scanner(System.in);

    public void handleManagement(List<Department> university) {
        while (true) {
            System.out.println("\n--- COLLEGE & COURSE MANAGEMENT ---");
            System.out.println("[A] Add New Department");
            System.out.println("[B] Add Course to Department (e.g. BSCS)");
            System.out.println("[C] Remove Department");
            System.out.println("[D] Back to Main Menu");
            System.out.print("Selection: ");
            String sub = sc.nextLine().toUpperCase().trim();

            if (sub.equals("D")) break;

            switch (sub) {
                case "A": performAddDept(university); break;
                case "B": performAddCourse(university); break;
                case "C": performRemoveDept(university); break;
                default: System.out.println("[ERROR] Invalid selection.");
            }
        }
    }

    private void performAddDept(List<Department> university) {
        System.out.print("Enter New Department Name: ");
        String name = sc.nextLine().trim();
        university.add(new Department(name));
        System.out.println("[SUCCESS] Department '" + name + "' added.");
    }

    public void handleDepartmentCRUD(List<Department> university) {
        System.out.println("\n--- DEPARTMENT MANAGEMENT ---");
        System.out.println("[A] Add New Department");
        System.out.println("[B] Remove Department");
        System.out.println("[C] Back");
        System.out.print("Selection: ");
        String sub = sc.nextLine().toUpperCase().trim();

        if (sub.equals("A")) {
            System.out.print("Enter Department Name: ");
            String name = sc.nextLine().trim();
            university.add(new Department(name));
            System.out.println("[SUCCESS] Created department: " + name);
        } else if (sub.equals("B")) {
            System.out.print("Enter Department Name to remove: ");
            String name = sc.nextLine().trim();
            university.removeIf(d -> d.getName().equalsIgnoreCase(name));
            System.out.println("[SUCCESS] Removed department if it existed.");
        }
    }

    private void performAddCourse(List<Department> university) {
        if (university.isEmpty()) {
            System.out.println("[ERROR] Create a department first.");
            return;
        }

        System.out.println("Select Department:");
        for (int i = 0; i < university.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + university.get(i).getName());
        }

        try {
            int deptIdx = Integer.parseInt(sc.nextLine()) - 1;
            System.out.print("Enter Course/Program Name (e.g., BSCS): ");
            String courseName = sc.nextLine().toUpperCase().trim();

            // Assuming your Department entity has a list of Courses or Programs
            university.get(deptIdx).getPrograms().add(courseName);
            System.out.println("[SUCCESS] " + courseName + " added to " + university.get(deptIdx).getName());
        } catch (Exception e) {
            System.out.println("[ERROR] Invalid selection.");
        }
    }

    private void performRemoveDept(List<Department> university) {
        System.out.print("Enter Department Name to Remove: ");
        String name = sc.nextLine().trim();
        boolean removed = university.removeIf(d -> d.getName().equalsIgnoreCase(name));

        if (removed) System.out.println("[SUCCESS] Department removed.");
        else System.out.println("[ERROR] Department not found.");
    }
}