package org.system;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.system.services.*;

public class EnrollmentSystemTest {

    @Test
    public void testTuitionMath() {
        TuitionServiceImpl tuition = new TuitionServiceImpl();
        tuition.recordUnits("S01", 3);
        tuition.calculateFee(3, false);
        assertTrue(tuition.getRemainingBalance("S01") > 0);
    }

    @Test
    public void testScholarshipDiscount() {
        TuitionServiceImpl t1 = new TuitionServiceImpl();
        TuitionServiceImpl t2 = new TuitionServiceImpl();

        t1.calculateFee(3, false);
        t2.calculateFee(3, true);

        assertTrue(t2.getRemainingBalance("S02") < t1.getRemainingBalance("S01"));
    }
}