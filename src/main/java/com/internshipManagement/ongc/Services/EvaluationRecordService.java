package com.internshipManagement.ongc.Services;

import com.internshipManagement.ongc.DTO.EvaluationRecordDTO;

import java.util.List;

public interface EvaluationRecordService {

    EvaluationRecordDTO createEvaluation(EvaluationRecordDTO dto);

    List<EvaluationRecordDTO> getAllEvaluations();

    EvaluationRecordDTO getEvaluationById(String id);

    List<EvaluationRecordDTO> getEvaluationsByInternId(String internId);

    List<EvaluationRecordDTO> getEvaluationsByMentorId(String mentorId);

    List<EvaluationRecordDTO> getEvaluationsByType(String evaluationType);

    List<EvaluationRecordDTO> getEvaluationsByTypeAndInternId(String evaluationType, String internId);

    List<EvaluationRecordDTO> getEvaluationsByTypeAndMentorId(String evaluationType, String mentorId);

    EvaluationRecordDTO updateEvaluation(String id, EvaluationRecordDTO dto);

    void deleteEvaluation(String id);
}
