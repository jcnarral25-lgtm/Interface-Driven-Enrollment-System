package org.system.controllers.subcontrollers;

import org.system.entities.Department;
import org.system.entities.Section;
import org.system.services.SectionServiceImpl;
import java.util.List;
import java.util.Scanner;

public class SectionSubController {
    private final Scanner sc = new Scanner(System.in);
    private final SectionServiceImpl sectionService;

    // FIXED: Constructor now accepts the university list to match MenuController
    public SectionSubController(List<Department> university) {
        // We initialize the service here so it's ready before any methods are called
        this.sectionService = new SectionServiceImpl(university);
    }

    public void handleSectionManagement(List<Department> university) {
        // Note: You can keep the service initialization here as a safety net,
        // but it's now handled by the constructor above.
        while (true) {
            System.out.println("\n--- SECTION MANAGEMENT ---");
            System.out.println("[1] Add New Section");
            System.out.println("[2] Remove Section");
            System.out.println("[3] Edit Section Capacity");
            System.out.println("[4] View All Sections");
            System.out.println("[5] Back");
            System.out.print("Selection: ");

            String choice = sc.nextLine().trim();

            if (choice.equals("5")) break;

            switch (choice) {
                case "1":
                    performAdd();
                    break;
                case "2":
                    performRemove();
                    break;
                case "3":
                    performEdit();
                    break;
                case "4":
                    viewAll();
                    break;
                default:
                    System.out.println("[ERROR] Invalid selection.");
            }
        }
    }

    private void performAdd() {
        try {
            System.out.print("Target Department (CITE, CEAS, CBEAM, CON): ");
            String dept = sc.nextLine().toUpperCase().trim();
            System.out.print("New Section Code (e.g., IT1A): ");
            String code = sc.nextLine().toUpperCase().trim();
            System.out.print("Max Capacity: ");
            int cap = Integer.parseInt(sc.nextLine());

            // Passing the section object to the service
            sectionService.addSection(dept, new Section(code, cap));
            System.out.println("[SUCCESS] Section " + code + " added to " + dept);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Capacity must be a number.");
        } catch (Exception e) {
            System.out.println("[ERROR] Could not add section. Reason: " + e.getMessage());
        }
    }

    private void performRemove() {
        System.out.print("Enter Section Code to remove: ");
        String code = sc.nextLine().toUpperCase().trim();
        sectionService.removeSection(code);
        System.out.println("[SUCCESS] Removal process finished for: " + code);
    }

    private void performEdit() {
        System.out.print("Enter Section Code to edit: ");
        String code = sc.nextLine().toUpperCase().trim();
        Section section = sectionService.getSectionByCode(code);

        if (section != null) {
            try {
                System.out.print("Enter New Max Capacity (Current: " + section.getMaxCapacity() + "): ");
                int newCap = Integer.parseInt(sc.nextLine());
                section.setMaxCapacity(newCap);
                System.out.println("[SUCCESS] Section updated.");
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Invalid number entered.");
            }
        } else {
            System.out.println("[ERROR] Section not found.");
        }
    }

    private void viewAll() {
        List<Section> sections = sectionService.getAllSections();
        if (sections == null || sections.isEmpty()) {
            System.out.println("No sections available.");
            return;
        }
        System.out.println("\n--- LIST OF SECTIONS ---");
        for (Section s : sections) {
            // Using enrolledStudents.size() to show current vs max capacity
            int currentEnrolled = (s.getEnrolledStudents() != null) ? s.getEnrolledStudents().size() : 0;
            System.out.println("Code: " + s.getSectionCode() + " | Capacity: " + currentEnrolled + "/" + s.getMaxCapacity());
        }
    }
}