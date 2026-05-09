package org.system.interfaces;

import org.system.entities.Section;
import java.util.List;

public interface ISectionService {
    void addSection(String deptName, Section section);
    void updateSectionCode(String oldCode, String newCode);
    void removeSection(String sectionCode);
    List<Section> getAllSections();
}