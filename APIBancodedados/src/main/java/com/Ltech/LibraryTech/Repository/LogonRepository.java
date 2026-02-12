package com.Ltech.LibraryTech.Repository;

import com.Ltech.LibraryTech.Entity.Logon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogonRepository extends JpaRepository<Logon, Integer> {
    Logon findByEmail(String email);
    boolean existsByEmail(String email);
}