package com.example.prestamoLibro.Model.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.prestamoLibro.Model.Entity.Usuario;

@Repository
public interface UsuariosDAOIface extends  JpaRepository<Usuario, Long> {
    
}
