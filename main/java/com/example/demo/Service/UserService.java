package com.example.demo.Service;

import com.example.demo.JavaClasses.User;
import com.example.demo.Repo.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    LawyerService lawyerService;
    private final UserRepo userRepo;

    public UserService(UserRepo ur) {
        userRepo = ur;
    }

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(int id) {
        return userRepo.findById(id).orElse(null);
    }

    public void deleteUser(int id) {
        System.out.println("delete user : "+id);
        userRepo.deleteById(id);
    }

    public User updateLawyerofUser(int userID, int lawyerID) {
        Optional<User> optionalUser = userRepo.findById(userID);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setLawyer(lawyerService.getLawyerById(lawyerID));
            return userRepo.save(user);
        } else {
            throw new EntityNotFoundException("Product not found with ID: " + userID);
        }
    }

    public User updateUser(User updatedUser) {
        Optional<User> existingUserOptional = userRepo.findById(updatedUser.getId());
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setSalary(updatedUser.getSalary());
            existingUser.setShareValue(updatedUser.getShareValue());
            existingUser.setPropertyValue(updatedUser.getPropertyValue());
            existingUser.setNetIncome(updatedUser.getNetIncome());
            return userRepo.save(existingUser);
        } else {

            throw new EntityNotFoundException("User not found with ID: " + updatedUser.getId());
        }
    }
}
