package org.system.services;
import org.system.interfaces.ITuitionService;
import java.util.HashMap;
import java.util.Map;

public class TuitionServiceImpl implements ITuitionService {
    private double balance = 0;
    private final double BASE_FEE = 15000.00;
    private final double PRICE_PER_UNIT = 1207.00;

    // In-memory storage for ID -> Units lookup
    private Map<String, Integer> studentUnits = new HashMap<>();

    public void recordUnits(String id, int units) {
        studentUnits.put(id, units);
    }

    public boolean hasRecord(String id) {
        return studentUnits.containsKey(id);
    }

    public int getUnits(String id) {
        return studentUnits.getOrDefault(id, 0);
    }

    @Override
    public double calculateFee(int units) {
        double fee = BASE_FEE + (units * PRICE_PER_UNIT);
        this.balance += fee;
        return fee;
    }

    @Override
    public void makePayment(double amount) {
        this.balance -= amount;
        System.out.println("[PAYMENT] ₱" + String.format("%.2f", amount) + " processed.");
    }

    @Override
    public double getRemainingBalance() { return balance; }
}