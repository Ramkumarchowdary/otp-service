package com.company.otpservice.service;

import java.security.SecureRandom;

public class OTPService {

    // Method 5: Mixed case alphanumeric (most secure)
    public static String generateMixedCaseOTP(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < length; i++) {
            otp.append(characters.charAt(random.nextInt(characters.length())));
        }
        return otp.toString();
    }
}
