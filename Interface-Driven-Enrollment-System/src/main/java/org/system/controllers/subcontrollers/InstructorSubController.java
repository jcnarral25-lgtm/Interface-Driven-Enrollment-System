package org.system.controllers.subcontrollers;

import org.system.entities.*;
import java.util.*;

public class InstructorSubController {
    private final Scanner sc = new Scanner(System.in);

    public void handleAssignment(List<Department> university) {
        while (true) {
            System.out.print("\nInstructor Name (or 'back'): ");
            String name = sc.nextLine().trim();
            if (name.equalsIgnoreCase("back")) return;

            System.out.print("Search Course: ");
            String course = sc.nextLine().trim();

            List<Section> matches = new ArrayList<>();
            for (Department d : university) {
                for (Section s : d.getSections()) {
                    if (s.getSectionCode().toLowerCase().contains(course.toLowerCase())) matches.add(s);
                }
            }

            if (matches.isEmpty()) { System.out.println("[ERROR] Course not found."); continue; }
            for (Section s : matches) System.out.println(" > " + s.getSectionCode().split(" - ")[0]);

            System.out.print("Target Section: ");
            String sec = sc.nextLine().toUpperCase().trim();
            for (Section s : matches) {
                if (s.getSectionCode().startsWith(sec)) {
                    s.setAssignedInstructor(new Instructor("INS-1", name));
                    System.out.println("[SUCCESS] Instructor Assigned.");
                    return;
                }
            }
            System.out.println("[ERROR] Invalid section.");
        }
    }
}