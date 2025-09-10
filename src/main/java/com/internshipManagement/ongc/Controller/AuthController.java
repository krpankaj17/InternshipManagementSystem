package com.internshipManagement.ongc.Controller;

import com.internshipManagement.ongc.Model.UserAccount;
import com.internshipManagement.ongc.Services.UserAccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // adjust for production
public class AuthController {

    @Autowired
    private UserAccountServices userAccountServices;

    /**
     * Login endpoint for the frontend
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        if (email == null || password == null) {
            return ResponseEntity.badRequest().body("Email and password must be provided.");
        }

        Optional<UserAccount> userOptional = userAccountServices.getUserByEmail(email);

        if (userOptional.isPresent()) {
            UserAccount user = userOptional.get();

            // ⚠️ Replace this simple check with BCrypt password matching if hashed
            if (user.getPasswordHash().equals(password)) {

                // Generate token (replace with JWT generation in production)
                String fakeToken = "sample-token-for-" + user.getId();

                Map<String, Object> response = new HashMap<>();
                response.put("token", fakeToken);
                response.put("user", user);

                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(401).body("Invalid credentials.");
            }
        } else {
            return ResponseEntity.status(404).body("User not found.");
        }
    }

    /**
     * Optional: Check token validity (extend with JWT validation later)
     */
    @GetMapping("/check")
    public ResponseEntity<String> checkAuth() {
        return ResponseEntity.ok("Authenticated");
    }
}

