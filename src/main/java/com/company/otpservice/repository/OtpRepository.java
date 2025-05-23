package com.company.otpservice.repository;

import com.company.otpservice.model.OtpRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.time.LocalDateTime;
public interface OtpRepository extends JpaRepository<OtpRecord, String> {

    Optional<OtpRecord> findTopByMobileNumberOrderByCreatedAtDesc(String mobileNumber);
//    void deleteByCreatedAtBefore(LocalDateTime cutoffTime);
    boolean existsByMobileNumber(String mobileNumber);

    @Modifying
    @Query("DELETE FROM OtpRecord o WHERE o.createdAt < :cutoff")
    int deleteOldRecords(@Param("cutoff") LocalDateTime cutoff);

}
