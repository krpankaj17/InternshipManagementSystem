package com.internshipManagement.ongc.Services;

import com.internshipManagement.ongc.DTO.NotificationRecordDTO;
import com.internshipManagement.ongc.Model.NotificationRecord;
import com.internshipManagement.ongc.Model.UserAccount;
import com.internshipManagement.ongc.mapper.NotificationRecordMapper;
import com.internshipManagement.ongc.repository.NotificationRecordRepository;
import com.internshipManagement.ongc.repository.UserAccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationRecordServiceImpl implements NotificationRecordService {

    @Autowired
    private NotificationRecordRepository notificationRecordRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public NotificationRecordDTO createNotification(NotificationRecordDTO dto) {

        UserAccount user = userAccountRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        NotificationRecord notification = NotificationRecordMapper.toEntity(dto);

        // ❌ Removed manual UUID setting:
        // notification.setId(UUID.randomUUID().toString());

        // ✅ Status validation
        String status = dto.getStatus();
        if (status == null) {
            throw new RuntimeException("Status is required.");
        }

        status = status.toLowerCase();
        if (!status.equals("unread") && !status.equals("read") && !status.equals("archived")) {
            throw new RuntimeException("Invalid notification status. Allowed: unread, read, archived.");
        }

        notification.setStatus(status);

        NotificationRecord saved = notificationRecordRepository.save(notification);
        return NotificationRecordMapper.toDTO(saved);
    }

    @Override
    public List<NotificationRecordDTO> getAllNotifications() {
        return notificationRecordRepository.findAll().stream()
                .map(NotificationRecordMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationRecordDTO getNotificationById(String id) {
        return notificationRecordRepository.findById(id)
                .map(NotificationRecordMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
    }

    @Override
    public List<NotificationRecordDTO> getNotificationsByUserId(String userId) {
        return notificationRecordRepository.findByUserId(userId).stream()
                .map(NotificationRecordMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationRecordDTO updateNotification(String id, NotificationRecordDTO dto) {
        NotificationRecord existing = notificationRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        if (dto.getMessage() != null) {
            existing.setMessage(dto.getMessage());
        }

        if (dto.getNotificationType() != null) {
            existing.setNotificationType(dto.getNotificationType());
        }

        if (dto.getStatus() != null) {
            String status = dto.getStatus().toLowerCase();
            if (!status.equals("unread") && !status.equals("read") && !status.equals("archived")) {
                throw new RuntimeException("Invalid notification status. Allowed: unread, read, archived.");
            }
            existing.setStatus(status);
        }

        NotificationRecord updated = notificationRecordRepository.save(existing);
        return NotificationRecordMapper.toDTO(updated);
    }

    @Override
    public void deleteNotification(String id) {
        if (!notificationRecordRepository.existsById(id)) {
            throw new RuntimeException("Notification not found");
        }
        notificationRecordRepository.deleteById(id);
    }

    @Override
    public List<NotificationRecordDTO> getNotificationsByStatus(String status) {
        String lowerStatus = status.toLowerCase();
        if (!lowerStatus.equals("unread") && !lowerStatus.equals("read") && !lowerStatus.equals("archived")) {
            throw new RuntimeException("Invalid notification status. Allowed: unread, read, archived.");
        }
        return notificationRecordRepository.findByStatus(lowerStatus).stream()
                .map(NotificationRecordMapper::toDTO)
                .collect(Collectors.toList());
    }
}
