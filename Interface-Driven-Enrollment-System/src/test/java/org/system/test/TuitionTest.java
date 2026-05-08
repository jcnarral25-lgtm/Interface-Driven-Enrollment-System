package org.system.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.system.services.TuitionServiceImpl;

public class TuitionTest {

    @Test
    public void testTuitionCalculationAndPayment() {
        TuitionServiceImpl service = new TuitionServiceImpl();

        // 3 units * 1000 per unit = 3000.00
        service.calculateFee(3);
        service.makePayment(1000.00);

        assertEquals(2000.00, service.getRemainingBalance(), "Remaining balance should be 2000.00");
    }
}