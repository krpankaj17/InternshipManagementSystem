package com.internshipManagement.ongc.repository;

import com.internshipManagement.ongc.Model.ProjectRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRecordRepository extends JpaRepository<ProjectRecord, String> {
    List<ProjectRecord> findByInternId(String internId);
    List<ProjectRecord> findByMentorId(String mentorId);
    List<ProjectRecord> findByStatus(String status);
}
