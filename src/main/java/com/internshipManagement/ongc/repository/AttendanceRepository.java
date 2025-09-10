package com.internshipManagement.ongc.repository;

import com.internshipManagement.ongc.Model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, String> {

    /**
     * Retrieve all attendance records for a specific intern.
     */
    List<Attendance> findByIntern_InternId(String internId);

    /**
     * Check if an attendance record already exists for the given intern on a specific date.
     */
    boolean existsByIntern_InternIdAndAttendanceDate(String internId, LocalDate attendanceDate);

    /**
     * Retrieve an attendance record for a specific intern on a specific date.
     */
    Optional<Attendance> findByIntern_InternIdAndAttendanceDate(String internId, LocalDate attendanceDate);

    /**
     * Retrieve all attendance records for a specific date.
     */
    List<Attendance> findByAttendanceDate(LocalDate attendanceDate);

    /**
     * Retrieve attendance records where checkout has not yet been marked for an intern on a specific date.
     * Useful for tracking pending check-outs.
     */
    List<Attendance> findByIntern_InternIdAndAttendanceDateAndCheckOutTimeIsNull(String internId, LocalDate attendanceDate);

    /**
     * Retrieve attendance records where check-in has not yet been marked for an intern on a specific date.
     * Useful for validation or reporting gaps.
     */
    List<Attendance> findByIntern_InternIdAndAttendanceDateAndCheckInTimeIsNull(String internId, LocalDate attendanceDate);

}
