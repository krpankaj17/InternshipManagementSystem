package com.internshipManagement.ongc.DTO;

import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendanceResponseDTO {
    private String id;
    private String internId;
    private String internName;
    private LocalDate attendanceDate;
    private String status;
    private String remarks;
    private String checkInTime;  // ISO 8601 string or null
    private String checkOutTime; // ISO 8601 string or null
}
