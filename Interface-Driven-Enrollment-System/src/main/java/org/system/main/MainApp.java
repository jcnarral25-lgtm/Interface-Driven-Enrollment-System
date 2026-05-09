package org.system.main;
import org.system.entities.Department;
import org.system.services.*;
import org.system.controllers.*;
import org.system.utils.CollegeFactory;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        EnrollmentServiceImpl enrollmentService = new EnrollmentServiceImpl();
        TuitionServiceImpl tuitionService = new TuitionServiceImpl();
        RegistrarController registrar = new RegistrarController(enrollmentService);
        DepartmentDeanController dean = new DepartmentDeanController();
        List<Department> dlsl = CollegeFactory.createDLSLHierarchy();

        MenuController menu = new MenuController(registrar, dean, tuitionService, dlsl);
        menu.start(dlsl);
    }
    }
