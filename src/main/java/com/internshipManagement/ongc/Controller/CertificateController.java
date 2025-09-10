package com.internshipManagement.ongc.Controller;

import com.internshipManagement.ongc.DTO.CertificateDTO;
import com.internshipManagement.ongc.Services.CertificateService;
import com.internshipManagement.ongc.Services.InternServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/certificates")
@RequiredArgsConstructor
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private InternServices internServices;

    @PostMapping
    public ResponseEntity<CertificateDTO> create(@RequestBody CertificateDTO dto) {
        return ResponseEntity.ok(certificateService.createCertificate(dto));
    }

    @GetMapping
    public ResponseEntity<List<CertificateDTO>> getAll() {
        return ResponseEntity.ok(certificateService.getAllCertificates());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CertificateDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok(certificateService.getCertificateById(id));
    }

    @GetMapping("/intern/{internId}")
    public ResponseEntity<List<CertificateDTO>> getByInternId(@PathVariable String internId) {
        return ResponseEntity.ok(certificateService.getCertificatesByInternId(internId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CertificateDTO> update(@PathVariable String id, @RequestBody CertificateDTO dto) {
        return ResponseEntity.ok(certificateService.updateCertificate(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        certificateService.deleteCertificate(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{internId}/recommend-certificate")
    public ResponseEntity<Void> recommendForCertificate(
            @PathVariable String internId,
            @RequestParam String currentUserId,
            @RequestParam String currentUserRole
    ) {
        internServices.recommendForCertificate(internId, currentUserId, currentUserRole);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<CertificateDTO>> getByStatus(@RequestParam String status) {
        return ResponseEntity.ok(certificateService.getCertificatesByStatus(status));
    }
}
