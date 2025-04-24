package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printWelcomeMessage();

        String input;
        while (true) {
            System.out.print("\nEnter ID number (or 'quit' to exit): ");
            input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("quit")) {
                break;
            }

            processIdInput(input);
        }

        scanner.close();
        System.out.println("\nGoodbye, have a great day!");
    }

    private static void printWelcomeMessage() {
        System.out.println("South African ID Validator");
        System.out.println("--------------------------");
    }

    private static void processIdInput(String idNumber) {
        if (ValidateSaid.isValid(idNumber)) {
            printValidIdDetails(idNumber);
        } else {
            printInvalidIdMessage();
        }
    }

    private static void printValidIdDetails(String id) {
        System.out.println("\n Valid SA ID Number");
        System.out.println("Gender: " + ValidateSaid.getGender(id));
        System.out.println("Birth Date: " + ValidateSaid.getBirthDate(id));
        System.out.println("\nID Breakdown:");
        System.out.println("YYMMDD: " + id.substring(0, 6));
        System.out.println("Citizenship: " + 
            (id.charAt(10) == '0' ? "SA Citizen" : "Permanent Resident"));
    }

    private static void printInvalidIdMessage() {
        System.out.println("\n Invalid SA ID Number");
        System.out.println("Possible issues:");
        System.out.println("- Incorrect length (must be 13 digits)");
        System.out.println("- Invalid date format");
        System.out.println("- Invalid citizenship digit");
    }
}