package org.system.entities;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private String name;

    private List<String> programs = new ArrayList<>();
    private List<Section> sections = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }


    public List<String> getPrograms() {
        return programs;
    }

    public String getName() {
        return name;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void addSection(Section section) {
        this.sections.add(section);
    }
}