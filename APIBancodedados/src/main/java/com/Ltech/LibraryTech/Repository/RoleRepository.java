package com.Ltech.LibraryTech.Repository;
import com.Ltech.LibraryTech.Entity.*;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(String roleName);
    boolean existsByRoleName(String roleName);

    @Query("select r from Role  r where r.roleName in :names")
    List<Role> findRoleByNames(@Param("names") List<String> names);

    Role findRoleById(Integer id);
}
