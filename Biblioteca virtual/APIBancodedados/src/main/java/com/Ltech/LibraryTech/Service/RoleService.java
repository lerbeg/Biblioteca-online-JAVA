package com.Ltech.LibraryTech.Service;

import com.Ltech.LibraryTech.DTO.RoleDTO;
import com.Ltech.LibraryTech.Entity.Role;
import com.Ltech.LibraryTech.Repository.RoleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public RoleDTO saveRole(RoleDTO roleDTO){
        Role role = new Role();
        role.setRoleName(roleDTO.getRoleName());
        Role savedRole= roleRepository.save(role);
        return new RoleDTO(
                savedRole.getId(),
                savedRole.getRoleName()
        );
    }
    public List<RoleDTO> GetAllRole(){
        List<Role> role = roleRepository.findAll();
        return role.stream()
                .map(roles -> new RoleDTO(
                 roles.getId(),
                 roles.getRoleName()
                ))
                .collect(Collectors.toList());
    }

    public RoleDTO UpdateRole(Integer id, RoleDTO roleDTO){
        Role  role = roleRepository.findRoleById(roleDTO.getId());
        if(role != null){
            role.setRoleName(roleDTO.getRoleName());
        Role roleUpdate = roleRepository.save(role);

        return  new RoleDTO(
          roleUpdate.getId(),
          roleUpdate.getRoleName()
        );
        }
        return null;
    }

    public boolean deleteRole(Integer id){
        if(roleRepository.existsById(id)) {
        roleRepository.deleteById(id);
        return  true;
        }
        return false;
    }
}
