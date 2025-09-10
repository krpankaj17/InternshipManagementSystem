package com.internshipManagement.ongc.repository;

import com.internshipManagement.ongc.Model.NotificationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRecordRepository extends JpaRepository<NotificationRecord, String> {
    List<NotificationRecord> findByUserId(String userId);
    List<NotificationRecord> findByStatus(String status);
}
