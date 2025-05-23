package com.company.otpservice.service;

import com.company.otpservice.repository.OtpRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class OTPService {

    private final OtpRepository otpRepository;

    public OTPService(OtpRepository otpRepository) {
        this.otpRepository = otpRepository;
    }

    public String generateMixedCaseOTP(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < length; i++) {
            otp.append(characters.charAt(random.nextInt(characters.length())));
        }
        return otp.toString();
    }

    public LocalDateTime formatTime() {
        return LocalDateTime.now();
    }

    @Scheduled(fixedRate = 300000) // every 5 minutes
    @Transactional
    public void deleteOldOtps() {
        LocalDateTime cutoffTime = LocalDateTime.now().minusMinutes(5);
        int deleted = otpRepository.deleteOldRecords(cutoffTime);
        System.out.println("Deleted " + deleted + " expired OTPs at: " + LocalDateTime.now());
    }
}
