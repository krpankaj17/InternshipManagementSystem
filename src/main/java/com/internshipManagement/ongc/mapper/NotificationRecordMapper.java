package com.internshipManagement.ongc.mapper;

import com.internshipManagement.ongc.DTO.NotificationRecordDTO;
import com.internshipManagement.ongc.Model.NotificationRecord;

public class NotificationRecordMapper {

    public static NotificationRecordDTO toDTO(NotificationRecord entity) {
        NotificationRecordDTO dto = new NotificationRecordDTO();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setMessage(entity.getMessage());
        dto.setNotificationType(entity.getNotificationType());
        dto.setStatus(entity.getStatus());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    public static NotificationRecord toEntity(NotificationRecordDTO dto) {
        NotificationRecord entity = new NotificationRecord();
        entity.setId(dto.getId()); // Will be ignored by Hibernate if @GeneratedValue is used
        entity.setUserId(dto.getUserId());
        entity.setMessage(dto.getMessage());
        entity.setNotificationType(dto.getNotificationType());
        entity.setStatus(dto.getStatus());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        return entity;
    }
}
