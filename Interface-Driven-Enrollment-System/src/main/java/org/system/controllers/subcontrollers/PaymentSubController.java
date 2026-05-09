package org.system.controllers.subcontrollers;

import org.system.services.TuitionServiceImpl;
import java.util.Scanner;

public class PaymentSubController {
    private final Scanner sc = new Scanner(System.in);

    public void handlePayment(TuitionServiceImpl tuitionService) {
        while (true) {
            System.out.print("\nEnter Student ID (or 'back'): ");
            String id = sc.nextLine().trim();
            if (id.equalsIgnoreCase("back")) return;

            double balance = tuitionService.getRemainingBalance(id);
            System.out.println("Balance: ₱" + String.format("%.2f", balance));

            while (true) {
                System.out.print("Payment Amount (or 'back'): ");
                String input = sc.nextLine().trim();
                if (input.equalsIgnoreCase("back")) break;

                try {
                    double amount = Double.parseDouble(input);
                    if (amount > 0 && amount <= balance) {
                        tuitionService.makePayment(amount);
                        return;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("[ERROR] Invalid input.");
                }
            }
        }
    }
}