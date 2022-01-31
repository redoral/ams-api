package com.revature.amsapi.controller;

import com.revature.amsapi.entity.Role;
import com.revature.amsapi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/roles")
@CrossOrigin
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @GetMapping
    public List<Role> getRoles(){
        return roleService.getRoles();
    }

    @GetMapping(path = "{roleId}")
    public Role getRoleById(@PathVariable("roleId") Integer roleId) {
        try {
            return roleService.getRole(roleId);
        } catch (IllegalStateException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PostMapping
    public Role createRole(@RequestBody Role role){ return roleService.createRole(role); }

    @DeleteMapping(path = "{roleId}")
    public boolean deleteRole(@PathVariable("roleId") Integer roleId){
        try {
            roleService.deleteRole(roleId);
            return true;
        } catch(IllegalStateException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @PutMapping(path = "{roleId}/{roleName}")
    public Role updateRole(@PathVariable("roleId") Integer roleId, @PathVariable("roleName") String roleName){
        try {
            Role updatedRole = roleService.updateRole(roleId, roleName);
            return updatedRole;
        } catch (IllegalStateException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
