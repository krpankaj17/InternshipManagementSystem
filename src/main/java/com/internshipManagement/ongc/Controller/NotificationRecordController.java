package com.internshipManagement.ongc.Controller;

import com.internshipManagement.ongc.DTO.NotificationRecordDTO;
import com.internshipManagement.ongc.Services.NotificationRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class NotificationRecordController {

    @Autowired
    private NotificationRecordService service;

    @PostMapping
    public ResponseEntity<NotificationRecordDTO> create(@RequestBody NotificationRecordDTO dto) {
        return ResponseEntity.ok(service.createNotification(dto));
    }

    @GetMapping
    public ResponseEntity<List<NotificationRecordDTO>> getAll() {
        return ResponseEntity.ok(service.getAllNotifications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationRecordDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getNotificationById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationRecordDTO>> getByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(service.getNotificationsByUserId(userId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<NotificationRecordDTO>> getByStatus(@PathVariable String status) {
        return ResponseEntity.ok(service.getNotificationsByStatus(status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationRecordDTO> update(@PathVariable String id, @RequestBody NotificationRecordDTO dto) {
        return ResponseEntity.ok(service.updateNotification(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}
