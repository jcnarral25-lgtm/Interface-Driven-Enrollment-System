package org.system.controllers.subcontrollers;

import org.system.entities.*;
import org.system.controllers.RegistrarController;
import org.system.services.*;
import org.system.interfaces.*;
import org.system.exceptions.SectionFullException;
import java.util.*;

public class StudentSubController {
    private final Scanner sc = new Scanner(System.in);
    private final IEnrollmentService enrollmentService = new EnrollmentServiceImpl();
    private final IStudentService studentService = new StudentServiceImpl();
    private final RegistrarController registrar;

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
                case "A": performAddEnrollment(university, tuitionService); break;
                case "B": performEditStudent(university); break;
                case "C": performRemoveStudent(university); break;
                default: System.out.println("[ERROR] Invalid selection.");
            }
        }
    }

    private void performAddEnrollment(List<Department> university, TuitionServiceImpl tuitionService) {
        System.out.println("\n--- NEW ENROLLMENT ---");

        System.out.print("Full Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Student ID: ");
        String id = sc.nextLine().trim();
        System.out.print("Program (BSIT, BSN, BSA): ");
        String program = sc.nextLine().toUpperCase().trim();

        System.out.print("Is this student a Scholar? (yes/no): ");
        boolean isScholar = sc.nextLine().equalsIgnoreCase("yes");

        while (true) {
            System.out.print("\nSearch Subject/Section (or 'back'): ");
            String search = sc.nextLine().trim();
            if (search.equalsIgnoreCase("back")) return;

            List<Section> matches = new ArrayList<>();
            for (Department d : university) {
                for (Section s : d.getSections()) {
                    if (s.getSectionCode().toLowerCase().contains(search.toLowerCase())) {
                        matches.add(s);
                    }
                }
            }

            if (matches.isEmpty()) {
                System.out.println("[ERROR] No matching subjects or sections found.");
                continue;
            }

            System.out.println("\nMatches Found:");
            for (int i = 0; i < matches.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + matches.get(i).getSectionCode());
            }

            System.out.print("Select number: ");
            try {
                int choice = Integer.parseInt(sc.nextLine()) - 1;
                if (choice < 0 || choice >= matches.size()) {
                    System.out.println("[ERROR] Invalid selection.");
                    continue;
                }

                Section finalSect = matches.get(choice);

                System.out.print("Enter Units for this subject: ");
                int units = Integer.parseInt(sc.nextLine());

                Student student = new Student(id, name, program);
                studentService.addStudent(student);
                enrollmentService.enrollStudentInSection(student, finalSect);

                tuitionService.recordUnits(id, units);
                tuitionService.calculateFee(units, isScholar);

                System.out.println("[SUCCESS] Enrolled " + name + " in " + finalSect.getSectionCode());
                break;
            } catch (SectionFullException e) {
                System.out.println("[FAILED] " + e.getMessage());
                break;
            } catch (Exception e) {
                System.out.println("[ERROR] Invalid input. Please enter a number.");
            }
        }
    }

    private void performEditStudent(List<Department> university) {
        System.out.print("\nEnter Student ID to Edit: ");
        String id = sc.nextLine().trim();
        System.out.print("Enter New Name: ");
        String newName = sc.nextLine().trim();

        studentService.updateStudent(id, newName);
        for (Department d : university) {
            for (Section s : d.getSections()) {
                for (Student st : s.getEnrolledStudents()) {
                    if (st.getId().equals(id)) st.setName(newName);
                }
            }
        }
        System.out.println("[SUCCESS] Info updated.");
    }

    private void performRemoveStudent(List<Department> university) {
        System.out.print("\nEnter Student ID to Unenroll: ");
        String id = sc.nextLine().trim();

        studentService.removeStudent(id);
        for (Department d : university) {
            for (Section s : d.getSections()) {
                s.getEnrolledStudents().removeIf(st -> st.getId().equals(id));
            }
        }
        System.out.println("[SUCCESS] Student removed.");
    }
}