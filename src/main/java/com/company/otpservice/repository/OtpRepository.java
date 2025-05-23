package com.company.otpservice.repository;

import com.company.otpservice.model.OtpRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<OtpRecord, String> {

    Optional<OtpRecord> findTopByMobileNumberOrderByCreatedAtDesc(String mobileNumber);

    boolean existsByMobileNumber(String mobileNumber);

}
