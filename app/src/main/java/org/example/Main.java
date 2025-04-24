package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        displayWelcome();

        while (true) {
            System.out.print("\nEnter ID number (or 'quit' to exit): ");
            String input = scanner.nextLine().trim();

            if ("quit".equalsIgnoreCase(input)) {
                break;
            }

            handleIdInput(input);
        }

        scanner.close();
        System.out.println("\nGoodbye, have a great day!");
    }

    private static void displayWelcome() {
        System.out.println("South African ID Validator");
        System.out.println("--------------------------");
    }

    private static void handleIdInput(String idNumber) {
        if (ValidateSaid.isValid(idNumber)) {
            displayValidIdInfo(idNumber);
        } else {
            displayInvalidIdWarning();
        }
    }

    private static void displayValidIdInfo(String id) {
        System.out.println("\n Valid SA ID Number");
        System.out.println("Gender: " + ValidateSaid.getGender(id));
        System.out.println("Birth Date: " + ValidateSaid.getBirthDate(id));

        System.out.println("\nID Breakdown:");
        System.out.printf("YYMMDD: %s%n", id.substring(0, 6));
        System.out.printf("Citizenship: %s%n", 
            id.charAt(10) == '0' ? "SA Citizen" : "Permanent Resident");
    }

    private static void displayInvalidIdWarning() {
        System.out.println("\n Invalid SA ID Number");
        System.out.println("Possible issues:");
        System.out.println("- Incorrect length (must be 13 digits)");
        System.out.println("- Invalid date format");
        System.out.println("- Invalid citizenship digit");
    }
}
