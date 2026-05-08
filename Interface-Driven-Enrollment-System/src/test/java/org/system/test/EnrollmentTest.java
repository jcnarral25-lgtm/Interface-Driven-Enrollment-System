package org.system.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.system.entities.*;
import org.system.services.EnrollmentServiceImpl;

public class EnrollmentTest {

    @Test
    public void testSectionCapacityValidation() {
        EnrollmentServiceImpl service = new EnrollmentServiceImpl();
        Section smallSection = new Section("TEST-SEC", 1); // Only 1 slot
        Student student1 = new Student("001", "Alice", "BSCS");
        Student student2 = new Student("002", "Bob", "BSIT");

        // First student should enroll successfully
        boolean firstEnroll = service.enrollStudentInSection(student1, smallSection);
        assertTrue(firstEnroll, "First student should successfully enroll.");

        // Second student enrollment must be rejected
        boolean secondEnroll = service.enrollStudentInSection(student2, smallSection);
        assertFalse(secondEnroll, "Second student enrollment should fail as section is full.");
        assertEquals(1, smallSection.getEnrolledStudents().size());
    }
}