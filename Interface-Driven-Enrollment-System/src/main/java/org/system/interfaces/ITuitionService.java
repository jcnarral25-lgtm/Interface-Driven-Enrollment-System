package org.system.interfaces;

public interface ITuitionService {
    double calculateFee(int units);
    void makePayment(double amount);
    double getRemainingBalance();
}