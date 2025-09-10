package com.internshipManagement.ongc.Services;

import com.internshipManagement.ongc.DTO.AttendanceRequestDTO;
import com.internshipManagement.ongc.DTO.AttendanceResponseDTO;
import com.internshipManagement.ongc.DTO.AttendanceUpdateRequest;

import java.util.List;

public interface AttendanceService {

    AttendanceResponseDTO markAttendance(AttendanceRequestDTO dto);

    List<AttendanceResponseDTO> getAllAttendance();

    List<AttendanceResponseDTO> getAttendanceByIntern(String internId);

    AttendanceResponseDTO getAttendanceById(String id);

    AttendanceResponseDTO updateAttendance(String id, AttendanceUpdateRequest dto);

    AttendanceResponseDTO getAttendanceByInternAndDate(String internId, String attendanceDate);
    void deleteAttendance(String id);

}
