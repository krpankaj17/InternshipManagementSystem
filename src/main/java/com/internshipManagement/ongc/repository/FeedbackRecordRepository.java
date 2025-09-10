package com.internshipManagement.ongc.repository;

import com.internshipManagement.ongc.Model.FeedbackRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRecordRepository extends JpaRepository<FeedbackRecord, String> {
    List<FeedbackRecord> findByInternId(String internId);
    List<FeedbackRecord> findByMentorId(String mentorId);
}
