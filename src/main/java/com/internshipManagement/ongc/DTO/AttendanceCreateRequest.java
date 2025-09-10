package com.internshipManagement.ongc.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * DTO for creating or updating attendance records,
 * supporting manual entry of check-in and check-out times,
 * aligned with ONGC Internship Management logic.
 */
@Data
public class AttendanceCreateRequest {

    /**
     * Intern's unique ID.
     */
    @NotBlank(message = "Intern ID is required")
    private String internId;

    /**
     * Attendance date for the record (cannot be in the future).
     */
    @NotNull(message = "Attendance date is required")
    @PastOrPresent(message = "Attendance date cannot be in the future")
    private Date attendanceDate;

    /**
     * Attendance status, e.g., "present", "absent".
     */
    @NotBlank(message = "Status is required (e.g., Present, Absent)")
    private String status;

    /**
     * Optional remarks.
     */
    private String remarks;

    /**
     * Optional manual check-in time; if null, backend will use current timestamp.
     */
    private Timestamp checkInTime;

    /**
     * Optional manual check-out time; if null, backend will use current timestamp on checkout.
     */
    private Timestamp checkOutTime;
}
