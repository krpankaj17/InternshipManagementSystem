package com.internshipManagement.ongc.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendanceRequestDTO {
    private String internId;          // required
    private String attendanceDate;    // required, YYYY-MM-DD
    private String status;            // required: "present", "absent"
    private String remarks;           // optional
    private String checkInTime;       // optional, ISO, used if present
    private String checkOutTime;      // optional, ISO, used if present
}
