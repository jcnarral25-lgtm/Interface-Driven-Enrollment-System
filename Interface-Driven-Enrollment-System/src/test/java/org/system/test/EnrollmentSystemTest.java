package org.system.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.system.entities.*;
import org.system.services.*;
import org.system.interfaces.*;
import org.system.exceptions.SectionFullException;

public class EnrollmentSystemTest {

    @Test
    public void testSectionFullException() {
        // ARRANGE
        IEnrollmentService service = new EnrollmentServiceImpl();
        Section section = new Section("IT1A", 1); // Only 1 slot
        Student s1 = new Student("S01", "Alice", "BSIT");
        Student s2 = new Student("S02", "Bob", "BSIT");

        // ACT & ASSERT
        try {
            service.enrollStudentInSection(s1, section); // Should pass
        } catch (SectionFullException e) {
            fail("Should not have thrown exception for first student.");
        }

        // Assert that the second enrollment throws the Custom Exception
        assertThrows(SectionFullException.class, () -> {
            service.enrollStudentInSection(s2, section);
        }, "Expected SectionFullException to be thrown!");
    }

    @Test
    public void testTuitionMath() {
        TuitionServiceImpl tuition = new TuitionServiceImpl();
        tuition.recordUnits("S01", 3);
        tuition.calculateFee(3);
        assertTrue(tuition.getRemainingBalance() > 0);
    }

    // (Other 3 tests: Payment, Duplicate, Instructor remain the same)
}