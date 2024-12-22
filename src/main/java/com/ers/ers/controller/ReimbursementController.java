package com.ers.ers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ers.ers.Entity.Reimbursement;
import com.ers.ers.Entity.User;
import com.ers.ers.service.JwtService;
import com.ers.ers.service.ReimbursementService;
@RestController
@RequestMapping("/reimbursement")
public class ReimbursementController {
	

	    private ReimbursementService reimbursementService;
	    private JwtService jwtService;

	    @Autowired
	    public ReimbursementController(ReimbursementService reimbursementService, JwtService jwtService) {
	        this.reimbursementService = reimbursementService;
	        this.jwtService = jwtService;
	    }

	    @PostMapping("/createReimbursement")
	    public ResponseEntity<Reimbursement> createReimbursement(@RequestHeader(value = "Token") String token,
	            @RequestBody Reimbursement reimbursement) {
	        User user = jwtService.decodeToken(token);
	        if (user != null) {
	        	reimbursement.setUser(user); // Ensure the user is set
	            Reimbursement createdReimbursement = reimbursementService.createReimbursement(reimbursement, user);
	            return ResponseEntity.status(HttpStatus.OK).body(createdReimbursement);
	        }

	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	    }

	    @PostMapping("/updateReimbursementStatus")
	    public ResponseEntity<Reimbursement> updateReimbursementStatus(@RequestHeader(value = "Token") String token,
	            @RequestBody Reimbursement reimbursement) {
	        User user = jwtService.decodeToken(token);
	        if (user != null && user.getRole().getRoleName().equals("manager")) {
	            Reimbursement updatedReimbursement = reimbursementService.updateReimbursementStatus(reimbursement);
	            if (updatedReimbursement != null)
	                return ResponseEntity.status(HttpStatus.OK).body(updatedReimbursement);
	            else
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        } else if (user != null && user.getRole().getRoleName().equals("Employee")) {
	            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	            // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	        }
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	    }

	    @PostMapping("/viewReimbursements")
	    public ResponseEntity<List<Reimbursement>> viewReimbursement(@RequestHeader(value = "Token") String token) {
	        User user = jwtService.decodeToken(token);
	        if (user != null) {
	            List<Reimbursement> foundReimbursements = reimbursementService.viewReimbursement(user);
	            if (foundReimbursements != null)
	                return ResponseEntity.status(HttpStatus.OK).body(foundReimbursements);
	            else
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	    }

	}


