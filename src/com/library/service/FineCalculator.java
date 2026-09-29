package com.library.service;

import com.library.model.MembershipType;

/*
 * FINAL CLASS.
 * Declared final because the fine rules are decided by the library
 * committee - no other class should be allowed to extend this and
 * silently change how the fine is calculated.
 */
public final class FineCalculator {

    // FINAL CONSTANTS
    public static final double PROCESSING_CHARGE = 5.0;
    public static final int GRACE_DAYS = 2;

    // private constructor so that nobody creates an object of a
    // class that only has static helper methods
    private FineCalculator() {
    }

    /*
     * OPERATOR PRECEDENCE demo.
     * In the expression below Java first does the multiplication and
     * only then the addition, i.e. it is read as
     *      (chargeableDays * finePerDay) + PROCESSING_CHARGE
     * If it had been evaluated left to right the answer would be wrong.
     */
    public static double calculateFine(int daysLate, MembershipType type) {
        if (daysLate <= GRACE_DAYS) {
            return 0.0;
        }
        int chargeableDays = daysLate - GRACE_DAYS;
        double fine = chargeableDays * type.getFinePerDay() + PROCESSING_CHARGE;
        return fine;
    }

    // TYPE CASTING: the cash counter does not accept paise,
    // so the double fine is converted into whole rupees.
    public static int roundToRupees(double fine) {
        int rupees = (int) fine;      // narrowing conversion
        if (fine - rupees > 0) {
            rupees = rupees + 1;      // rounding up manually
        }
        return rupees;
    }
}
