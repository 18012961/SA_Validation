package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidateSaid {
    public static boolean isValid(String idNumber) {
        // Basic validation
        if (idNumber == null || idNumber.length() != 13 || !idNumber.matches("\\d+")) {
            return false;
        }

        // Date validation (YYMMDD)
        if (!isValidDate(idNumber.substring(0, 6))) {
            return false;
        }

        // Citizenship validation (digit 10)
        int citizenshipDigit = Character.getNumericValue(idNumber.charAt(10));
        if (citizenshipDigit != 0 && citizenshipDigit != 1) {
            return false;
        }

        // Luhn Algorithm validation
        return isValidLuhn(idNumber);
    }

    private static boolean isValidDate(String dateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
            LocalDate.parse(dateStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static boolean isValidLuhn(String idNumber) {
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = Character.getNumericValue(idNumber.charAt(i));
            int weight = (i % 2 == 0) ? 1 : 2;
            int product = digit * weight;
            sum += (product > 9) ? product - 9 : product;
        }
        int checkDigit = Character.getNumericValue(idNumber.charAt(12));
        return (10 - (sum % 10)) % 10 == checkDigit;
    }

    public static String getGender(String idNumber) {
        if (!isValid(idNumber)) return "Invalid ID";
        int genderDigit = Character.getNumericValue(idNumber.charAt(6));
        return (genderDigit < 5) ? "Female" : "Male";
    }

    public static String getBirthDate(String idNumber) {
        if (!isValid(idNumber)) return "Invalid ID";
        try {
            String dateStr = idNumber.substring(0, 6);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
            LocalDate birthDate = LocalDate.parse(dateStr, formatter);
            return birthDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            return "Invalid date in ID";
        }
    }
}