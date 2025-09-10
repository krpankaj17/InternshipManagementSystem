package com.internshipManagement.ongc.Controller;

import com.internshipManagement.ongc.DTO.AttendanceRequestDTO;
import com.internshipManagement.ongc.DTO.AttendanceResponseDTO;
import com.internshipManagement.ongc.DTO.AttendanceUpdateRequest;
import com.internshipManagement.ongc.Services.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    /**
     * Mark attendance for an intern with status, check-in, check-out.
     * Example: present with times, absent with null times.
     */
    @PostMapping("/mark")
    public ResponseEntity<AttendanceResponseDTO> markAttendance(@RequestBody AttendanceRequestDTO dto) {
        AttendanceResponseDTO response = attendanceService.markAttendance(dto);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/all")
    public ResponseEntity<List<AttendanceResponseDTO>> getAll() {
        return ResponseEntity.ok(attendanceService.getAllAttendance());
    }

    @GetMapping("/intern/{internId}")
    public ResponseEntity<List<AttendanceResponseDTO>> getByIntern(@PathVariable String internId) {
        return ResponseEntity.ok(attendanceService.getAttendanceByIntern(internId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceResponseDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok(attendanceService.getAttendanceById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AttendanceResponseDTO> updateAttendance(
            @PathVariable String id,
            @RequestBody AttendanceUpdateRequest dto) {
        return ResponseEntity.ok(attendanceService.updateAttendance(id, dto));
    }

    @GetMapping("/search")
    public ResponseEntity<AttendanceResponseDTO> getByInternAndDate(
            @RequestParam String internId,
            @RequestParam String attendanceDate) {
        return ResponseEntity.ok(attendanceService.getAttendanceByInternAndDate(internId, attendanceDate));
    }
    /**
     * Delete an attendance record by its ID.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAttendance(@PathVariable String id) {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.ok("Attendance record with ID " + id + " has been deleted successfully.");
    }

}
