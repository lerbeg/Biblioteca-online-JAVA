package com.Ltech.LibraryTech.Service;

import com.Ltech.LibraryTech.DTO.LogonDTO;
import com.Ltech.LibraryTech.Entity.Logon;
import com.Ltech.LibraryTech.Repository.LogonRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LogonService {

    @Autowired
    private LogonRepository logonRepository;

    @PersistenceContext
    private EntityManager entityManager;

    // ========== CREATE ==========
    public LogonDTO saveLogon(LogonDTO logonDTO) {
        Logon logon = new Logon();
        logon.setNameUser(logonDTO.getNameUser());
        logon.setRole(logonDTO.getRole());
        logon.setEmail(logonDTO.getEmail());
        logon.setPassword(logonDTO.getPassword());

        Logon savedLogon = logonRepository.save(logon);

        return new LogonDTO(
                savedLogon.getId(),
                savedLogon.getNameUser(),
                savedLogon.getRole(),
                savedLogon.getEmail(),
                null  // Não retorna senha
        );
    }

    // ========== READ ALL ==========
    public List<LogonDTO> getAllLogons() {
        List<Logon> logons = logonRepository.findAll();
        return logons.stream()
                .map(logon -> new LogonDTO(
                        logon.getId(),
                        logon.getNameUser(),
                        logon.getRole(),
                        logon.getEmail(),
                        null  // Não retorna senha
                ))
                .collect(Collectors.toList());
    }

    // ========== READ BY ID ==========
    public LogonDTO getLogonById(Integer id) {
        Logon logon = logonRepository.findById(id).orElse(null);
        if (logon != null) {
            return new LogonDTO(
                    logon.getId(),
                    logon.getNameUser(),
                    logon.getRole(),
                    logon.getEmail(),
                    null
            );
        }
        return null;
    }

    // ========== READ BY EMAIL ==========
    public LogonDTO getLogonByEmail(String email) {
        Logon logon = logonRepository.findByEmail(email);
        if (logon != null) {
            return new LogonDTO(
                    logon.getId(),
                    logon.getNameUser(),
                    logon.getRole(),
                    logon.getEmail(),
                    null
            );
        }
        return null;
    }

    // ========== UPDATE ==========
    public LogonDTO updateLogon(Integer id, LogonDTO logonDTO) {
        Logon logon = logonRepository.findById(id).orElse(null);
        if (logon != null) {
            logon.setNameUser(logonDTO.getNameUser());
            logon.setRole(logonDTO.getRole());
            logon.setEmail(logonDTO.getEmail());
            if (logonDTO.getPassword() != null && !logonDTO.getPassword().isEmpty()) {
                logon.setPassword(logonDTO.getPassword());
            }

            Logon updatedLogon = logonRepository.save(logon);

            return new LogonDTO(
                    updatedLogon.getId(),
                    updatedLogon.getNameUser(),
                    updatedLogon.getRole(),
                    updatedLogon.getEmail(),
                    null
            );
        }
        return null;
    }

    // ========== DELETE ==========
    public boolean deleteLogon(Integer id) {
        if (logonRepository.existsById(id)) {
            logonRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // ========== VALIDATIONS ==========
    public boolean emailExists(String email) {
        return logonRepository.existsByEmail(email);
    }

    public boolean existsById(Integer id) {
        return logonRepository.existsById(id);
    }

    // ========== UTILITIES ==========
    public long getRepositoryCount() {
        return logonRepository.count();
    }

    public String testConnection() {
        try {
            long count = logonRepository.count();
            return "✅ Conexão OK! Total de usuários: " + count;
        } catch (Exception e) {
            return "❌ Erro na conexão: " + e.getMessage();
        }
    }
}