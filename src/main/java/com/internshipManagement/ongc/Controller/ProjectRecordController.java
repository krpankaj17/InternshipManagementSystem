package com.internshipManagement.ongc.Controller;

import com.internshipManagement.ongc.DTO.ProjectRecordDTO;
import com.internshipManagement.ongc.Services.ProjectRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProjectRecordController {

    @Autowired
    private ProjectRecordService service;

    @PostMapping
    public ResponseEntity<ProjectRecordDTO> create(@RequestBody ProjectRecordDTO dto) {
        return ResponseEntity.ok(service.createProject(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProjectRecordDTO>> getAll() {
        return ResponseEntity.ok(service.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectRecordDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getProjectById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectRecordDTO> update(@PathVariable String id, @RequestBody ProjectRecordDTO dto) {
        return ResponseEntity.ok(service.updateProject(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    // 🚀 Optional: Upload file endpoint (save URL to DB)
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileUrl = service.storeFile(file); // Implement in service
        return ResponseEntity.ok(fileUrl);
    }
}
