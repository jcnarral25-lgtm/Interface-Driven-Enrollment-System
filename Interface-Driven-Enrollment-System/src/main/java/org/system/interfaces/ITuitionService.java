package org.system.interfaces;

public interface ITuitionService {
    double calculateFee(int units, boolean isScholar);
    void makePayment(double amount);
    double getRemainingBalance(String id);
    void recordUnits(String id, int units);
}