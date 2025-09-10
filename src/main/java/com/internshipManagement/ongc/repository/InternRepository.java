package com.internshipManagement.ongc.repository;

import com.internshipManagement.ongc.Model.Interns;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InternRepository extends JpaRepository<Interns, String> {

    // Basic filters
    List<Interns> findByStatus(String status);

    List<Interns> findByInternshipType(String internshipType);

    List<Interns> findByNameContainingIgnoreCase(String name);

    List<Interns> findByRecommendedForCertificateTrue();

    // Mentor-based filters
    List<Interns> findByAssignedMentor_Id(String mentorId);

    List<Interns> findByCurrentMentor_Id(String mentorId);

    List<Interns> findByAssignedMentor_IdOrCurrentMentor_Id(String assignedMentorId, String currentMentorId);

    // Combined filters for mentor dashboards
    List<Interns> findByStatusAndAssignedMentor_IdOrStatusAndCurrentMentor_Id(
            String status, String assignedMentorId,
            String status2, String currentMentorId
    );

    List<Interns> findByInternshipTypeAndAssignedMentor_IdOrInternshipTypeAndCurrentMentor_Id(
            String internshipType, String assignedMentorId,
            String internshipType2, String currentMentorId
    );

    List<Interns> findByNameContainingIgnoreCaseAndAssignedMentor_IdOrNameContainingIgnoreCaseAndCurrentMentor_Id(
            String name, String assignedMentorId,
            String name2, String currentMentorId
    );

    List<Interns> findByRecommendedForCertificateTrueAndAssignedMentor_IdOrRecommendedForCertificateTrueAndCurrentMentor_Id(
            String assignedMentorId, String currentMentorId
    );
}
