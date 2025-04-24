package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidateSaid {

    public static boolean isValid(String idNumber) {
        return isProperLengthAndDigits(idNumber)
                && isValidDatePart(idNumber.substring(0, 6))
                && isValidCitizenshipDigit(idNumber.charAt(10))
                && passesLuhnCheck(idNumber);
    }

    private static boolean isProperLengthAndDigits(String idNumber) {
        return idNumber != null && idNumber.length() == 13 && idNumber.matches("\\d+");
    }

    private static boolean isValidDatePart(String datePart) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
            LocalDate.parse(datePart, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static boolean isValidCitizenshipDigit(char digitChar) {
        int digit = Character.getNumericValue(digitChar);
        return digit == 0 || digit == 1;
    }

    private static boolean passesLuhnCheck(String idNumber) {
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = Character.getNumericValue(idNumber.charAt(i));
            int factor = (i % 2 == 0) ? 1 : 2;
            int product = digit * factor;
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
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyMMdd");
            LocalDate date = LocalDate.parse(dateStr, inputFormat);
            return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            return "Invalid date in ID";
        }
    }
}
