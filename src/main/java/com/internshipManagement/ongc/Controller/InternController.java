package com.internshipManagement.ongc.Controller;

import com.internshipManagement.ongc.DTO.InternResponseDTO;
import com.internshipManagement.ongc.DTO.InternUpdateRequest;
import com.internshipManagement.ongc.Services.InternServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/interns")
@CrossOrigin(origins = "*")
@Validated
public class InternController {

    @Autowired
    private InternServices internServices;

    @PostMapping("/add")
    public ResponseEntity<InternResponseDTO> addIntern(
            @RequestBody InternUpdateRequest request,
            @RequestParam String currentUserId,
            @RequestParam String currentUserRole) {
        return ResponseEntity.ok(internServices.addIntern(request, currentUserId, currentUserRole));
    }

    @GetMapping("/all")
    public ResponseEntity<List<InternResponseDTO>> getAllInterns(
            @RequestParam String currentUserId,
            @RequestParam String currentUserRole) {
        return ResponseEntity.ok(internServices.getAllInterns(currentUserId, currentUserRole));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InternResponseDTO> getInternById(
            @PathVariable String id,
            @RequestParam String currentUserId,
            @RequestParam String currentUserRole) {
        return ResponseEntity.ok(internServices.getInternById(id, currentUserId, currentUserRole));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<InternResponseDTO>> getByStatus(
            @PathVariable String status,
            @RequestParam String currentUserId,
            @RequestParam String currentUserRole) {
        return ResponseEntity.ok(internServices.getInternsByStatus(status, currentUserId, currentUserRole));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<InternResponseDTO>> getByType(
            @PathVariable String type,
            @RequestParam String currentUserId,
            @RequestParam String currentUserRole) {
        return ResponseEntity.ok(internServices.getInternsByType(type, currentUserId, currentUserRole));
    }

    @GetMapping("/search")
    public ResponseEntity<List<InternResponseDTO>> searchByName(
            @RequestParam String name,
            @RequestParam String currentUserId,
            @RequestParam String currentUserRole) {
        return ResponseEntity.ok(internServices.searchInternsByName(name, currentUserId, currentUserRole));
    }

    @GetMapping("/assigned-mentor/{mentorId}")
    public ResponseEntity<List<InternResponseDTO>> getByAssignedMentor(
            @PathVariable String mentorId,
            @RequestParam String currentUserId,
            @RequestParam String currentUserRole) {
        return ResponseEntity.ok(internServices.getInternsByAssignedMentor(mentorId, currentUserId, currentUserRole));
    }

    @GetMapping("/current-mentor/{mentorId}")
    public ResponseEntity<List<InternResponseDTO>> getByCurrentMentor(
            @PathVariable String mentorId,
            @RequestParam String currentUserId,
            @RequestParam String currentUserRole) {
        return ResponseEntity.ok(internServices.getInternsByCurrentMentor(mentorId, currentUserId, currentUserRole));
    }

    @GetMapping("/recommended")
    public ResponseEntity<List<InternResponseDTO>> getRecommended(
            @RequestParam String currentUserId,
            @RequestParam String currentUserRole) {
        return ResponseEntity.ok(internServices.getRecommendedForCertificate(currentUserId, currentUserRole));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<InternResponseDTO> updateIntern(
            @PathVariable String id,
            @RequestBody InternUpdateRequest request,
            @RequestParam String currentUserId,
            @RequestParam String currentUserRole) {
        return ResponseEntity.ok(internServices.updateIntern(id, request, currentUserId, currentUserRole));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteIntern(
            @PathVariable String id,
            @RequestParam String currentUserId,
            @RequestParam String currentUserRole) {
        internServices.deleteIntern(id, currentUserId, currentUserRole);
        return ResponseEntity.noContent().build();
    }
}
