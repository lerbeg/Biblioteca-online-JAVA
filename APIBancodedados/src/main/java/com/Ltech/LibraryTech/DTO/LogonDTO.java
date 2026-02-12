package com.Ltech.LibraryTech.DTO;
import com.Ltech.LibraryTech.Entity.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.Ltech.LibraryTech.Entity.Logon;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogonDTO {
    private Integer id;
    private String nameUser;  // Agora corresponde a name_user
    private Role role;   // Adicionado campo id_role
    private String email;
    private String password;
}