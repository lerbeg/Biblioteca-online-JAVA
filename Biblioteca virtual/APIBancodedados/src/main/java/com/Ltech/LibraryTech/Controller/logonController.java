package com.Ltech.LibraryTech.Controller;

import com.Ltech.LibraryTech.DTO.LogonDTO;
import com.Ltech.LibraryTech.Service.LogonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/logon")
@CrossOrigin(origins = "*")
public class logonController {

    @Autowired
    private LogonService logonService;

    // ========== CREATE ==========
    @PostMapping("/create")
    public ResponseEntity<?> createLogon(@RequestBody LogonDTO logonDTO) {
        try {
            if (logonService.emailExists(logonDTO.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("❌ Email já cadastrado!");
            }

            LogonDTO savedLogon = logonService.saveLogon(logonDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedLogon);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Erro: " + e.getMessage());
        }
    }

    // ========== READ ALL ==========
    @GetMapping("/all")
    public ResponseEntity<?> getAllLogons() {
        try {
            List<LogonDTO> logons = logonService.getAllLogons();
            return ResponseEntity.ok(logons);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Erro: " + e.getMessage());
        }
    }

    // ========== READ BY ID ==========
    @GetMapping("/{id}")
    public ResponseEntity<?> getLogonById(@PathVariable Integer id) {
        try {
            LogonDTO logon = logonService.getLogonById(id);
            if (logon != null) {
                return ResponseEntity.ok(logon);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("❌ Usuário não encontrado com ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Erro: " + e.getMessage());
        }
    }

    // ========== READ BY EMAIL ==========
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getLogonByEmail(@PathVariable String email) {
        try {
            LogonDTO logon = logonService.getLogonByEmail(email);
            if (logon != null) {
                return ResponseEntity.ok(logon);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("❌ Usuário não encontrado com email: " + email);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Erro: " + e.getMessage());
        }
    }

    // ========== UPDATE ==========
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateLogon(@PathVariable Integer id, @RequestBody LogonDTO logonDTO) {
        try {
            LogonDTO updatedLogon = logonService.updateLogon(id, logonDTO);
            if (updatedLogon != null) {
                return ResponseEntity.ok(updatedLogon);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("❌ Usuário não encontrado com ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Erro: " + e.getMessage());
        }
    }

    // ========== DELETE ==========
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLogon(@PathVariable Integer id) {
        try {
            boolean deleted = logonService.deleteLogon(id);
            if (deleted) {
                return ResponseEntity.ok("✅ Usuário deletado com sucesso!");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("❌ Usuário não encontrado com ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Erro: " + e.getMessage());
        }
    }

    // ========== HEALTH CHECK ==========
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("✅ API CRUD está funcionando!");
    }

    // ========== TEST DB ==========
    @GetMapping("/test-db")
    public ResponseEntity<String> testDatabase() {
        String result = logonService.testConnection();
        return ResponseEntity.ok(result);
    }
}