package com.internshipManagement.ongc.repository;

import com.internshipManagement.ongc.Model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, String> {

    List<Certificate> findByInternId(String internId);

    // 🩶 New: Filter by status (ISSUED, UNDER_PROCESSING, NO_RESPONSE, NOT_ISSUED)
    List<Certificate> findByStatus(String status);

    // 🩶 New: Filter by internId + status (for frontend dashboard filtering)
    List<Certificate> findByInternIdAndStatus(String internId, String status);

}
