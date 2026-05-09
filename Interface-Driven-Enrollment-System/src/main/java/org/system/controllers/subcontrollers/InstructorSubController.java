package org.system.controllers.subcontrollers;

import org.system.entities.Instructor;
import org.system.entities.Department;
import org.system.entities.Section;
import java.util.List;
import java.util.Scanner;

public class InstructorSubController {
    private final Scanner sc = new Scanner(System.in);

    // RENAME THIS TO handleAssignment TO MATCH YOUR MENU
    public void handleAssignment(List<Department> university) {
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
                case "A": performAdd(university); break;
                case "B": performEdit(university); break;
                case "C": performRemove(university); break;
                default: System.out.println("[ERROR] Invalid selection.");
            }
        }
    }

    private void performAdd(List<Department> university) {
        System.out.print("Enter Instructor ID: ");
        String id = sc.nextLine().trim();
        System.out.print("Enter Full Name: ");
        String name = sc.nextLine().trim();

        Instructor newInstructor = new Instructor(id, name);

        System.out.print("Assign to Section Code: ");
        String sectionCode = sc.nextLine().trim();

        boolean found = false;
        for (Department d : university) {
            for (Section s : d.getSections()) {
                if (s.getSectionCode().equalsIgnoreCase(sectionCode)) {
                    s.setAssignedInstructor(newInstructor);
                    System.out.println("[SUCCESS] Instructor assigned.");
                    found = true;
                    break;
                }
            }
        }
        if (!found) System.out.println("[ERROR] Section not found.");
    }

    private void performEdit(List<Department> university) {
        System.out.print("Enter Instructor ID to Edit: ");
        String id = sc.nextLine().trim();

        for (Department d : university) {
            for (Section s : d.getSections()) {
                Instructor ins = s.getAssignedInstructor();
                if (ins != null && ins.getId().equals(id)) {
                    System.out.print("Enter New Name: ");
                    ins.setName(sc.nextLine().trim());
                    System.out.println("[SUCCESS] Updated.");
                    return;
                }
            }
        }
        System.out.println("[ERROR] Not found.");
    }

    private void performRemove(List<Department> university) {
        System.out.print("Enter Instructor ID to Remove: ");
        String id = sc.nextLine().trim();

        for (Department d : university) {
            for (Section s : d.getSections()) {
                Instructor ins = s.getAssignedInstructor();
                if (ins != null && ins.getId().equals(id)) {
                    s.setAssignedInstructor(null);
                    System.out.println("[SUCCESS] Removed.");
                    return;
                }
            }
        }
        System.out.println("[ERROR] Not found.");
    }
}