package org.system.controllers.subcontrollers;

import org.system.services.TuitionServiceImpl;
import java.util.Scanner;

public class PaymentSubController {
    private final Scanner sc = new Scanner(System.in);

    public void handlePayment(TuitionServiceImpl tuitionService) {
        while (true) {
            System.out.print("\nEnter Student ID (or 'back'): ");
            String searchId = sc.nextLine().trim();
            if (searchId.equalsIgnoreCase("back")) return;

            if (tuitionService.hasRecord(searchId)) {
                System.out.println("Balance: ₱" + String.format("%.2f", tuitionService.getRemainingBalance()));
                System.out.print("Payment Amount: ");
                try {
                    double amt = Double.parseDouble(sc.nextLine());
                    tuitionService.makePayment(amt);
                    System.out.println("Payment Processed.");
                    break;
                } catch (Exception e) { System.out.println("[ERROR] Invalid amount."); }
            } else System.out.println("[ERROR] No record found.");
        }
    }
}