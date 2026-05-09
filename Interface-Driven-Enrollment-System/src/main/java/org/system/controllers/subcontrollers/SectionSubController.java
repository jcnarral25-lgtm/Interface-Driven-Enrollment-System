package org.system.controllers.subcontrollers;

import org.system.entities.Department;
import org.system.entities.Section;
import org.system.services.SectionServiceImpl;
import java.util.List;
import java.util.Scanner;

public class SectionSubController {
    private final Scanner sc = new Scanner(System.in);
    private final SectionServiceImpl sectionService;


    public SectionSubController() {
        this.sectionService = new SectionServiceImpl();
    }

    public void handleSectionManagement(List<Department> university) {

}

    public void handleSectionManagement() {
        while (true) {
            System.out.println("\n--- SECTION MANAGEMENT ---");
            System.out.println("[1] Add New Section");
            System.out.println("[2] Remove Section");
            System.out.println("[3] View All Sections");
            System.out.println("[4] Back");
            System.out.print("Selection: ");
            String choice = sc.nextLine();

            if (choice.equals("4")) break;

            switch (choice) {
                case "1": performAdd(); break;
                case "2": performRemove(); break;
                case "3": viewAll(); break;
                default: System.out.println("[ERROR] Invalid choice.");
            }
        }
    }

    private void performAdd() {
        System.out.print("Target Department (CITE, CEAS, CBEAM, CON): ");
        String dept = sc.nextLine().toUpperCase();
        System.out.print("New Section Code (e.g., IT1A): ");
        String code = sc.nextLine().toUpperCase();
        System.out.print("Max Capacity: ");
        int cap = Integer.parseInt(sc.nextLine());

        sectionService.addSection(dept, new Section(code, cap));
    }

    private void performRemove() {
        System.out.print("Enter Section Code to remove: ");
        sectionService.removeSection(sc.nextLine().toUpperCase());
        System.out.println("[SUCCESS] Removal process finished.");
    }

    private void viewAll() {
        for (Section s : sectionService.getAllSections()) {
            System.out.println("Section: " + s.getSectionCode() + " | Capacity: " + s.getEnrolledStudents().size()
                    + "/" + s.getMaxCapacity());
        }
    }
}