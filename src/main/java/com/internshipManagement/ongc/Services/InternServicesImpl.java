package com.internshipManagement.ongc.Services;

import com.internshipManagement.ongc.DTO.InternResponseDTO;
import com.internshipManagement.ongc.DTO.InternUpdateRequest;
import com.internshipManagement.ongc.Model.Interns;
import com.internshipManagement.ongc.repository.InternRepository;
import com.internshipManagement.ongc.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InternServicesImpl implements InternServices {

    @Autowired
    private InternRepository internRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    private boolean isAdmin(String role) {
        return "ADMIN".equalsIgnoreCase(role);
    }

    private boolean isMentor(String role) {
        return "MENTOR".equalsIgnoreCase(role);
    }

    @Override
    public InternResponseDTO addIntern(InternUpdateRequest request, String currentUserId, String currentUserRole) {
        if (!isAdmin(currentUserRole)) {
            throw new RuntimeException("Only ADMIN can add interns.");
        }

        Interns intern = mapRequestToEntity(request, new Interns());
        Interns saved = internRepository.save(intern);
        return mapToDTO(saved);
    }

    @Override
    public List<InternResponseDTO> getAllInterns(String currentUserId, String currentUserRole) {
        List<Interns> interns;

        if (isAdmin(currentUserRole)) {
            interns = internRepository.findAll();
        } else if (isMentor(currentUserRole)) {
            interns = internRepository.findByAssignedMentor_IdOrCurrentMentor_Id(currentUserId, currentUserId);
        } else {
            throw new RuntimeException("Unauthorized to view interns.");
        }

        return interns.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public InternResponseDTO getInternById(String id, String currentUserId, String currentUserRole) {
        Interns intern = internRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Intern with ID " + id + " not found."));

        if (isAdmin(currentUserRole) ||
                (isMentor(currentUserRole) && (
                        (intern.getAssignedMentor() != null && intern.getAssignedMentor().getId().equals(currentUserId)) ||
                                (intern.getCurrentMentor() != null && intern.getCurrentMentor().getId().equals(currentUserId))
                ))) {
            return mapToDTO(intern);
        } else {
            throw new RuntimeException("Unauthorized to view this intern.");
        }
    }

    @Override
    public List<InternResponseDTO> getInternsByStatus(String status, String currentUserId, String currentUserRole) {
        List<Interns> interns;

        if (isAdmin(currentUserRole)) {
            interns = internRepository.findByStatus(status);
        } else if (isMentor(currentUserRole)) {
            interns = internRepository.findByStatusAndAssignedMentor_IdOrStatusAndCurrentMentor_Id(
                    status, currentUserId,
                    status, currentUserId
            );
        } else {
            throw new RuntimeException("Unauthorized to view interns by status.");
        }

        return interns.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<InternResponseDTO> getInternsByType(String internshipType, String currentUserId, String currentUserRole) {
        List<Interns> interns;

        if (isAdmin(currentUserRole)) {
            interns = internRepository.findByInternshipType(internshipType);
        } else if (isMentor(currentUserRole)) {
            interns = internRepository.findByInternshipTypeAndAssignedMentor_IdOrInternshipTypeAndCurrentMentor_Id(
                    internshipType, currentUserId,
                    internshipType, currentUserId
            );
        } else {
            throw new RuntimeException("Unauthorized to view interns by type.");
        }

        return interns.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<InternResponseDTO> searchInternsByName(String name, String currentUserId, String currentUserRole) {
        List<Interns> interns;

        if (isAdmin(currentUserRole)) {
            interns = internRepository.findByNameContainingIgnoreCase(name);
        } else if (isMentor(currentUserRole)) {
            interns = internRepository.findByNameContainingIgnoreCaseAndAssignedMentor_IdOrNameContainingIgnoreCaseAndCurrentMentor_Id(
                    name, currentUserId,
                    name, currentUserId
            );
        } else {
            throw new RuntimeException("Unauthorized to search interns.");
        }

        return interns.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<InternResponseDTO> getInternsByAssignedMentor(String mentorId, String currentUserId, String currentUserRole) {
        if (!isAdmin(currentUserRole) && !(isMentor(currentUserRole) && currentUserId.equals(mentorId))) {
            throw new RuntimeException("Unauthorized to view interns by assigned mentor.");
        }
        return internRepository.findByAssignedMentor_Id(mentorId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<InternResponseDTO> getInternsByCurrentMentor(String mentorId, String currentUserId, String currentUserRole) {
        if (!isAdmin(currentUserRole) && !(isMentor(currentUserRole) && currentUserId.equals(mentorId))) {
            throw new RuntimeException("Unauthorized to view interns by current mentor.");
        }
        return internRepository.findByCurrentMentor_Id(mentorId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<InternResponseDTO> getRecommendedForCertificate(String currentUserId, String currentUserRole) {
        List<Interns> interns;

        if (isAdmin(currentUserRole)) {
            interns = internRepository.findByRecommendedForCertificateTrue();
        } else if (isMentor(currentUserRole)) {
            interns = internRepository.findByRecommendedForCertificateTrueAndAssignedMentor_IdOrRecommendedForCertificateTrueAndCurrentMentor_Id(
                    currentUserId, currentUserId
            );
        } else {
            throw new RuntimeException("Unauthorized to view recommended interns.");
        }

        return interns.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public InternResponseDTO updateIntern(String id, InternUpdateRequest request, String currentUserId, String currentUserRole) {
        if (!isAdmin(currentUserRole)) {
            throw new RuntimeException("Only ADMIN can update interns.");
        }
        Interns intern = internRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Intern with ID " + id + " not found."));
        intern = mapRequestToEntity(request, intern);
        Interns updated = internRepository.save(intern);
        return mapToDTO(updated);
    }

    @Override
    public void deleteIntern(String id, String currentUserId, String currentUserRole) {
        if (!isAdmin(currentUserRole)) {
            throw new RuntimeException("Only ADMIN can delete interns.");
        }
        if (!internRepository.existsById(id)) {
            throw new RuntimeException("Intern with ID " + id + " not found.");
        }
        internRepository.deleteById(id);
    }

    /**
     * Converts InternUpdateRequest DTO to Interns entity for save/update.
     */
    private Interns mapRequestToEntity(InternUpdateRequest request, Interns intern) {
        if (intern.getInternId() == null || intern.getInternId().isEmpty()) {
            intern.setInternId(java.util.UUID.randomUUID().toString());
        }

        intern.setName(request.getName());
        intern.setFatherName(request.getFatherName());
        intern.setEmail(request.getEmail());
        intern.setPhone(request.getPhone());
        intern.setAlternatePhone(request.getAlternatePhone());
        intern.setDateOfBirth(request.getDateOfBirth());
        intern.setGender(request.getGender());
        intern.setAddress(request.getAddress());
        intern.setCity(request.getCity());
        intern.setState(request.getState());
        intern.setPincode(request.getPincode());
        intern.setCollege(request.getCollege());
        intern.setUniversity(request.getUniversity());
        intern.setCourse(request.getCourse());
        intern.setBranch(request.getBranch());
        intern.setCurrentYear(request.getCurrentYear());
        intern.setCurrentSemester(request.getCurrentSemester());
        intern.setCurrentCgpa(request.getCurrentCgpa());
        intern.setLastSemesterSgpa(request.getLastSemesterSgpa());
        intern.setExpectedGraduation(request.getExpectedGraduation());
        intern.setInternshipType(request.getInternshipType());
        intern.setStartDate(request.getStartDate());
        intern.setEndDate(request.getEndDate());
        intern.setProject(request.getProject());
        intern.setProjectDomain(request.getProjectDomain());
        intern.setPreferredDepartment(request.getPreferredDepartment());
        intern.setBiodataFileName(request.getBiodataFileName());
        intern.setBiodataFileUrl(request.getBiodataFileUrl());
        intern.setBiodataUploadedAt(request.getBiodataUploadedAt());
        intern.setQuickNotes(request.getQuickNotes());
        intern.setStatus(request.getStatus() != null ? request.getStatus() : "Pending");
        intern.setRecommendedForCertificate(request.getRecommendedForCertificate() != null ? request.getRecommendedForCertificate() : false);

        if (request.getAssignedMentorId() != null && !request.getAssignedMentorId().isEmpty()) {
            userAccountRepository.findById(request.getAssignedMentorId())
                    .ifPresent(intern::setAssignedMentor);
        } else {
            intern.setAssignedMentor(null);
        }

        if (request.getCurrentMentorId() != null && !request.getCurrentMentorId().isEmpty()) {
            userAccountRepository.findById(request.getCurrentMentorId())
                    .ifPresent(intern::setCurrentMentor);
        } else {
            intern.setCurrentMentor(null);
        }

        return intern;
    }

    /**
     * Converts Interns entity to InternResponseDTO for frontend.
     */
    private InternResponseDTO mapToDTO(Interns intern) {
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
        dto.setStatus(intern.getStatus());
        dto.setRecommendedForCertificate(intern.getRecommendedForCertificate());
        dto.setCreatedAt(intern.getCreatedAt());
        dto.setLastUpdated(intern.getLastUpdated());

        if (intern.getAssignedMentor() != null) {
            dto.setAssignedMentorId(intern.getAssignedMentor().getId());
            dto.setAssignedMentorName(intern.getAssignedMentor().getName());
        }
        if (intern.getCurrentMentor() != null) {
            dto.setCurrentMentorId(intern.getCurrentMentor().getId());
            dto.setCurrentMentorName(intern.getCurrentMentor().getName());
        }

        return dto;
    }
    @Override
    public void recommendForCertificate(String internId, String currentUserId, String currentUserRole) {
        if (!isAdmin(currentUserRole) && !isMentor(currentUserRole)) {
            throw new RuntimeException("Only ADMIN or MENTOR can recommend for certificate.");
        }

        Interns intern = internRepository.findById(internId)
                .orElseThrow(() -> new RuntimeException("Intern with ID " + internId + " not found."));

        // If MENTOR, check that they are assigned/current mentor of this intern
        if (isMentor(currentUserRole)) {
            boolean isRelated = (intern.getAssignedMentor() != null && intern.getAssignedMentor().getId().equals(currentUserId))
                    || (intern.getCurrentMentor() != null && intern.getCurrentMentor().getId().equals(currentUserId));
            if (!isRelated) {
                throw new RuntimeException("You are not authorized to recommend this intern for certificate.");
            }
        }

        intern.setRecommendedForCertificate(true);
        internRepository.save(intern);
    }

}
