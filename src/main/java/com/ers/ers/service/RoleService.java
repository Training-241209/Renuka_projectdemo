package com.ers.ers.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ers.ers.Entity.Role;
import com.ers.ers.Repository.RoleRepository;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role createRole(Role role) {
        Optional<Role> existingRole = roleRepository.findByRoleName(role.getRoleName());
        if (!existingRole.isPresent()) {
            return roleRepository.save(role);
        }
        return null;

    }

}