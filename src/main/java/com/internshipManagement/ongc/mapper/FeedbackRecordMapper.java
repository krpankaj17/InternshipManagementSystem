package com.internshipManagement.ongc.mapper;

import com.internshipManagement.ongc.DTO.FeedbackRecordDTO;
import com.internshipManagement.ongc.Model.FeedbackRecord;

public class FeedbackRecordMapper {

    public static FeedbackRecordDTO toDTO(FeedbackRecord record) {
        if (record == null) return null;

        FeedbackRecordDTO dto = new FeedbackRecordDTO();
        dto.setId(record.getId());
        dto.setInternId(record.getInternId());
        dto.setMentorId(record.getMentorId());
        dto.setFeedbackText(record.getFeedbackText());
        dto.setFeedbackDate(record.getFeedbackDate());
        dto.setStatus(record.getStatus());
        dto.setCreatedAt(record.getCreatedAt());
        dto.setUpdatedAt(record.getUpdatedAt());
        return dto;
    }

    public static FeedbackRecord toEntity(FeedbackRecordDTO dto) {
        if (dto == null) return null;

        FeedbackRecord record = new FeedbackRecord();
        record.setId(dto.getId()); // will be ignored on creation as UUID is auto-generated
        record.setInternId(dto.getInternId());
        record.setMentorId(dto.getMentorId());
        record.setFeedbackText(dto.getFeedbackText());
        record.setFeedbackDate(dto.getFeedbackDate());
        record.setStatus(dto.getStatus());
        record.setCreatedAt(dto.getCreatedAt());
        record.setUpdatedAt(dto.getUpdatedAt());
        return record;
    }
}
