package org.system.controllers;

import org.system.entities.*;
import org.system.interfaces.IEnrollmentService;
import org.system.exceptions.SectionFullException;

public class RegistrarController {
    private IEnrollmentService service;

    public RegistrarController(IEnrollmentService service) {
        this.service = service;
    }

    public void enrollStudent(String id, String name, String prog, Section sec) throws SectionFullException {
        service.enrollStudentInSection(new Student(id, name, prog), sec);
    }
}