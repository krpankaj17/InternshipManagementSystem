package com.internshipManagement.ongc.DTO;

import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendanceUpdateRequest {

    /**
     * Optional. Validated if present to ensure no future date is set.
     */
    @PastOrPresent(message = "Attendance date cannot be in the future")
    private Date attendanceDate;

    /**
     * Optional. New status (e.g., "present", "absent", "leave").
     */
    private String status;

    /**
     * Optional remarks to update.
     */
    private String remarks;

    /**
     * Optional. Used only if you need to reassign attendance to a different intern.
     */
    private String internId;

    /**
     * Optional. Allows admin/manual update of check-in time.
     * Should be in ISO 8601 format ("2025-07-23T09:00:00").
     */
    private String checkInTime;

    /**
     * Optional. Allows admin/manual update of check-out time.
     * Should be in ISO 8601 format ("2025-07-23T17:00:00").
     */
    private String checkOutTime;

    public @PastOrPresent(message = "Attendance date cannot be in the future") Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(@PastOrPresent(message = "Attendance date cannot be in the future") Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getInternId() {
        return internId;
    }

    public void setInternId(String internId) {
        this.internId = internId;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
    }
}
