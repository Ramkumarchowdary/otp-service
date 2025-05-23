//package com.company.otpservice.model;
//
//public class OtpRecord {
//}
package com.company.otpservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "otp_record") // optional: specify table name
public class OtpRecord {

    @Id
    private String uuid;

    private String mobileNumber;
    private String otp;
    private LocalDateTime createdAt;
    private boolean validated;


    // Constructors
    public OtpRecord() {}

    public OtpRecord(String uuid, String mobileNumber, String otp, LocalDateTime createdAt, boolean validated) {
        this.uuid = uuid;
        this.mobileNumber = mobileNumber;
        this.otp = otp;
        this.createdAt = createdAt;
        this.validated = validated;
    }
    // Getter
    public boolean isValidated(boolean b) {
        return validated;
    }

    // Setter
    public void setValidated(boolean validated) {
        this.validated = validated;
    }
    // Getters and Setters
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



}
