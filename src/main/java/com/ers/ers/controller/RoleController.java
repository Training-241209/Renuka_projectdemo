package com.ers.ers.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ers.ers.Entity.Role;
import com.ers.ers.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/createrole")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role newRole = roleService.createRole(role);
        if (newRole != null) {
            return ResponseEntity.status(HttpStatus.OK).body(newRole);
        } else
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

}