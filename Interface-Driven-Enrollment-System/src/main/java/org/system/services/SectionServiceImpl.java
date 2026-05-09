package org.system.services;

import org.system.entities.Department;
import org.system.entities.Section;
import org.system.interfaces.ISectionService;
import java.util.ArrayList;
import java.util.List;

public class SectionServiceImpl implements ISectionService {
    private List<Department> university;

    public SectionServiceImpl() {
    }

    @Override
    public void addSection(String deptName, Section section) {
        for (Department d : university) {
            if (d.getName().equalsIgnoreCase(deptName)) {
                d.getSections().add(section);
                System.out.println("[SUCCESS] Section added to " + deptName);
                return;
            }
        }
        System.out.println("[ERROR] Department " + deptName + " not found.");
    }

    @Override
    public void updateSectionCode(String oldCode, String newCode) {
        for (Department d : university) {
            for (Section s : d.getSections()) {
                if (s.getSectionCode().startsWith(oldCode)) {
                    System.out.println("[SUCCESS] Section code updated.");
                    return;
                }
            }
        }
    }

    @Override
    public void removeSection(String sectionCode) {
        for (Department d : university) {
            d.getSections().removeIf(s -> s.getSectionCode().startsWith(sectionCode));
        }
    }

    @Override
    public List<Section> getAllSections() {
        List<Section> all = new ArrayList<>();
        for (Department d : university) all.addAll(d.getSections());
        return all;
    }
}