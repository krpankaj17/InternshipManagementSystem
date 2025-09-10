package com.internshipManagement.ongc.mapper;

import com.internshipManagement.ongc.DTO.EvaluationRecordDTO;
import com.internshipManagement.ongc.Model.EvaluationRecord;

public class EvaluationRecordMapper {

    public static EvaluationRecordDTO toDTO(EvaluationRecord record) {
        if (record == null) {
            return null;
        }

        EvaluationRecordDTO dto = new EvaluationRecordDTO();
        dto.setId(record.getId());
        dto.setInternId(record.getInternId());
        dto.setMentorId(record.getMentorId());
        dto.setScore(record.getScore());
        dto.setFeedback(record.getFeedback());
        dto.setEvaluationDate(record.getEvaluationDate());
        dto.setStatus(record.getStatus());

        dto.setTechnicalScore(record.getTechnicalScore());
        dto.setCommunicationScore(record.getCommunicationScore());
        dto.setTeamworkScore(record.getTeamworkScore());
        dto.setPunctualityScore(record.getPunctualityScore());
        dto.setInitiativeScore(record.getInitiativeScore());

        dto.setCreatedAt(record.getCreatedAt());
        dto.setUpdatedAt(record.getUpdatedAt());
        dto.setEvaluationType(record.getEvaluationType());

        return dto;
    }

    public static EvaluationRecord toEntity(EvaluationRecordDTO dto) {
        if (dto == null) {
            return null;
        }

        EvaluationRecord record = new EvaluationRecord();

        // 🚫 Removed:
        // record.setId(dto.getId());
        // Let Hibernate generate the ID automatically.

        record.setInternId(dto.getInternId());
        record.setMentorId(dto.getMentorId());
        record.setScore(dto.getScore());
        record.setFeedback(dto.getFeedback());
        record.setEvaluationDate(dto.getEvaluationDate());
        record.setStatus(dto.getStatus());

        record.setTechnicalScore(dto.getTechnicalScore());
        record.setCommunicationScore(dto.getCommunicationScore());
        record.setTeamworkScore(dto.getTeamworkScore());
        record.setPunctualityScore(dto.getPunctualityScore());
        record.setInitiativeScore(dto.getInitiativeScore());

        record.setCreatedAt(dto.getCreatedAt());
        record.setUpdatedAt(dto.getUpdatedAt());
        record.setEvaluationType(dto.getEvaluationType());

        return record;
    }
}
