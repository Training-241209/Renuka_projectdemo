package com.ers.ers.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ers.ers.Entity.Reimbursement;
import com.ers.ers.Entity.User;
import com.ers.ers.Repository.ReimbursementRepository;
import com.ers.ers.Repository.UserRepository;

@Service
public class ReimbursementService {
    ReimbursementRepository reimbursementRepository;
    UserRepository userRepository;

    public ReimbursementService(ReimbursementRepository reimbursementRepository, UserRepository userRepository) {
        this.reimbursementRepository = reimbursementRepository;
        this.userRepository = userRepository;
    }

    public Reimbursement createReimbursement(Reimbursement reimbursement, User user) {
        if (reimbursement.getDescription() == null || reimbursement.getDescription().trim().isEmpty()
                || reimbursement.getDescription().length() > 255 || reimbursement.getAmount() <= 0) {
            return null;
        }
        reimbursement.setStatus("Pending");
        reimbursement.setUser(user);
        return reimbursementRepository.save(reimbursement);
    }

    public Reimbursement updateReimbursementStatus(Reimbursement reimbursement) {
        Optional<Reimbursement> optionalReimbursement = reimbursementRepository.findById(reimbursement.getReimId());
        if (optionalReimbursement.isPresent()) {
            Reimbursement foundReimbursement = optionalReimbursement.get();
            foundReimbursement.setStatus(reimbursement.getStatus());
            return reimbursementRepository.save(foundReimbursement);
        }
        return null;
    }

    public List<Reimbursement> viewReimbursement(User user) {
        if (user.getRole().getRoleName().equals("Admin")) {
            return reimbursementRepository.findAll();
        } else if (user.getRole().getRoleName().equals("Employee")) {
            return reimbursementRepository.findByUser(user);
        }
        return null;
    }

}