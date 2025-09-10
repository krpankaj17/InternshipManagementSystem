package com.internshipManagement.ongc.repository;

import com.internshipManagement.ongc.Model.EvaluationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRecordRepository extends JpaRepository<EvaluationRecord, String> {

    List<EvaluationRecord> findByEvaluationType(String evaluationType);

    List<EvaluationRecord> findByInternId(String internId);

    List<EvaluationRecord> findByMentorId(String mentorId);

    // ✅ For filtering by evaluationType + internId
    List<EvaluationRecord> findByEvaluationTypeAndInternId(String evaluationType, String internId);

    // ✅ For filtering by evaluationType + mentorId
    List<EvaluationRecord> findByEvaluationTypeAndMentorId(String evaluationType, String mentorId);
}
