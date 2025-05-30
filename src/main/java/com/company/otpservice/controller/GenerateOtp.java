//package com.company.otpservice.controller;
//
//import com.company.otpservice.model.OtpRecord;
//import com.company.otpservice.repository.OtpRepository;
//import com.company.otpservice.service.OTPService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.util.*;
//
//@RestController
//
//@RequestMapping("/generate-otp")
//public class GenerateOtp {
//
//    @Autowired
//    private OtpRepository otpRepository;
//    @Autowired
//    private OTPService otpService;
//
//
//    @PostMapping
//    public ResponseEntity<Map<String, String>> generateOtp(@RequestParam String mobileNumber) {
//        if (mobileNumber == null || !mobileNumber.matches("\\d{10}")) {
//            return ResponseEntity.badRequest().body(Map.of("error", "Invalid mobile number"));
//        }
//        if (otpRepository.existsByMobileNumber(mobileNumber)) {
//            return ResponseEntity.badRequest().body(Map.of("error", "Mobile number already registered"));
//        }
//
//
////        String otp = String.format("%04d", new Random().nextInt(10000));
//        String otp= otpService.generateMixedCaseOTP(4);
//        String uuid = UUID.randomUUID().toString();
//
//        OtpRecord record = new OtpRecord();
//        record.setUuid(uuid);
//        record.setMobileNumber(mobileNumber);
//        record.setOtp(otp);
//        record.setCreatedAt(otpService.formatTime());
//        record.setValidated(false);
//
//        otpRepository.save(record); // Saves record in PostgreSQL
//
//        Map<String, String> response = new HashMap<>();
//        response.put("uuid", uuid);
////        response.put("otp", otp); // You might want to remove this in production
//        response.put("message", "OTP generated and saved");
//
//
//        return ResponseEntity.ok(response);
//    }
//}
package com.company.otpservice.controller;

import com.company.otpservice.model.OtpRecord;
import com.company.otpservice.repository.OtpRepository;
import com.company.otpservice.service.OTPService;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/generate-otp")
public class GenerateOtp {

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private OTPService otpService;

    private final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
    String regionCode;
    @PostMapping
    public ResponseEntity<Map<String, String>> generateOtp(@RequestParam String mobileNumber) {
        PhoneNumber phoneNumber;

        try {
            phoneNumber = phoneUtil.parse(mobileNumber, "ZZ");

            if (!phoneUtil.isValidNumber(phoneNumber)) {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid mobile number."));
            }

        } catch (NumberParseException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Number parse error: " + e.getMessage()));
        }

        String e164FormattedNumber = phoneUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164);
         regionCode = phoneUtil.getRegionCodeForNumber(phoneNumber);

        System.out.println("Mobile Number: " + e164FormattedNumber);
        System.out.println("Region: " + regionCode);

        if (otpRepository.existsByMobileNumber(e164FormattedNumber)) {
            return ResponseEntity.badRequest().body(Map.of("error", "Mobile number already registered"));
        }

        String otp = otpService.generateMixedCaseOTP(4);
        String uuid = UUID.randomUUID().toString();

        OtpRecord record = new OtpRecord();

        record.setUuid(uuid);
        record.setMobileNumber(e164FormattedNumber);
        record.setOtp(otp);
        record.setCreatedAt(otpService.formatTime());
        record.setValidated(false);
        record.setRegion(regionCode);


        otpRepository.save(record);

        Map<String, String> response = new HashMap<>();
        response.put("uuid", uuid);
        response.put("message", "OTP generated and saved");
        response.put("region",regionCode);

        return ResponseEntity.ok(response);
    }
}
