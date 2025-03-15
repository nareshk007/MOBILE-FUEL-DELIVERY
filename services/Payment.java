package models;

import java.util.Random;

public class Payment {
    private int otp;

    public int generateOTP() {
        Random rand = new Random();
        otp = 1000 + rand.nextInt(9000);
        return otp;
    }

    public boolean verifyOTP(int enteredOTP) {
        return enteredOTP == otp;
    }
}