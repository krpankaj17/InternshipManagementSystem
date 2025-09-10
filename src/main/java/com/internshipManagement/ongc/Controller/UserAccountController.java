package com.internshipManagement.ongc.Controller;

import com.internshipManagement.ongc.DTO.UserAccountResponseDTO;
import com.internshipManagement.ongc.mapper.UserAccountMapper;
import com.internshipManagement.ongc.Model.UserAccount;
import com.internshipManagement.ongc.Services.UserAccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // adjust for production
public class UserAccountController {

    @Autowired
    private UserAccountServices userAccountServices;

    @Autowired
    private UserAccountMapper userAccountMapper;

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<UserAccountResponseDTO> registerUser(@RequestBody UserAccount userAccount) {
        userAccount.setId(null); // Force id to null to ignore frontend id
        UserAccount registeredUser = userAccountServices.registerUser(userAccount);
        UserAccountResponseDTO dto = userAccountMapper.toDTO(registeredUser);
        return ResponseEntity.ok(dto);
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<UserAccountResponseDTO>> getAllUsers() {
        List<UserAccount> users = userAccountServices.getAllUsers();
        List<UserAccountResponseDTO> dtos = users.stream()
                .map(userAccountMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserAccountResponseDTO> getUserById(@PathVariable String id) {
        Optional<UserAccount> userOptional = userAccountServices.getUserById(id);
        return userOptional
                .map(user -> ResponseEntity.ok(userAccountMapper.toDTO(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Get user by Email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserAccountResponseDTO> getUserByEmail(@PathVariable String email) {
        Optional<UserAccount> userOptional = userAccountServices.getUserByEmail(email);
        return userOptional
                .map(user -> ResponseEntity.ok(userAccountMapper.toDTO(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Get users by Role
    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserAccountResponseDTO>> getUsersByRole(@PathVariable String role) {
        List<UserAccount> users = userAccountServices.getUsersByRole(role);
        List<UserAccountResponseDTO> dtos = users.stream()
                .map(userAccountMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    // Update user
    @PutMapping("/{id}")
    public ResponseEntity<UserAccountResponseDTO> updateUser(@PathVariable String id, @RequestBody UserAccount updatedUser) {
        Optional<UserAccount> existingUserOptional = userAccountServices.getUserById(id);
        if (existingUserOptional.isPresent()) {
            UserAccount existingUser = existingUserOptional.get();

            // Update fields safely
            if (updatedUser.getName() != null) existingUser.setName(updatedUser.getName());
            if (updatedUser.getEmail() != null) existingUser.setEmail(updatedUser.getEmail());
            if (updatedUser.getPasswordHash() != null) existingUser.setPasswordHash(updatedUser.getPasswordHash());
            if (updatedUser.getRole() != null) existingUser.setRole(updatedUser.getRole());
            if (updatedUser.getDepartment() != null) existingUser.setDepartment(updatedUser.getDepartment());

            UserAccount savedUser = userAccountServices.updateUser(existingUser);
            UserAccountResponseDTO dto = userAccountMapper.toDTO(savedUser);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userAccountServices.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Search users by name
    @GetMapping("/search")
    public ResponseEntity<List<UserAccountResponseDTO>> searchUsers(@RequestParam String name) {
        List<UserAccount> results = userAccountServices.searchUsersByName(name);
        List<UserAccountResponseDTO> dtos = results.stream()
                .map(userAccountMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }
}
