package com.Ltech.LibraryTech.Controller;

import com.Ltech.LibraryTech.DTO.RoleDTO;
import com.Ltech.LibraryTech.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/v1/api/role")
@CrossOrigin(origins = "*")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @PostMapping("/create")
    public ResponseEntity<?> SaveRole(@RequestBody RoleDTO roleDTO){
        try{
            RoleDTO rolesaved = roleService.saveRole(roleDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(rolesaved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("X error" + e.getMessage());
        }
    }
    @GetMapping("/all")
    public ResponseEntity<?> GetAllRole(){
        List<RoleDTO> roleDTOList= roleService.GetAllRole();
        return ResponseEntity.ok(roleDTOList);

    }
    @DeleteMapping("/delete{id}")
    public ResponseEntity<?>deleteRole(@PathVariable Integer id){
        try {
            boolean delete= roleService.deleteRole(id);
            if (delete){
                return ResponseEntity.ok( "Role deletada com sucesso");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role não encontrada");

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
        }
    }

}

