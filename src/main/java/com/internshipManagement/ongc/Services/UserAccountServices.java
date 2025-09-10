package com.internshipManagement.ongc.Services;

import com.internshipManagement.ongc.Model.UserAccount;

import java.util.List;
import java.util.Optional;

public interface UserAccountServices {

    UserAccount registerUser(UserAccount userAccount);

    List<UserAccount> getAllUsers();

    Optional<UserAccount> getUserById(String id); // UUID ➔ String

    Optional<UserAccount> getUserByEmail(String email);

    List<UserAccount> getUsersByRole(String role);

    UserAccount updateUser(UserAccount userAccount);

    List<UserAccount> searchUsersByName(String name);

    void deleteUser(String id); // UUID ➔ String
}
