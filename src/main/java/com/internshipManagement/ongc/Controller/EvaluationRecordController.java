package com.internshipManagement.ongc.Controller;

import com.internshipManagement.ongc.DTO.EvaluationRecordDTO;
import com.internshipManagement.ongc.Services.EvaluationRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/evaluations")
@RequiredArgsConstructor
public class EvaluationRecordController {
    @Autowired
    private  EvaluationRecordService service;

    @PostMapping
    public ResponseEntity<EvaluationRecordDTO> create(@RequestBody EvaluationRecordDTO dto) {
        return ResponseEntity.ok(service.createEvaluation(dto));
    }

    @GetMapping
    public ResponseEntity<List<EvaluationRecordDTO>> getAll() {
        return ResponseEntity.ok(service.getAllEvaluations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluationRecordDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getEvaluationById(id));
    }

    @GetMapping("/intern/{internId}")
    public ResponseEntity<List<EvaluationRecordDTO>> getByInternId(@PathVariable String internId) {
        return ResponseEntity.ok(service.getEvaluationsByInternId(internId));
    }

    @GetMapping("/mentor/{mentorId}")
    public ResponseEntity<List<EvaluationRecordDTO>> getByMentorId(@PathVariable String mentorId) {
        return ResponseEntity.ok(service.getEvaluationsByMentorId(mentorId));
    }

    // 🚀 New: Get all evaluations filtered by evaluationType
    @GetMapping("/type/{evaluationType}")
    public ResponseEntity<List<EvaluationRecordDTO>> getByEvaluationType(@PathVariable String evaluationType) {
        return ResponseEntity.ok(service.getEvaluationsByType(evaluationType));
    }

    // 🚀 New: Get evaluations filtered by evaluationType and internId
    @GetMapping("/type/{evaluationType}/intern/{internId}")
    public ResponseEntity<List<EvaluationRecordDTO>> getByEvaluationTypeAndIntern(
            @PathVariable String evaluationType,
            @PathVariable String internId) {
        return ResponseEntity.ok(service.getEvaluationsByTypeAndInternId(evaluationType, internId));
    }

    // 🚀 New: Get evaluations filtered by evaluationType and mentorId
    @GetMapping("/type/{evaluationType}/mentor/{mentorId}")
    public ResponseEntity<List<EvaluationRecordDTO>> getByEvaluationTypeAndMentor(
            @PathVariable String evaluationType,
            @PathVariable String mentorId) {
        return ResponseEntity.ok(service.getEvaluationsByTypeAndMentorId(evaluationType, mentorId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvaluationRecordDTO> update(@PathVariable String id, @RequestBody EvaluationRecordDTO dto) {
        return ResponseEntity.ok(service.updateEvaluation(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteEvaluation(id);
        return ResponseEntity.noContent().build();
    }



}
