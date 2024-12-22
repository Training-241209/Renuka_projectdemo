package com.ers.ers.Entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "roles")
public class Role {
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "roleId")
	    private int roleId;

	    @Column(name = "roleName")
	    private String roleName;

	    @OneToMany(mappedBy = "role")
	    @JsonManagedReference
	    private Set<User> users;

	    public Role() {
	    }

	    public Role(String roleName, Set<User> users) {
	        this.roleName = roleName;
	        this.users = users;
	    }

	    public Role(String roleName) {
	        this.roleName = roleName;
	    }

	    public int getRoleId() {
	        return roleId;
	    }

	    public String getRoleName() {
	        return roleName;
	    }

	    public Set<User> getUsers() {
	        return users;
	    }

	    public void setRoleId(int roleId) {
	        this.roleId = roleId;
	    }

	    public void setRoleName(String roleName) {
	        this.roleName = roleName;
	    }

	    public void setUsers(Set<User> users) {
	        this.users = users;
	    }
	}


