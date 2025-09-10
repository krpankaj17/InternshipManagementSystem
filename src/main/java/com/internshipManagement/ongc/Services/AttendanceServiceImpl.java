package com.internshipManagement.ongc.Services;

import com.internshipManagement.ongc.DTO.AttendanceRequestDTO;
import com.internshipManagement.ongc.DTO.AttendanceResponseDTO;
import com.internshipManagement.ongc.DTO.AttendanceUpdateRequest;
import com.internshipManagement.ongc.Model.Attendance;
import com.internshipManagement.ongc.Model.Interns;
import com.internshipManagement.ongc.repository.AttendanceRepository;
import com.internshipManagement.ongc.repository.InternRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private InternRepository internRepository;

    /**
     * Unified mark attendance: "present" with times, "absent" with null times.
     */
    public AttendanceResponseDTO markAttendance(AttendanceRequestDTO dto) {
        LocalDate attendanceDate = LocalDate.parse(dto.getAttendanceDate());

        if (attendanceRepository.existsByIntern_InternIdAndAttendanceDate(dto.getInternId(), attendanceDate)) {
            throw new IllegalStateException("Attendance already marked for this intern on this date.");
        }

        Interns intern = internRepository.findById(dto.getInternId())
                .orElseThrow(() -> new RuntimeException("Intern not found with ID: " + dto.getInternId()));

        Attendance attendance = new Attendance();
        attendance.setIntern(intern);
        attendance.setAttendanceDate(attendanceDate);

        String status = dto.getStatus() != null ? dto.getStatus().toLowerCase() : "present";
        attendance.setStatus(status);
        attendance.setRemarks(dto.getRemarks());

        if ("present".equals(status)) {
            LocalDateTime checkIn = dto.getCheckInTime() != null && !dto.getCheckInTime().isBlank()
                    ? LocalDateTime.parse(dto.getCheckInTime())
                    : LocalDateTime.now();
            LocalDateTime checkOut = dto.getCheckOutTime() != null && !dto.getCheckOutTime().isBlank()
                    ? LocalDateTime.parse(dto.getCheckOutTime())
                    : null; // allow null check-out if user will check out later

            attendance.setCheckInTime(checkIn);
            attendance.setCheckOutTime(checkOut);
        } else if ("absent".equals(status)) {
            attendance.setCheckInTime(null);
            attendance.setCheckOutTime(null);
        } else {
            throw new IllegalArgumentException("Invalid status: " + status + ". Use 'present' or 'absent'.");
        }

        Attendance saved = attendanceRepository.save(attendance);
        return mapToDTO(saved);
    }

    @Override
    public AttendanceResponseDTO getAttendanceById(String id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found with ID: " + id));
        return mapToDTO(attendance);
    }

    @Override
    public List<AttendanceResponseDTO> getAllAttendance() {
        return attendanceRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceResponseDTO> getAttendanceByIntern(String internId) {
        return attendanceRepository.findByIntern_InternId(internId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AttendanceResponseDTO updateAttendance(String id, AttendanceUpdateRequest dto) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found with ID: " + id));

        if (dto.getAttendanceDate() != null) {
            attendance.setAttendanceDate(dto.getAttendanceDate().toLocalDate());
        }

        if (dto.getStatus() != null && !dto.getStatus().isBlank()) {
            attendance.setStatus(dto.getStatus().toLowerCase());
        }

        if (dto.getRemarks() != null && !dto.getRemarks().isBlank()) {
            attendance.setRemarks(dto.getRemarks());
        }

        if (dto.getCheckInTime() != null && !dto.getCheckInTime().isBlank()) {
            attendance.setCheckInTime(parseFlexibleDateTime(
                    dto.getCheckInTime(),
                    attendance.getAttendanceDate() != null ? attendance.getAttendanceDate() : LocalDate.now(),
                    true
            ));
        }

        if (dto.getCheckOutTime() != null && !dto.getCheckOutTime().isBlank()) {
            attendance.setCheckOutTime(parseFlexibleDateTime(
                    dto.getCheckOutTime(),
                    attendance.getAttendanceDate() != null ? attendance.getAttendanceDate() : LocalDate.now(),
                    false
            ));
        }

        if (dto.getInternId() != null && !dto.getInternId().isBlank()) {
            Interns intern = internRepository.findById(dto.getInternId())
                    .orElseThrow(() -> new RuntimeException("Intern not found with ID: " + dto.getInternId()));
            attendance.setIntern(intern);
        }

        Attendance updated = attendanceRepository.save(attendance);
        return mapToDTO(updated);
    }

    @Override
    public AttendanceResponseDTO getAttendanceByInternAndDate(String internId, String attendanceDate) {
        LocalDate date = LocalDate.parse(attendanceDate);
        Attendance attendance = attendanceRepository.findByIntern_InternIdAndAttendanceDate(internId, date)
                .orElse(null);
        return attendance != null ? mapToDTO(attendance) : null;
    }

    private AttendanceResponseDTO mapToDTO(Attendance attendance) {
        return AttendanceResponseDTO.builder()
                .id(attendance.getId())
                .internId(attendance.getIntern().getInternId())
                .internName(attendance.getIntern().getName())
                .attendanceDate(attendance.getAttendanceDate())
                .status(attendance.getStatus())
                .remarks(attendance.getRemarks())
                .checkInTime(attendance.getCheckInTime() != null ? attendance.getCheckInTime().toString() : null)
                .checkOutTime(attendance.getCheckOutTime() != null ? attendance.getCheckOutTime().toString() : null)
                .build();
    }
    private LocalDateTime parseFlexibleDateTime(String input, LocalDate date, boolean isCheckIn) {
        if (input == null || input.isBlank()) {
            return isCheckIn ? LocalDateTime.now() : null;
        }
        try {
            if (input.contains("T")) {
                return LocalDateTime.parse(input); // Handles ISO format
            } else {
                LocalTime time = LocalTime.parse(input); // Handles "HH:mm"
                return LocalDateTime.of(date, time);
            }
        } catch (Exception e) {
            throw new RuntimeException("Invalid time format: " + input + ". Use 'HH:mm' or 'YYYY-MM-DDTHH:MM:SS'", e);
        }
    }
    @Override
    public void deleteAttendance(String id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found with ID: " + id));
        attendanceRepository.delete(attendance);
    }


}
