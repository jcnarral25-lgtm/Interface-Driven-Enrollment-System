package org.system.utils;
import org.system.entities.*;
import java.util.*;

public class CollegeFactory {
    public static List<Department> createDLSLHierarchy() {
        List<Department> university = new ArrayList<>();

        Department cite = new Department("CITE");
        addSections(cite, "IT1", new String[]{"Programming 1", "Intro to Computing"});
        addSections(cite, "IT2", new String[]{"Discrete Math", "HCI", "Information Management"});
        university.add(cite);

        Department cbeam = new Department("CBEAM");
        addSections(cbeam, "ACT1", new String[]{"Accounting 1"});
        university.add(cbeam);

        Department ceas = new Department("CEAS");
        addSections(ceas, "G1", new String[]{"Understanding the Self"});
        university.add(ceas);

        // Hardcoded professor for Infoman
        for (Section s : cite.getSections()) {
            if (s.getSectionCode().contains("Information Management")) {
                s.setAssignedInstructor(new Instructor("INS-001", "Dominador Malasmas"));
            }
        }

        return university;
    }

    private static void addSections(Department d, String pre, String[] subs) {
        for (String s : subs) {
            for (char c = 'A'; c <= 'D'; c++) d.addSection(new Section(pre + c + " - " + s, 30));
        }
    }
}