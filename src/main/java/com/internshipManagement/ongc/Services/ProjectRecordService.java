package com.internshipManagement.ongc.Services;

import com.internshipManagement.ongc.DTO.ProjectRecordDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProjectRecordService {

    ProjectRecordDTO createProject(ProjectRecordDTO dto);
    List<ProjectRecordDTO> getAllProjects();
    ProjectRecordDTO getProjectById(String id);
    ProjectRecordDTO updateProject(String id, ProjectRecordDTO dto);
    void deleteProject(String id);

    String storeFile(MultipartFile file); // for file uploads
}
