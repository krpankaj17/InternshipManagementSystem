package com.internshipManagement.ongc.Services;

import com.internshipManagement.ongc.DTO.ProjectRecordDTO;
import com.internshipManagement.ongc.Model.ProjectRecord;
import com.internshipManagement.ongc.repository.ProjectRecordRepository;
import com.internshipManagement.ongc.mapper.ProjectRecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectRecordServiceImpl implements ProjectRecordService {
    @Autowired
    private  ProjectRecordRepository repository;

    @Override
    public ProjectRecordDTO createProject(ProjectRecordDTO dto) {
        ProjectRecord record = ProjectRecordMapper.toEntity(dto);
        ProjectRecord saved = repository.save(record);
        return ProjectRecordMapper.toDTO(saved);
    }

    @Override
    public List<ProjectRecordDTO> getAllProjects() {
        return repository.findAll()
                .stream()
                .map(ProjectRecordMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectRecordDTO getProjectById(String id) {
        ProjectRecord record = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
        return ProjectRecordMapper.toDTO(record);
    }

    @Override
    public ProjectRecordDTO updateProject(String id, ProjectRecordDTO dto) {
        ProjectRecord existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));

        // Update fields
        existing.setProjectName(dto.getProjectName());
        existing.setInternId(dto.getInternId());
        existing.setMentorId(dto.getMentorId());
        existing.setStatus(dto.getStatus());
        existing.setRemarks(dto.getRemarks());
        existing.setDescription(dto.getDescription());
        existing.setFileUrl(dto.getFileUrl());
        existing.setGithubLink(dto.getGithubLink());

        ProjectRecord updated = repository.save(existing);
        return ProjectRecordMapper.toDTO(updated);
    }

    @Override
    public void deleteProject(String id) {
        repository.deleteById(id);
    }

    @Override
    public String storeFile(MultipartFile file) {
        try {
            String folder = "uploads/";
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path path = Paths.get(folder + fileName);
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());
            return path.toString(); // or return a public URL if you configure static serving
        } catch (Exception e) {
            throw new RuntimeException("Failed to store file: " + e.getMessage());
        }
    }
}
