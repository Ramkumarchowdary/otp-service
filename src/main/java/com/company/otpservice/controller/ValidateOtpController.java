package com.company.otpservice.controller;


import com.company.otpservice.model.OtpRecord;
import com.company.otpservice.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/validate-otp")
public class ValidateOtpController {

    @Autowired
    private OtpRepository otpRepository;

    @PostMapping
    public ResponseEntity<?> validateOtp(@RequestParam String uuid, @RequestParam String otp) {
        Optional<OtpRecord> optionalRecord = otpRepository.findById(uuid);

        if (optionalRecord.isEmpty()) {
            return ResponseEntity.status(404).body("Invalid UUID");
        }

        OtpRecord record = optionalRecord.get();

        if (record.getOtp().equals(otp)) {
            record.setValidated(true);
            otpRepository.save(record);
            return ResponseEntity.ok("OTP is valid");
        } else {
            return ResponseEntity.status(400).body("Invalid OTP");
        }
    }
}
