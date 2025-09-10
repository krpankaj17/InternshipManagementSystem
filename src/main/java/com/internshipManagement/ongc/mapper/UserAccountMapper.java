package com.internshipManagement.ongc.mapper;

import com.internshipManagement.ongc.DTO.UserAccountResponseDTO;
import com.internshipManagement.ongc.Model.UserAccount;
import org.springframework.stereotype.Component;

@Component
public class UserAccountMapper {

    public UserAccountResponseDTO toDTO(UserAccount user) {
        UserAccountResponseDTO dto = new UserAccountResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setDepartment(user.getDepartment());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        return dto;
    }
}
