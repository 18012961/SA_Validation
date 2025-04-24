package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test
    void testValidIdNumbers() {
        assertTrue(ValidateSaid.isValid("2001014800086"));
        assertTrue(ValidateSaid.isValid("2909035800085"));
    }

    private void assertTrue(boolean valid) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assertTrue'");
    }

    @Test
    void testInvalidLength() {
        assertFalse(ValidateSaid.isValid("123"));
        assertFalse(ValidateSaid.isValid("2001014800086123"));
    }

    @Test
    void testNonNumeric() {
        assertFalse(ValidateSaid.isValid("20010A4800086"));
        assertFalse(ValidateSaid.isValid("abcdefghijklm"));
    }

    @Test
    void testLuhnAlgorithm() {
        assertTrue(ValidateSaid.isValid("2001014800086"));  // Valid
        assertFalse(ValidateSaid.isValid("2001014800087")); // Invalid
    }

    @Test
    void testDateValidation() {
        assertTrue(ValidateSaid.isValid("9202204800086"));  // Valid date
        assertTrue(ValidateSaid.isValid("0002294800082"));  // Leap year
        assertFalse(ValidateSaid.isValid("9902304800083")); // Invalid day
        assertFalse(ValidateSaid.isValid("9913024800081")); // Invalid month
    }

    @Test
    void testCitizenshipDigit() {
        assertTrue(ValidateSaid.isValid("9202204800086"));  // Citizen (0)
        assertTrue(ValidateSaid.isValid("9202204800183"));  // Resident (1)
        assertFalse(ValidateSaid.isValid("9202204800280")); // Invalid (2)
    }
}
