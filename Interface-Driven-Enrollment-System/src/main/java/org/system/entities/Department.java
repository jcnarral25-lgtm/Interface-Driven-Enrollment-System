package org.system.entities;
import java.util.ArrayList;
import java.util.List;

public class Department {
    private String departmentName;
    private List<Section> sections = new ArrayList<>();

    public Department(String name) { this.departmentName = name; }
    public String getName() { return departmentName; }
    public List<Section> getSections() { return sections; }
    public void addSection(Section section) { this.sections.add(section); }
}