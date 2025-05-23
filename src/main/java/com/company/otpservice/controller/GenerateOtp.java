package com.company.otpservice.controller;

import com.company.otpservice.model.OtpRecord;
import com.company.otpservice.repository.OtpRepository;
import com.company.otpservice.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController

@RequestMapping("/generate-otp")
public class GenerateOtp {

    @Autowired
    private OtpRepository otpRepository;
    @Autowired
    private OTPService otpService;


    @PostMapping
    public ResponseEntity<Map<String, String>> generateOtp(@RequestParam String mobileNumber) {
        if (mobileNumber == null || !mobileNumber.matches("\\d{10}")) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid mobile number"));
        }

//        Optional<OtpRecord> existingRecord = otpRepository.findTopByMobileNumberOrderByCreatedAtDesc(mobileNumber);


//        if (existingRecord.isPresent()) {
//            OtpRecord record = existingRecord.get();
//            if()
//        }
        if (otpRepository.existsByMobileNumber(mobileNumber)) {
            return ResponseEntity.badRequest().body(Map.of("error", "Mobile number already registered"));
        }


//        String otp = String.format("%04d", new Random().nextInt(10000));
        String otp= otpService.generateMixedCaseOTP(4);
        String uuid = UUID.randomUUID().toString();

        OtpRecord record = new OtpRecord();
        record.setUuid(uuid);
        record.setMobileNumber(mobileNumber);
        record.setOtp(otp);
        record.setCreatedAt(otpService.formatTime());
        record.setValidated(false);

        otpRepository.save(record); // Saves record in PostgreSQL

        Map<String, String> response = new HashMap<>();
        response.put("uuid", uuid);
//        response.put("otp", otp); // You might want to remove this in production
        response.put("message", "OTP generated and saved");


        return ResponseEntity.ok(response);
    }
}
