package com.ers.ers.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reimbursement")
public class Reimbursement {
	 @Column(name = "reimId")
	    @Id
	    @GeneratedValue
	    private Integer reimId;

	    @Column(name = "description")
	    private String description;

	    @Column(name = "amount")
	    private int amount;

	    @Column(name = "status")
	    private String status;

	    @ManyToOne
	    @JoinColumn(name = "userId", nullable = false)
	    @JsonBackReference
	    private User user;

	    public Reimbursement() {
	    }

	    public Reimbursement(Integer reimId, String description, int amount, String status, User user) {
	        this.reimId = reimId;
	        this.description = description;
	        this.amount = amount;
	        this.status = status;
	        this.user = user;
	    }

	    public Reimbursement(String description, int amount, String status, User user) {
	        this.description = description;
	        this.amount = amount;
	        this.status = status;
	        this.user = user;
	    }

	    public Reimbursement(String description, int amount) {
	        this.description = description;
	        this.amount = amount;
	    }

	    public Integer getReimId() {
	        return reimId;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public int getAmount() {
	        return amount;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public User getUser() {
	        return user;
	    }

	    public void setReimId(Integer reimId) {
	        this.reimId = reimId;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public void setAmount(int amount) {
	        this.amount = amount;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    public void setUser(User user) {
	        this.user = user;
	    }

	}


