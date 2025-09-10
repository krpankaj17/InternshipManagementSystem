package com.internshipManagement.ongc.Services;

import com.internshipManagement.ongc.Model.UserAccount;
import com.internshipManagement.ongc.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserAccountServiceImpl implements UserAccountServices {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public UserAccount registerUser(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }



    @Override
    public List<UserAccount> getAllUsers() {
        return userAccountRepository.findAll();
    }

    @Override
    public Optional<UserAccount> getUserById(String id) {
        return userAccountRepository.findById(id);
    }

    @Override
    public Optional<UserAccount> getUserByEmail(String email) {
        return userAccountRepository.findByEmail(email);
    }

    @Override
    public List<UserAccount> getUsersByRole(String role) {
        return userAccountRepository.findByRole(role);
    }

    @Override
    public UserAccount updateUser(UserAccount userAccount) {
        // It is safe to use save() for update as JPA handles merge
        return userAccountRepository.save(userAccount);
    }

    @Override
    public void deleteUser(String id) {
        userAccountRepository.deleteById(id);
    }

    @Override
    public List<UserAccount> searchUsersByName(String name) {
        return userAccountRepository.searchByName(name);
    }
}
