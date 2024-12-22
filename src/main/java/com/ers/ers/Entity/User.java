package com.ers.ers.Entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Integer userId;

	    @Column(nullable = false)
	    private String firstName;

	    @Column(nullable = false)
	    private String lastName;

	    @Column(unique = true)
	    private String username;

	    @Column(nullable = false)
	    private String password;

	    @ManyToOne
	    @JoinColumn(name = "roleId", nullable = false)
	    @JsonBackReference
	    private Role role;

	    @OneToMany(mappedBy = "user")
	    @JsonManagedReference
	    private Set<Reimbursement> reimbursements;

	    public User() {
	    }

	    public User(String firstName, String lastName, String username, String password, Role role) {
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.username = username;
	        this.password = password;
	        this.role = role;
	    }

	    public User(Integer userId, String firstName, String lastName, String username, String password, Role role) {
	        this.userId = userId;
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.username = username;
	        this.password = password;
	        this.role = role;
	    }

	    public User(Integer userId, String username) {
	        this.userId = userId;
	        this.username = username;
	    }

	    public User(String username, String password) {
	        this.username = username;
	        this.password = password;
	    }

	    public User(Integer userId, String username, String role) {
	        this.userId = userId;
	        this.username = username;
	        this.role = new Role(role);
	    }

	    public Integer getUserId() {
	        return userId;
	    }

	    public String getFirstName() {
	        return firstName;
	    }

	    public String getLastName() {
	        return lastName;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public Role getRole() {
	        return role;
	    }

	    public Set<Reimbursement> getReimbursements() {
	        return reimbursements;
	    }

	    public void setUserId(Integer userId) {
	        this.userId = userId;
	    }

	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }

	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public void setRole(Role role) {
	        this.role = role;
	    }

	    public void setReimbursements(Set<Reimbursement> reimbursements) {
	        this.reimbursements = reimbursements;
	    }

	}


