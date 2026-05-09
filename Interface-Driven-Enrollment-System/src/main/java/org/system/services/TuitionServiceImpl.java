package org.system.services;

import org.system.interfaces.ITuitionService;
import java.util.HashMap;
import java.util.Map;

public class TuitionServiceImpl implements ITuitionService {
    private double balance = 0.0;
    private static final double BASE_FEE = 5000.0;
    private static final double PRICE_PER_UNIT = 500.0;
    private final Map<String, Integer> studentUnits = new HashMap<>();

    @Override
    public void recordUnits(String id, int units) {
        studentUnits.put(id, units);
    }

    @Override
    public double calculateFee(int units, boolean isScholar) {
        double fee = BASE_FEE + (units * PRICE_PER_UNIT);
        if (isScholar) {
            fee = fee * 0.8;
        }
        this.balance += fee;
        return fee;
    }

    @Override
    public void makePayment(double amount) {
        this.balance -= amount;
    }

    @Override
    public double getRemainingBalance(String id) {
        return this.balance;
    }
}