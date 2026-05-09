package org.system.controllers.subcontrollers;

import org.system.entities.*;
import org.system.controllers.HumanResourceController;
import java.util.*;

public class InstructorSubController {
    private final Scanner sc = new Scanner(System.in);
    private final HumanResourceController hr = new HumanResourceController();

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
                    if (s.getSectionCode().toLowerCase().contains(course.toLowerCase())) {
                        matches.add(s);
                    }
                }
            }

            if (matches.isEmpty()) {
                System.out.println("[ERROR] Course not found.");
                continue;
            }

            for (Section s : matches) {
                System.out.println(" > " + s.getSectionCode().split(" - ")[0]);
            }

            System.out.print("Target Section: ");
            String sec = sc.nextLine().toUpperCase().trim();

            for (Section s : matches) {
                if (s.getSectionCode().startsWith(sec)) {
                    Instructor instructor = new Instructor("INS-" + (new Random().nextInt(900) + 100), name);
                    hr.assignInstructorToSection(instructor, s);
                    return;
                }
            }
            System.out.println("[ERROR] Invalid section.");
        }
    }
}