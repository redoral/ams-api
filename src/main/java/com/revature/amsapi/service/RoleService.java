package com.revature.amsapi.service;

import com.revature.amsapi.entity.Role;
import com.revature.amsapi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }

    public Role getRole(int roleId) {
        return roleRepository.findById(roleId).orElseThrow(() -> new IllegalStateException("Customer with ID: " + roleId + " does not exist."));
    }

    public Role createRole(Role role){ return roleRepository.save(role); }

    public boolean deleteRole(int roleId) {
        roleRepository.findById(roleId).orElseThrow(() -> new IllegalStateException("Customer with ID: " + roleId + " does not exist."));

        try {
            roleRepository.deleteById(roleId);
        } catch (Exception e) {
            System.out.println("Error:" + e);
            return false;
        }

        return true;
    }

    @Transactional
    public Role updateRole(int roleId, String name) {
        Role updatedRole = roleRepository.findById(roleId).orElseThrow(() ->
                new IllegalStateException("Customer with ID: " + roleId + " does not exist."));

        if (name !=  null) {
            updatedRole.setName(name);
        }

        return roleRepository.save(updatedRole);
    }
}
