package com.internshipManagement.ongc.mapper;

import com.internshipManagement.ongc.DTO.InternResponseDTO;
import com.internshipManagement.ongc.DTO.InternUpdateRequest;
import com.internshipManagement.ongc.Model.Interns;

public class InternMapper {

    // ✅ Entity -> Response DTO
    public static InternResponseDTO toResponseDTO(Interns intern) {
        if (intern == null) return null;

        InternResponseDTO dto = new InternResponseDTO();
        dto.setInternId(intern.getInternId());
        dto.setName(intern.getName());
        dto.setFatherName(intern.getFatherName());
        dto.setEmail(intern.getEmail());
        dto.setPhone(intern.getPhone());
        dto.setAlternatePhone(intern.getAlternatePhone());
        dto.setDateOfBirth(intern.getDateOfBirth());
        dto.setGender(intern.getGender());
        dto.setAddress(intern.getAddress());
        dto.setCity(intern.getCity());
        dto.setState(intern.getState());
        dto.setPincode(intern.getPincode());
        dto.setCollege(intern.getCollege());
        dto.setUniversity(intern.getUniversity());
        dto.setCourse(intern.getCourse());
        dto.setBranch(intern.getBranch());
        dto.setCurrentYear(intern.getCurrentYear());
        dto.setCurrentSemester(intern.getCurrentSemester());
        dto.setCurrentCgpa(intern.getCurrentCgpa());
        dto.setLastSemesterSgpa(intern.getLastSemesterSgpa());
        dto.setExpectedGraduation(intern.getExpectedGraduation());
        dto.setInternshipType(intern.getInternshipType());
        dto.setStartDate(intern.getStartDate());
        dto.setEndDate(intern.getEndDate());
        dto.setProject(intern.getProject());
        dto.setProjectDomain(intern.getProjectDomain());
        dto.setPreferredDepartment(intern.getPreferredDepartment());
        dto.setBiodataFileName(intern.getBiodataFileName());
        dto.setBiodataFileUrl(intern.getBiodataFileUrl());
        dto.setBiodataUploadedAt(intern.getBiodataUploadedAt());
        dto.setQuickNotes(intern.getQuickNotes());

        dto.setAssignedMentorId(intern.getAssignedMentor() != null ? intern.getAssignedMentor().getId() : null);
        dto.setAssignedMentorName(intern.getAssignedMentor() != null ? intern.getAssignedMentor().getName() : null);
        dto.setCurrentMentorId(intern.getCurrentMentor() != null ? intern.getCurrentMentor().getId() : null);
        dto.setCurrentMentorName(intern.getCurrentMentor() != null ? intern.getCurrentMentor().getName() : null);

        dto.setStatus(intern.getStatus());
        dto.setRecommendedForCertificate(intern.getRecommendedForCertificate());
        dto.setCreatedAt(intern.getCreatedAt());
        dto.setLastUpdated(intern.getLastUpdated());

        return dto;
    }

    // ✅ Update Request -> update existing Entity (null-safe)
    public static void updateEntityFromRequest(InternUpdateRequest req, Interns intern) {
        if (req == null || intern == null) return;

        if (req.getName() != null) intern.setName(req.getName());
        if (req.getFatherName() != null) intern.setFatherName(req.getFatherName());
        if (req.getEmail() != null) intern.setEmail(req.getEmail());
        if (req.getPhone() != null) intern.setPhone(req.getPhone());
        if (req.getAlternatePhone() != null) intern.setAlternatePhone(req.getAlternatePhone());
        if (req.getDateOfBirth() != null) intern.setDateOfBirth(req.getDateOfBirth());
        if (req.getGender() != null) intern.setGender(req.getGender());
        if (req.getAddress() != null) intern.setAddress(req.getAddress());
        if (req.getCity() != null) intern.setCity(req.getCity());
        if (req.getState() != null) intern.setState(req.getState());
        if (req.getPincode() != null) intern.setPincode(req.getPincode());
        if (req.getCollege() != null) intern.setCollege(req.getCollege());
        if (req.getUniversity() != null) intern.setUniversity(req.getUniversity());
        if (req.getCourse() != null) intern.setCourse(req.getCourse());
        if (req.getBranch() != null) intern.setBranch(req.getBranch());
        if (req.getCurrentYear() != null) intern.setCurrentYear(req.getCurrentYear());
        if (req.getCurrentSemester() != null) intern.setCurrentSemester(req.getCurrentSemester());
        if (req.getCurrentCgpa() != null) intern.setCurrentCgpa(req.getCurrentCgpa());
        if (req.getLastSemesterSgpa() != null) intern.setLastSemesterSgpa(req.getLastSemesterSgpa());
        if (req.getExpectedGraduation() != null) intern.setExpectedGraduation(req.getExpectedGraduation());
        if (req.getInternshipType() != null) intern.setInternshipType(req.getInternshipType());
        if (req.getStartDate() != null) intern.setStartDate(req.getStartDate());
        if (req.getEndDate() != null) intern.setEndDate(req.getEndDate());
        if (req.getProject() != null) intern.setProject(req.getProject());
        if (req.getProjectDomain() != null) intern.setProjectDomain(req.getProjectDomain());
        if (req.getPreferredDepartment() != null) intern.setPreferredDepartment(req.getPreferredDepartment());
        if (req.getBiodataFileName() != null) intern.setBiodataFileName(req.getBiodataFileName());
        if (req.getBiodataFileUrl() != null) intern.setBiodataFileUrl(req.getBiodataFileUrl());
        if (req.getBiodataUploadedAt() != null) intern.setBiodataUploadedAt(req.getBiodataUploadedAt());
        if (req.getQuickNotes() != null) intern.setQuickNotes(req.getQuickNotes());
        if (req.getStatus() != null) intern.setStatus(req.getStatus());
        if (req.getRecommendedForCertificate() != null) intern.setRecommendedForCertificate(req.getRecommendedForCertificate());

        // ⚡ MENTOR MAPPING:
        // These require fetching `UserAccount` entities in the Service layer:
        // - assignedMentor
        // - currentMentor
        // Example in InternServicesImpl:
        // if (req.getAssignedMentorId() != null) {
        //     intern.setAssignedMentor(userAccountRepository.findById(req.getAssignedMentorId()).orElse(null));
        // }
        // if (req.getCurrentMentorId() != null) {
        //     intern.setCurrentMentor(userAccountRepository.findById(req.getCurrentMentorId()).orElse(null));
        // }
    }
}
