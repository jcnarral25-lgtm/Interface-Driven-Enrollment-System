package org.system.controllers.subcontrollers;

import org.system.entities.*;
import org.system.controllers.RegistrarController;
import org.system.services.TuitionServiceImpl;
import org.system.services.EnrollmentServiceImpl;
import org.system.interfaces.IEnrollmentService;
import org.system.exceptions.SectionFullException;
import java.util.*;

public class StudentSubController {
    private final Scanner sc = new Scanner(System.in);
    private final RegistrarController registrar;

    private final IEnrollmentService enrollmentService = new EnrollmentServiceImpl();

    public StudentSubController(RegistrarController registrar) {
        this.registrar = registrar;
    }

    public void handleManagement(List<Department> university, TuitionServiceImpl tuitionService) {
        while (true) {
            System.out.println("\n--- STUDENT MANAGEMENT ---");
            System.out.println("[A] Add New Enrollment");
            System.out.println("[B] Edit Student Info");
            System.out.println("[C] Remove Student");
            System.out.println("[D] Back to Main Menu");
            System.out.print("Selection: ");
            String sub = sc.nextLine().toUpperCase().trim();

            if (sub.equals("D")) break;

            switch (sub) {
                case "A": performAddEnrollment(university, tuitionService); return;
                case "B": performEditStudent(university); return;
                case "C": performRemoveStudent(university); return;
                default: System.out.println("[ERROR] Invalid selection.");
            }
        }
    }

    private void performAddEnrollment(List<Department> university, TuitionServiceImpl tuitionService) {
        System.out.println("\n--- NEW ENROLLMENT ---");

        String name;
        while (true) {
            System.out.print("Full Name (Letters only): ");
            name = sc.nextLine().trim();
            if (name.matches("^[a-zA-Z\\s.]+$") && !name.isEmpty()) break;
            System.out.println("[ERROR] Name cannot contain numbers or symbols.");
        }

        System.out.print("Student ID: ");
        String id = sc.nextLine().trim();

        String program;
        while (true) {
            System.out.print("Program (BSIT, BSN, BSA): ");
            program = sc.nextLine().toUpperCase().trim();
            if (program.equals("BSIT") || program.equals("BSN") || program.equals("BSA")) break;
            System.out.println("[ERROR] Invalid Program.");
        }

        while (true) {
            System.out.print("\nSearch Subject Name (or 'back'): ");
            String search = sc.nextLine().trim();
            if (search.equalsIgnoreCase("back")) return;

            List<Section> matches = new ArrayList<>();
            Department targetDept = null;

            for (Department d : university) {
                for (Section s : d.getSections()) {
                    if (s.getSectionCode().toLowerCase().contains(search.toLowerCase())) {
                        matches.add(s);
                        targetDept = d;
                    }
                }
            }

            if (matches.isEmpty()) {
                System.out.println("[ERROR] No subjects found.");
                continue;
            }

            boolean exists = false;
            for (Section s : matches) {
                for (Student st : s.getEnrolledStudents()) {
                    if (st.getId().equals(id)) { exists = true; break; }
                }
            }
            if (exists) {
                System.out.println("[ERROR] ID " + id + " is already enrolled in this subject.");
                continue;
            }

            if ((targetDept.getName().equals("CITE") && !program.equals("BSIT")) ||
                    (targetDept.getName().equals("CON") && !program.equals("BSN"))) {
                System.out.println("[ACCESS DENIED] This subject is restricted to specific programs.");
                continue;
            }

            System.out.println("\nAvailable Sections:");
            for (Section s : matches) System.out.println(" > " + s.getSectionCode().split(" - ")[0]);

            System.out.print("Select Section (e.g., IT1A): ");
            String selection = sc.nextLine().toUpperCase().trim();

            Section finalSect = null;
            for (Section s : matches) {
                if (s.getSectionCode().startsWith(selection)) { finalSect = s; break; }
            }

            if (finalSect != null) {

                int units = 0;
                while (true) {
                    System.out.print("Enter Units for this subject: ");
                    try {
                        units = Integer.parseInt(sc.nextLine());
                        if (units > 0) break;
                        System.out.println("[ERROR] Units must be at least 1.");
                    } catch (Exception e) {
                        System.out.println("[ERROR] Enter a valid numeric value.");
                    }
                }

                try {

                    Student student = new Student(id, name, program);
                    enrollmentService.enrollStudentInSection(student, finalSect);


                    tuitionService.recordUnits(id, units);
                    tuitionService.calculateFee(units);

                    System.out.println("[SUCCESS] Enrolled " + name + " in " + finalSect.getSectionCode());
                    break;
                } catch (SectionFullException e) {
                    // Catch the custom exception thrown by the service
                    System.out.println("\n[REGISTRATION FAILED] " + e.getMessage());
                    break; // Exit the loop as enrollment failed
                }
            } else {
                System.out.println("[ERROR] Invalid section selection.");
            }
        }
    }

    private void performEditStudent(List<Department> university) {
        while (true) {
            System.out.print("\nEnter Student ID to Edit (or 'back'): ");
            String id = sc.nextLine().trim();
            if (id.equalsIgnoreCase("back")) return;

            for (Department d : university) {
                for (Section s : d.getSections()) {
                    for (Student st : s.getEnrolledStudents()) {
                        if (st.getId().equals(id)) {
                            System.out.println("Current Name: " + st.getName());
                            System.out.print("Enter New Name: ");
                            String newName = sc.nextLine().trim();
                            if (newName.matches("^[a-zA-Z\\s.]+$") && !newName.isEmpty()) {
                                st.setName(newName);
                                System.out.println("[SUCCESS] Student information updated.");
                                return;
                            }
                            System.out.println("[ERROR] Invalid name.");
                            continue;
                        }
                    }
                }
            }
            System.out.println("[ERROR] ID not found.");
        }
    }

    private void performRemoveStudent(List<Department> university) {
        while (true) {
            System.out.print("\nEnter Student ID to Unenroll (or 'back'): ");
            String id = sc.nextLine().trim();
            if (id.equalsIgnoreCase("back")) return;

            for (Department d : university) {
                for (Section s : d.getSections()) {
                    if (s.getEnrolledStudents().removeIf(st -> st.getId().equals(id))) {
                        System.out.println("[SUCCESS] Student removed from " + s.getSectionCode());
                        return;
                    }
                }
            }
            System.out.println("[ERROR] ID not found in any section.");
        }
    }
}