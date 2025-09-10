package com.internshipManagement.ongc.Controller;

import com.internshipManagement.ongc.DTO.FeedbackRecordDTO;
import com.internshipManagement.ongc.Services.FeedbackRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/feedbacks")

@RequiredArgsConstructor
public class FeedbackRecordController {

    @Autowired
    private FeedbackRecordService service;

    @PostMapping
    public ResponseEntity<FeedbackRecordDTO> create(@RequestBody FeedbackRecordDTO dto) {
        return ResponseEntity.ok(service.createFeedback(dto));
    }

    @GetMapping
    public ResponseEntity<List<FeedbackRecordDTO>> getAll() {
        return ResponseEntity.ok(service.getAllFeedbacks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackRecordDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getFeedbackById(id));
    }

    @GetMapping("/intern/{internId}")
    public ResponseEntity<List<FeedbackRecordDTO>> getByInternId(@PathVariable String internId) {
        return ResponseEntity.ok(service.getFeedbacksByInternId(internId));
    }

    @GetMapping("/mentor/{mentorId}")
    public ResponseEntity<List<FeedbackRecordDTO>> getByMentorId(@PathVariable String mentorId) {
        return ResponseEntity.ok(service.getFeedbacksByMentorId(mentorId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackRecordDTO> update(@PathVariable String id, @RequestBody FeedbackRecordDTO dto) {
        return ResponseEntity.ok(service.updateFeedback(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }
}
