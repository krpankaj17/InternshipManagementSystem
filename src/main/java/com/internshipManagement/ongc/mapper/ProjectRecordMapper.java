package com.internshipManagement.ongc.mapper;

import com.internshipManagement.ongc.DTO.ProjectRecordDTO;
import com.internshipManagement.ongc.Model.ProjectRecord;

public class ProjectRecordMapper {

    public static ProjectRecordDTO toDTO(ProjectRecord record) {
        if (record == null) return null;

        ProjectRecordDTO dto = new ProjectRecordDTO();
        dto.setId(record.getId());
        dto.setInternId(record.getInternId());
        dto.setProjectName(record.getProjectName());
        dto.setMentorId(record.getMentorId());
        dto.setStatus(record.getStatus());
        dto.setRemarks(record.getRemarks());
        dto.setDescription(record.getDescription());   // NEW
        dto.setFileUrl(record.getFileUrl());           // NEW
        dto.setGithubLink(record.getGithubLink());     // NEW
        dto.setCreatedAt(record.getCreatedAt());
        dto.setUpdatedAt(record.getUpdatedAt());
        return dto;
    }

    public static ProjectRecord toEntity(ProjectRecordDTO dto) {
        if (dto == null) return null;

        ProjectRecord record = new ProjectRecord();
        record.setId(dto.getId());
        record.setInternId(dto.getInternId());
        record.setProjectName(dto.getProjectName());
        record.setMentorId(dto.getMentorId());
        record.setStatus(dto.getStatus());
        record.setRemarks(dto.getRemarks());
        record.setDescription(dto.getDescription());   // NEW
        record.setFileUrl(dto.getFileUrl());           // NEW
        record.setGithubLink(dto.getGithubLink());     // NEW
        record.setCreatedAt(dto.getCreatedAt());
        record.setUpdatedAt(dto.getUpdatedAt());
        return record;
    }
}
