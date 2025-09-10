package com.internshipManagement.ongc.repository;

import com.internshipManagement.ongc.Model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, String> {

    // Find by email for login and uniqueness checks
    Optional<UserAccount> findByEmail(String email);

    // Find all users by role (admin, mentor, intern)
    List<UserAccount> findByRole(String role);

    // Case-insensitive search using LIKE
    @Query("SELECT u FROM UserAccount u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<UserAccount> searchByName(@Param("name") String name);
}
