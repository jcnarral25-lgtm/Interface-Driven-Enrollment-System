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
        addSections(cbeam, "A1", new String[]{"Accounting 1"});
        university.add(cbeam);


        Department con = new Department("CON");
        addSections(con, "NUR1", new String[]{"Anatomy and Physiology", "Health Assessment"});
        addSections(con, "NUR2", new String[]{"Pharmacology", "Nursing Care Management"});
        university.add(con);


        Department ceas = new Department("CEAS");
        addSections(ceas, "G1", new String[]{"Understanding the Self"});
        university.add(ceas);


        return university;
    }

    private static void addSections(Department d, String pre, String[] subs) {
        for (String s : subs) {
            for (char c = 'A'; c <= 'D'; c++) d.addSection(new Section(pre + c + " - " + s, 30));
        }
    }
}