package com.example.demo.Service;

import com.example.demo.JavaClasses.Lawyer;
import com.example.demo.JavaClasses.User;
import com.example.demo.Repo.LawyerRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LawyerService {
    private final LawyerRepo lawyerRepo;
    public LawyerService(LawyerRepo ur){
        lawyerRepo=ur;
    }

    public Lawyer createLawyer(Lawyer lawyer) {
        return lawyerRepo.save(lawyer);
    }
    public List<Lawyer> getAllLawyers() {
        return lawyerRepo.findAll();
    }

    public Lawyer getLawyerById(int id) {
        return lawyerRepo.findById(id).orElse(null);
    }

    public void deleteLawyer(int id) {
        lawyerRepo.deleteById(id);
    }
    public Lawyer updateLawyer(Lawyer updatedLawyer) {
        Optional<Lawyer> existingLawyerOptional = lawyerRepo.findById(updatedLawyer.getLawyerID());
        if (existingLawyerOptional.isPresent()) {
            Lawyer existingUser = existingLawyerOptional.get();
            existingUser.setLawyerName(updatedLawyer.getLawyerName());
            existingUser.setEmail(updatedLawyer.getEmail());
            existingUser.setSalary(updatedLawyer.getSalary());

            return lawyerRepo.save(existingUser);
        } else {

            throw new EntityNotFoundException("User not found with ID: " + updatedLawyer.getLawyerName());
        }
    }
}
