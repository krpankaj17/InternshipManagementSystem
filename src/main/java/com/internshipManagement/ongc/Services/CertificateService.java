package com.internshipManagement.ongc.Services;

import com.internshipManagement.ongc.DTO.CertificateDTO;
import java.util.List;

public interface CertificateService {
    CertificateDTO createCertificate(CertificateDTO dto);
    List<CertificateDTO> getAllCertificates();
    CertificateDTO getCertificateById(String id);
    List<CertificateDTO> getCertificatesByInternId(String internId);
    CertificateDTO updateCertificate(String id, CertificateDTO dto);
    void deleteCertificate(String id);
    List<CertificateDTO> getCertificatesByStatus(String status);
    List<CertificateDTO> getCertificatesByInternIdAndStatus(String internId, String status);


}
