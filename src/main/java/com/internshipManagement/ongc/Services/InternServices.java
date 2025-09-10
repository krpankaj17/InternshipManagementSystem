package com.internshipManagement.ongc.Services;

import com.internshipManagement.ongc.DTO.InternResponseDTO;
import com.internshipManagement.ongc.DTO.InternUpdateRequest;

import java.util.List;

public interface InternServices {

    InternResponseDTO addIntern(InternUpdateRequest request, String currentUserId, String currentUserRole);

    List<InternResponseDTO> getAllInterns(String currentUserId, String currentUserRole);

    InternResponseDTO getInternById(String id, String currentUserId, String currentUserRole);

    List<InternResponseDTO> getInternsByStatus(String status, String currentUserId, String currentUserRole);

    List<InternResponseDTO> getInternsByType(String internshipType, String currentUserId, String currentUserRole);

    List<InternResponseDTO> searchInternsByName(String name, String currentUserId, String currentUserRole);

    List<InternResponseDTO> getInternsByAssignedMentor(String mentorId, String currentUserId, String currentUserRole);

    List<InternResponseDTO> getInternsByCurrentMentor(String mentorId, String currentUserId, String currentUserRole);

    List<InternResponseDTO> getRecommendedForCertificate(String currentUserId, String currentUserRole);

    InternResponseDTO updateIntern(String id, InternUpdateRequest request, String currentUserId, String currentUserRole);

    void deleteIntern(String id, String currentUserId, String currentUserRole);
    void recommendForCertificate(String internId, String currentUserId, String currentUserRole);

}
