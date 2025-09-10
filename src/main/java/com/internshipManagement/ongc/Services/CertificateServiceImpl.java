package com.internshipManagement.ongc.Services;

import com.internshipManagement.ongc.DTO.CertificateDTO;
import com.internshipManagement.ongc.Model.Certificate;
import com.internshipManagement.ongc.Model.Interns;
import com.internshipManagement.ongc.mapper.CertificateMapper;
import com.internshipManagement.ongc.repository.CertificateRepository;
import com.internshipManagement.ongc.repository.InternRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {

    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private InternRepository internRepository;

    @Override
    public CertificateDTO createCertificate(CertificateDTO dto) {
        Interns intern = internRepository.findById(dto.getInternId())
                .orElseThrow(() -> new RuntimeException("Intern not found"));

        if (Boolean.FALSE.equals(intern.getRecommendedForCertificate())) {
            throw new RuntimeException("Intern is not recommended for certificate.");
        }

        Certificate certificate = CertificateMapper.toEntity(dto);
        certificate.setStatus("UNDER_PROCESSING");
        certificate.setCertificateNumber(generateCertificateNumber());

        Certificate saved = certificateRepository.save(certificate);
        return CertificateMapper.toDTO(saved);
    }

    @Override
    public List<CertificateDTO> getAllCertificates() {
        return certificateRepository.findAll().stream()
                .map(CertificateMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CertificateDTO getCertificateById(String id) {
        return certificateRepository.findById(id)
                .map(CertificateMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));
    }

    @Override
    public List<CertificateDTO> getCertificatesByInternId(String internId) {
        return certificateRepository.findByInternId(internId).stream()
                .map(CertificateMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public CertificateDTO updateCertificate(String id, CertificateDTO dto) {
        Certificate existing = certificateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));

        if (dto.getStatus() != null) {
            String status = dto.getStatus().toUpperCase();

            if (!status.equals("NO_RESPONSE") && !status.equals("UNDER_PROCESSING")
                    && !status.equals("ISSUED") && !status.equals("NOT_ISSUED")) {
                throw new RuntimeException("Invalid certificate status. Allowed: NO_RESPONSE, UNDER_PROCESSING, ISSUED, NOT_ISSUED");
            }

            existing.setStatus(status);
        }

        if (dto.getRemarks() != null) {
            existing.setRemarks(dto.getRemarks());
        }

        if (dto.getRecommendedDate() != null) {
            existing.setRecommendedDate(dto.getRecommendedDate());
        }

        Certificate updated = certificateRepository.save(existing);
        return CertificateMapper.toDTO(updated);
    }


    @Override
    public void deleteCertificate(String id) {
        if (!certificateRepository.existsById(id)) {
            throw new RuntimeException("Certificate not found");
        }
        certificateRepository.deleteById(id);
    }

    private String generateCertificateNumber() {
        return "ONGC-CERT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
    @Override
    public List<CertificateDTO> getCertificatesByStatus(String status) {
        String upperStatus = status.toUpperCase();
        if (!upperStatus.equals("NO_RESPONSE") && !upperStatus.equals("UNDER_PROCESSING")
                && !upperStatus.equals("ISSUED") && !upperStatus.equals("NOT_ISSUED")) {
            throw new RuntimeException("Invalid status filter.");
        }
        return certificateRepository.findByStatus(upperStatus).stream()
                .map(CertificateMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public List<CertificateDTO> getCertificatesByInternIdAndStatus(String internId, String status) {
        String upperStatus = status.toUpperCase();
        if (!upperStatus.equals("NO_RESPONSE") && !upperStatus.equals("UNDER_PROCESSING")
                && !upperStatus.equals("ISSUED") && !upperStatus.equals("NOT_ISSUED")) {
            throw new RuntimeException("Invalid status filter.");
        }
        return certificateRepository.findByInternIdAndStatus(internId, upperStatus).stream()
                .map(CertificateMapper::toDTO)
                .collect(Collectors.toList());
    }


}
