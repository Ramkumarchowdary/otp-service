////package com.company.otpservice.model;
////
////public class OtpRecord {
////}
//package com.company.otpservice.model;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "otp_record") // optional: specify table name
//public class OtpRecord {
//
//    @Id
//    private String uuid;
//
//    private String mobileNumber;
//    private String otp;
//    @Column(name = "created_at")
//    private LocalDateTime createdAt;
//    private boolean validated;
//    private String Region;
//
//
//    // Constructors
//    public OtpRecord() {}
//
//    public OtpRecord(String uuid, String mobileNumber, String otp, LocalDateTime createdAt, boolean validated,String Region) {
//        this.uuid = uuid;
//        this.mobileNumber = mobileNumber;
//        this.otp = otp;
//        this.createdAt = createdAt;
//        this.validated = validated;
//        this.Region=Region;
//    }
//    // Getter
//    public boolean isValidated(boolean b) {
//        return validated;
//    }
//
//    // Setter
//    public void setValidated(boolean validated) {
//        this.validated = validated;
//    }
//    // Getters and Setters
//    public String getUuid() {
//        return uuid;
//    }
//
//    public void setUuid(String uuid) {
//        this.uuid = uuid;
//    }
//
//    public String getMobileNumber() {
//        return mobileNumber;
//    }
//
//    public void setMobileNumber(String mobileNumber) {
//        this.mobileNumber = mobileNumber;
//    }
//
//    public String getOtp() {
//        return otp;
//    }
//
//    public void setOtp(String otp) {
//        this.otp = otp;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//
//
//}
package com.company.otpservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "otp_record")
public class OtpRecord {

    @Id
    private String uuid;

    private String mobileNumber;
    private String otp;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private boolean validated;

    @Column(name = "region")
    private String region;

    public OtpRecord() {}

    public OtpRecord(String uuid, String mobileNumber, String otp, LocalDateTime createdAt, boolean validated, String region) {
        this.uuid = uuid;
        this.mobileNumber = mobileNumber;
        this.otp = otp;
        this.createdAt = createdAt;
        this.validated = validated;
        this.region = region;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
