package com.revature.amsapi.service;

import com.revature.amsapi.entity.Role;
import com.revature.amsapi.exception.RoleNotFoundException;
import com.revature.amsapi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }

    public Role getRole(int roleId) throws RoleNotFoundException {
        return roleRepository.findById(roleId).orElseThrow(() -> new RoleNotFoundException("Role with ID: " + roleId + " does not exist."));
    }

    public Role createRole(Role role){ return roleRepository.save(role); }

    public boolean deleteRole(int roleId) throws RoleNotFoundException {
        roleRepository.findById(roleId).orElseThrow(() -> new RoleNotFoundException("Role with ID: " + roleId + " does not exist."));
        roleRepository.deleteById(roleId);
        return true;
    }

    @Transactional
    public Role updateRole(int roleId, String name) throws RoleNotFoundException {
        Role updatedRole = roleRepository.findById(roleId).orElseThrow(() -> new RoleNotFoundException("Role with ID: " + roleId + " does not exist."));

        if (name != null) {
            updatedRole.setName(name);
        }

        return roleRepository.save(updatedRole);
    }
}
