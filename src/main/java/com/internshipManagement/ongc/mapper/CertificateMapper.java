package com.internshipManagement.ongc.mapper;

import com.internshipManagement.ongc.DTO.CertificateDTO;
import com.internshipManagement.ongc.Model.Certificate;

public class CertificateMapper {

    public static CertificateDTO toDTO(Certificate certificate) {
        if (certificate == null) return null;
        CertificateDTO dto = new CertificateDTO();
        dto.setId(certificate.getId());
        dto.setInternId(certificate.getInternId());
        dto.setStatus(certificate.getStatus());
        dto.setCertificateNumber(certificate.getCertificateNumber());
        dto.setRecommendedDate(certificate.getRecommendedDate());
        dto.setRemarks(certificate.getRemarks());
        dto.setCreatedAt(certificate.getCreatedAt());
        dto.setUpdatedAt(certificate.getUpdatedAt());
        return dto;
    }

    public static Certificate toEntity(CertificateDTO dto) {
        if (dto == null) return null;
        Certificate certificate = new Certificate();
        certificate.setId(dto.getId());
        certificate.setInternId(dto.getInternId());
        certificate.setStatus(dto.getStatus());
        certificate.setCertificateNumber(dto.getCertificateNumber());
        certificate.setRecommendedDate(dto.getRecommendedDate());
        certificate.setRemarks(dto.getRemarks());
        certificate.setCreatedAt(dto.getCreatedAt());
        certificate.setUpdatedAt(dto.getUpdatedAt());
        return certificate;
    }
}
