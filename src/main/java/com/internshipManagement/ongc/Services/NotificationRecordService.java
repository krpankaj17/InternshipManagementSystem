package com.internshipManagement.ongc.Services;

import com.internshipManagement.ongc.DTO.NotificationRecordDTO;
import java.util.List;

public interface NotificationRecordService {

    NotificationRecordDTO createNotification(NotificationRecordDTO dto);

    List<NotificationRecordDTO> getAllNotifications();

    NotificationRecordDTO getNotificationById(String id);

    List<NotificationRecordDTO> getNotificationsByUserId(String userId);

    List<NotificationRecordDTO> getNotificationsByStatus(String status); // ✅ included for filtering by status

    NotificationRecordDTO updateNotification(String id, NotificationRecordDTO dto);

    void deleteNotification(String id);
}
